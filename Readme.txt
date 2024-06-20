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

by :

  Chandrashekar Rayanki @ChanduRayanki
  Kavya Kutumbaka @Kavya7628


