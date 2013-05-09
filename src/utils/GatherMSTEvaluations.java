package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GatherMSTEvaluations {

	public static void main(String[] args) {
		String mstEvalFile = "mst-ord01-iter15-retest.txt";
		String res = "resultEval1.csv";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(mstEvalFile))));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(res))));

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("Tokens: ")) {
					bw.write(line.replaceAll("Tokens: ", ""));
					bw.write("\t");
				}
				if (line.startsWith("Correct: ")) {
					bw.write(line.replaceAll("Correct: ", ""));
					bw.write("\t");	
				}
				if (line.startsWith("Unlabeled Accuracy: ")) {
					bw.write(line.replaceAll("Unlabeled Accuracy: ", ""));
					bw.write("\t");
				}
				if (line.startsWith("Unlabeled Complete Correct: ")) {
					bw.write(line.replaceAll("Unlabeled Complete Correct: ", ""));
					bw.write("\t");
				}
				if (line.startsWith("Labeled Accuracy: ")) {
					bw.write(line.replaceAll("Labeled Accuracy: ", ""));
					bw.write("\t");
				}
				if (line.startsWith("Labeled Complete Correct: ")) {
					bw.write(line.replaceAll("Labeled Complete Correct: ", ""));
					bw.write("\n");
				}
			}
			bw.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
