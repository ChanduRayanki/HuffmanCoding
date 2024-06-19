import java.io.*;
import java.util.*;

public class HuffmanCodingMain {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java HuffmanCodingMain <compress/decompress> -f <input file> [-o <output file>] [-s]");
            return;
        }

        String operation = args[0];
        String inputFile = null;
        String outputFile = null;
        boolean printStats = false;
        // Handling command-line arguments
        for (int i = 1; i < args.length; i++) {
            if ("-f".equals(args[i]) && i + 1 < args.length) {
                inputFile = args[++i];
            } else if ("-o".equals(args[i]) && i + 1 < args.length) {
                outputFile = args[++i];
            } else if ("-s".equals(args[i])) {
                printStats = true;
            }
        }

        if (inputFile == null) {
            System.out.println("Input file is required.");
            return;
        }
        // creating the output file in required format
        if (outputFile == null) {
            if ("compress".equals(operation)) {
                outputFile = inputFile + ".hzip";
            } else if ("decompress".equals(operation)) {
                outputFile = inputFile.replaceAll("\\.hzip$", ".txt");
            }
        }
                // calling compress/decompress and printing statistics
            try {
                long startTime = System.currentTimeMillis();
                if ("compress".equals(operation)) {
                    HashMap<String, Object> stats = HuffmanCoding.compress(inputFile, outputFile);
                    long endTime = System.currentTimeMillis();
                    if (printStats) {
                        System.out.println("Compression Statistics:");
                        System.out.println("Time Taken: " + (endTime - startTime) + " ms");
                        System.out.println("Distinct Characters: " + stats.get("distinctCharacters"));
                        System.out.println("Compression Ratio: " + String.format("%.2f", stats.get("compressionRatio")));
                    }
                } else if ("decompress".equals(operation)) {
                    try{
                        HashMap<String, Object> stats = HuffmanCoding.decompress(inputFile, outputFile);
                        long endTime = System.currentTimeMillis();
                        if (printStats) {
                            System.out.println("Decompression Statistics:");
                            System.out.println("Time Taken: " + (endTime - startTime) + " ms");
                            System.out.println("Characters Written: " + stats.get("charactersWritten"));
                        }
                    } catch(IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                        return;
                    }
                } else {
                    System.out.println("Invalid operation specified.");
                    return;
                }

                System.out.println(operation.substring(0, 1).toUpperCase() + operation.substring(1) + "ion completed successfully.");
            } catch (Exception e) {
                System.err.println("Error during " + operation + "ion: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }