#!/bin/bash

# Directory setup
SRC_DIR="src"
BIN_DIR="bin"

# Initialize variables
input_file=""
output_file=""
stats_flag=""

# Ensure bin directory exists 
mkdir -p "$BIN_DIR"

# Parse command-line options
while getopts "f:o:s" opt; do
  case $opt in
    f) input_file="$OPTARG";;
    o) output_file="$OPTARG";;
    s) stats_flag="-s";;
    \?) echo "Usage: $0 -f <input_file> [-o <output_file>] [-s]" >&2; exit 1;;
  esac
done

# Check if input file was given
if [ -z "$input_file" ]; then
  echo "Usage: $0 -f <input_file> [-o <output_file>] [-s]"
  exit 1
fi

# Set default output file if not provided
if [ -z "$output_file" ]; then
  output_file="${input_file%.*}.hzip"
fi

# Compile the Java source code
#javac -d "$BIN_DIR" "$SRC_DIR"/*.java

# Run the Huffman coding compression
java -cp "$BIN_DIR" HuffmanCodingMain compress -f "$input_file" -o "$output_file" $stats_flag
