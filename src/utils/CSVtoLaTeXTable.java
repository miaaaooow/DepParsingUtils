package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVtoLaTeXTable {
	static final String DELIM_TAB = "\t";
	static final String DELIM_COMMA = ",";
	static final String DELIM_COL = " &\t";
	static final String DELIM_EL = "\\\\ \n";
	static final String DELIM_LINE = "\\hline \n";

	static final String BEGIN_TABLE = "\\begin{table}[!htb] \n \\centering\n \\begin{tabular}{";
	static final String END_TABLE = " \\end{tabular}\n \\label{" ;
	static final String END_TABLE2 = "} \n\\end{table}";
	public static void transferToLaTeXTable(String input, String output, String tableName, boolean horizontalLines) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(input)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));
			String line = reader.readLine();
			String [] firstLine = line.split(DELIM_TAB);
			int width = firstLine.length;
			
			StringBuilder lcr = new StringBuilder();
			for (int i = 0; i < width; i++) {
				lcr.append("|l");
			}
			lcr.append("|}\n");
			
			writer.write(BEGIN_TABLE);
			writer.write(lcr.toString());
			if (horizontalLines) {
				writer.write(DELIM_LINE);
			}
			while (line != null && line != "") {
				firstLine = line.split(DELIM_TAB);
				width = firstLine.length;
				StringBuilder lin = new StringBuilder();
				for (int i = 0 ; i < width - 1; i++) {
					lin.append(firstLine[i].trim());
					lin.append(DELIM_COL);					
				}
				lin.append(firstLine[width - 1]);
				if (width > 1 && horizontalLines) {
					lin.append(DELIM_EL);
					lin.append(DELIM_LINE);
				}
				writer.write(lin.toString());
				line = reader.readLine();
				// System.out.println("LINE:" + line);
			}
			writer.write(END_TABLE);
			writer.write(tableName);
			writer.write(END_TABLE2);
			
			reader.close();
			writer.close();
		} catch (IOException e) {
			System.err.println("I/O procedure failed!");
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		String input = "resources/test.csv";
		String output = "resources/testout.tex";
		transferToLaTeXTable(input, output, "tab:1", true);
	}
}
