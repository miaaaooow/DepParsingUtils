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

	public static void gather(String inputname, boolean labeled) {
		int ROUND = 8;
	
		System.out.println(inputname);
		String mstEvalFile = inputname + ".txt";
		String res = inputname + ".csv";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							mstEvalFile))));
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							new File(res))));

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
					// bw.write(line.replaceAll("Tokens: ", ""));
					// bw.write("\t");
				} else if (line.startsWith("Correct: ")) {
					bw.write(line.replaceAll("Correct: ", ""));
					bw.write("\t");
				} else if (line.startsWith("Unlabeled Accuracy: ")) {
					String ulabString = line.replaceAll(
							"Unlabeled Accuracy: ", "");
					double ulab = Double.parseDouble(ulabString);
					if (ulabString.length() > ROUND) {
						ulabString = ulabString.substring(0, ROUND);
					}
					bw.write(ulabString);
					if (maxUlab < ulab) {
						maxUlab = ulab;
					}
					bw.write("\t");
				} else if (line.startsWith("Unlabeled Complete Correct: ")) {
					String toWrite = line.replaceAll("Unlabeled Complete Correct: ", "");
					if (toWrite.length() > ROUND) {
						toWrite = toWrite.substring(0, ROUND);
					}
					bw.write(toWrite);
					bw.write("\t");
					// bw.write("\n");
				} else if (labeled && line.startsWith("Labeled Accuracy: ")) {
					String labString = line.replaceAll(
							"Labeled Accuracy: ", "");
					double lab = Double.parseDouble(labString);
					if (labString.length() > ROUND) {
						labString = labString.substring(0, ROUND);
					}
					bw.write(labString);
					if (maxLab < lab) {
						maxLab = lab;
					}
					bw.write("\t");
				} else if (labeled && line.startsWith("Labeled Complete Correct: ")) {
					String toWrite = line.replaceAll("Labeled Complete Correct: ", "");
					if (toWrite.length() > ROUND) {
						toWrite = toWrite.substring(0, ROUND);
					}
					bw.write(toWrite);
					bw.write("\n");

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
	public static void gatherML2() {
		String[] ws = { "AVG", "ACC", "EQL" };
		String[] ml = { "Min", "Max", "Mult" };
		
		for (String w : ws) {
			for (String m : ml) {
				String name = "resources/Voting_" + m + "_" + w
						+ "_Weights_all_of14";
				gather(name, true);
			}
		}
	}
	public static void gatherML1() {
		gather("resources/Voting_ALL_EQL_Weights_all_of14", true);
		gather("resources/Voting_ALL_ACC_Weights_all_of14", true);
		gather("resources/Voting_ALL_AVG_Weights_all_of14", true);
	}
	
	public static void main(String[] args) {
		gatherML1();
	}
	


}
