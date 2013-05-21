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
		String mstEvalFile = "voting_unlabeled_avg_acc_2,3,5,7_14.txt";
		String res = "resultEval_ulab_avg_acc.csv";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(mstEvalFile))));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(res))));
			
			double maxUlab = 0.0;
			double maxLab = 0.0;
			
			String line = "";
			boolean parsers = false;
			while ((line = br.readLine()) != null) {
				if (parsers) {
					bw.write(line);
					bw.write("\t");
					parsers = false;
				}
				if (line.startsWith("Parsers chosen for voting: ")) {
					parsers = true;
				}
				if (line.startsWith("Tokens: ")) {
					//bw.write(line.replaceAll("Tokens: ", ""));
					//bw.write("\t");
				} else if (line.startsWith("Correct: ")) {
					//bw.write(line.replaceAll("Correct: ", ""));
					//bw.write("\t");	
				} else if (line.startsWith("Unlabeled Accuracy: ")) {
					String ulabString = line.replaceAll("Unlabeled Accuracy: ", "");
					double ulab = Double.parseDouble(ulabString);
					bw.write(ulabString);
					if (maxUlab < ulab) {
						maxUlab = ulab;
					}
					bw.write("\t");
				} else if (line.startsWith("Unlabeled Complete Correct: ")) {
					bw.write(line.replaceAll("Unlabeled Complete Correct: ", ""));
				//	bw.write("\t");
					bw.write("\n");
				} else if (line.startsWith("Labeled Accuracy: ")) {
					String labString = line.replaceAll("Labeled Accuracy: ", "");
					double lab = Double.parseDouble(labString);
				//	bw.write(labString);
					if (maxLab < lab) {
						maxLab = lab;
					}
				//	bw.write("\t");
				} else if (line.startsWith("Labeled Complete Correct: ")) {
					//bw.write(line.replaceAll("Labeled Complete Correct: ", ""));
					//bw.write("\n");
					
				}
			}
			System.out.println(maxUlab);
			System.out.println(maxLab);			
			bw.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
