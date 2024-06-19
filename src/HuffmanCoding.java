import java.io.*;
import java.util.*;

public class HuffmanCoding {

    static class HuffmanNode implements Comparable<HuffmanNode> {
        char data;
        int frequency;
        HuffmanNode left, right;

        HuffmanNode(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }

        @Override
        public int  compareTo(HuffmanNode node) {
            return this.frequency - node.frequency;
        }
    }

        // Method to perform compression and collect statistics
        public static HashMap<String, Object> compress(String inputFile, String outputFile) throws IOException {
            HashMap<String, Object> stats = new HashMap<>();
            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            String magicWord = "HUFF";
            oos.writeUTF(magicWord);
            System.out.println("Compressing.....");
            int[] freq = new int[256];  // Frequency array for all possible byte values
            byte[] buffer = new byte[1024];
            int bytesRead, totalBytes = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                totalBytes += bytesRead;
                for (int i = 0; i < bytesRead; i++) {
                    freq[buffer[i] & 0xff]++;
                }
            }
            fis.close();

            PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
            for (int i = 0; i < 256; i++) {
                if (freq[i] > 0) {
                    pq.add(new HuffmanNode((char) i, freq[i]));
                }
            }

            // Statistic: Number of distinct characters
            int distinctCharacters = pq.size();
            stats.put("distinctCharacters", distinctCharacters);

            HuffmanNode root = null;
            while (pq.size() > 1) {
                HuffmanNode left = pq.poll();
                HuffmanNode right = pq.poll();
                HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
                parent.left = left;
                parent.right = right;
                pq.add(parent);
                root = parent;
            }

            Map<Character, String> huffmanCodes = generateCodes(root);

            fis = new FileInputStream(inputFile);
            BitSet bitSet = new BitSet();
            int bitLength = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    String code = huffmanCodes.get((char) (buffer[i] & 0xff));
                    for (char c : code.toCharArray()) {
                        if (c == '1') bitSet.set(bitLength);
                        bitLength++;
                    }
                }
            }
            fis.close();

            oos.writeObject(huffmanCodes);
            oos.writeInt(bitLength);
            byte[] encodedData = bitSet.toByteArray();
            oos.write(encodedData);
            oos.close();
            bos.close();
            fos.close();

            // Statistic: Compression ratio
            double compressionRatio = (double) totalBytes / (double) encodedData.length;
            stats.put("compressionRatio", compressionRatio);

            return stats;
        }

        // Helper method to generate Huffman codes from the tree
        private static Map<Character, String> generateCodes(HuffmanNode root) {
            Map<Character, String> codes = new HashMap<>();
            generateCodes(root, "", codes);
            return codes;
        }

        private static void generateCodes(HuffmanNode node, String code, Map<Character, String> codes) {
            if (node != null) {
                if (node.left == null && node.right == null) {
                    codes.put(node.data, code);
                }
                generateCodes(node.left, code + "0", codes);
                generateCodes(node.right, code + "1", codes);
            }
        }

         // Method to perform decompression and collect statistics

    public static HashMap<String, Object> decompress(String inputFile, String outputFile) throws IOException, ClassNotFoundException {
        HashMap<String, Object> stats = new HashMap<>();
        FileInputStream fis = new FileInputStream(inputFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        // Read and check the magic word
        String mWord = ois.readUTF();
        System.out.println("Decompressing.....");
        if (!"HUFF".equals(mWord)) {
            throw new IllegalArgumentException("Invalid Huffman file format");
        }
        Map<Character, String> huffmanCodes = (Map<Character, String>) ois.readObject();
        Map<String, Character> reverseCodes = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseCodes.put(entry.getValue(), entry.getKey());
        }

        int bitLength = ois.readInt();
        BitSet bits = BitSet.valueOf(ois.readAllBytes());
        ois.close();
        bis.close();
        fis.close();

        FileOutputStream fos = new FileOutputStream(outputFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        StringBuilder currentCode = new StringBuilder();
        int charactersWritten = 0;
        for (int i = 0; i < bitLength; i++) {
            currentCode.append(bits.get(i) ? '1' : '0');
            if (reverseCodes.containsKey(currentCode.toString())) {
                bos.write(reverseCodes.get(currentCode.toString()));
                charactersWritten++;
                currentCode.setLength(0);
            }
        }
        bos.flush();
        bos.close();
        fos.close();

        stats.put("charactersWritten", charactersWritten);
        return stats;
    }
    }


