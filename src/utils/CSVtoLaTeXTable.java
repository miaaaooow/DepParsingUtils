package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVtoLaTeXTable {

	public static void transferToLaTeX(String input, String output) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					input)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));
			
			writer.write(reader.readLine());
			
		} catch (IOException e) {
			System.err.println("I/O procedure failed!");
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		String input = "resources/test.csv";
		String output = "resources/testout.csv";
		transferToLaTeX(input, output);
	}
}
