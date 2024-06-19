#Huffman Coding Project

This project implements Huffman coding for file compression and decompression. It includes Java code for managing Huffman trees, encoding and decoding data, and command-line scripts to facilitate easy operation.

## FILE STRUCTURE:

Huffman
│
├── src
│   ├── HuffmanCoding.java
│   └── HuffmanCodingMain.java
│
├── compress.sh
├── decompress.sh
├── Readme.txt
├── Makefile
└── Proof_of_working.jpg



## COMPILATION:

Navigate to the directory where the "Make file" is located. Open the terminal in that location and run the following command.


make all

or 

mingwxx-make all 			( if using MinGw. xx is the either 32 or 64)



A new directory will be created with the name "bin" where all the compiled class files are placed.



## RUNNING THE CODE:

compression : sh compress.sh -f myfile.txt [-o myfile.hzip -s] 			#### Do not include "[ ]" in the command.

decompression : sh decompress.sh -f myfile.hzip [-o myfile.txt -s]

for decompression, if output file name is not given the output file will be stored as myfile.hzip.txt. This is to avoid overwrite of original text file.


## CONTRIBUTIONS :

Group members : Kavya Kutumbaka, Chandrashekar Rayanki

1) Chandrashekar Rayanki: Was primarily responsible for developing the compression logic within the Java application. This included designing and implementing the Huffman tree construction and encoding functionalities. I also contributed to initial debugging of the compression features. Created the compress.sh

2) Kavya Kutumbaka : Focused on the decompression logic. Implemented the logic to validate the magic word during decompression, ensuring file integrity and correct processing. Additionally,worked on creating decompress.sh script. Also contributed in testing and debugging of decompression code.

We both worked on integrating compress and decompression logics and also built Main class to be able to handle function calls, input, output and also to handle command line operations.
Final debugging was contributed by both of us.


