package utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BTB2DepTrees {

	static String mstFile = "BTB-01-Test.mst";
	static String dep = "BTB-01-Test-tikz";
	static String prelude = "\\begin{dependency}\n\\begin{deptext}\n";
	static String intermezzo = "\\end{deptext}\n";
	static String culminata = "\\end{dependency}\n\n\\\\\n";

	public static void main(String[] args) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(mstFile))));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(dep))));
			int lineCount = 0;
			String[][] lines = new String[4][];
			String line = "";
			while ((line = br.readLine()) != null) {
				if (lineCount == 4) {
					lineCount = 0;
					bw.write(prelude);
					bw.write(makeSentence(lines[0]));
					bw.write(intermezzo);
					String[] edges = makeEdges(lines[3], lines[2]);
					for (String e : edges) {
						bw.write(e);
						bw.write("\n");
					}
					bw.write(culminata);
					continue;
				} else {
					lines[lineCount] = line.split("\t");
					lineCount += 1;
				}
			}
			bw.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String makeSentence(String[] sent) {
		String res = sent[0];
		for (int i = 1; i < sent.length; i++) {
			res += " \\& ";
			if (sent[i].equals("$")) {
				res += "\\";
			}
			res += sent[i];
		}
		return res + " \\\\\n";
	}

	private static String[] makeEdges(String[] froms, String[] labels) {
		String[] edges = new String[froms.length - 1];
		int ec = 0;
		for (int i = 0; i < froms.length; i++) {
			int start = Integer.parseInt(froms[i]);
			if (start != 0) {
				int end = i + 1;
				edges[ec] = new String("\\depedge{" + start + "}{" + end
						+ "}{" + labels[i] + "}");
				ec += 1;
			}
		}
		return edges;
	}
}
