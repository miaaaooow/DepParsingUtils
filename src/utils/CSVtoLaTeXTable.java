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

	static final String BEGIN_TABLE = "\\begin{table}[!htb] \n";
	static final String BEGIN_TABULAR ="  \\begin{tabular}{";
	static final String END_TABLE = " \\end{tabular}\n " ;
	static final String END_TABLE2 = " \\end{table}";
	
	static final String STARTING_LINE = "Комбинация & Брой  & Точност без  & Точност цели  & Точност със  & Точност цели  \\\\\n" +
			" & верни	& етикети & изречения & етикети & изречения \\\\\n" +
			" & дъги	& 	& без етикети & 	& със етикети \\\\\n";
	public static void transferToLaTeXTable(String input, String output, String tableName, String caption, boolean horizontalLines) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(input)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output)));
			String line = reader.readLine();
			String [] firstLine = line.split(DELIM_TAB);
			int width = firstLine.length;
			
			writer.write(BEGIN_TABLE);

			writer.write(BEGIN_TABULAR);
			
			StringBuilder lcr = new StringBuilder();
			for (int i = 0; i < width; i++) {
				lcr.append("|l");
			}
			lcr.append("|}\n");
			
			writer.write(lcr.toString());
			writer.write(DELIM_LINE);
		//	writer.write(STARTING_LINE);
			
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
			String label = "\\label{" + tableName + "}\n";
			String cation = "\\caption{" + caption + "}\n";
			
			writer.write(END_TABLE);
			writer.write(label);
			writer.write(cation);
			
			writer.write(END_TABLE2);
			
			reader.close();
			writer.close();
		} catch (IOException e) {
			System.err.println("I/O procedure failed!");
			e.printStackTrace();
		}

	}
	
	public static void makeTablesLabeledEdges() {
		String [] a = {"max", "min", "mult"};
		String [] b = {"acc", "avg", "eql"};
		for (String mmm : a) {
			for (String aae : b) {
				String name = mmm + "_" + aae;
				String input = "resources/tab_" + name + ".csv";
				String output = "resources/tab_" + name + ".tex";
				transferToLaTeXTable(input, output, "tab:lab_best_" + name, 
						"Най-добрите 15 комбинации, спрямо точността без етикети, за \\textbf{МТ=" + aae + ", МС=" 
				+ mmm + "}" , true);
			}
		}
	}
	
	public static void makeTablesBestAAE() {
		String[] b = { "acc", "avg", "eql" };
		for (String name : b) {
			String input = "resources/" + name + ".best.csv";
			String output = "resources/tab_" + name + "_best.tex";
			transferToLaTeXTable(input, output, "tab:lab_" + name,
					"Най-добри комбинации за метрика \\textbf{"+ name + "}", true);
		}
	}
	public static void main(String[] args) {
		String input = "resources/__fun.csv";
			String output = "resources/test.tex";
			transferToLaTeXTable(input, output, "tab:mainpostags",
					"Основни категории части на речта", true);
		

	}
}
