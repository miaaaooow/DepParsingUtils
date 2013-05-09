package org.bultreebank.expriments;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Evaluator {
	private final static int HEAD_INDEX = 5;
	private final static int DEP_TYPE_INDEX = 6; 
	
	/**
	 * An method to measure the coverage(accuracy)
	 * between two parsers.
	 * @param parserDataA
	 * @param parserDataB
	 */
	public static void evaluateTwoParsers(File parserDataA, File parserDataB) {
		  BufferedReader brA = getReader(parserDataA);
		  BufferedReader brB = getReader(parserDataB);
		  String strLineA;
		  String strLineB;
		  
		  int sentencesCounter = 1;
		  int partialMatches = 0;
		  int completeMatches = 0;
		  int wordCounter = 0;
		  
		  try {
		  while ((strLineA = brA.readLine()) != null) {
			  strLineB = brB.readLine();
			  if (strLineB == null) {
				  System.err.println("Incompatible files.");
			  }
			  String [] relationA = strLineA.split(" ");
			  String [] relationB = strLineB.split(" ");
			  if (relationA.length == 0) {
				  sentencesCounter += 1; 
				  continue;
			  } 
			  wordCounter = 0;
			  if (relationA.length < 6 || relationB.length < 6) {
				  System.err.println("Corrupted data");
			  }
 			  if (relationA[HEAD_INDEX].equals(relationB[HEAD_INDEX])) {
 				  partialMatches += 1;
 				  if (relationA[DEP_TYPE_INDEX].equals(relationB[DEP_TYPE_INDEX])) {
 					  completeMatches += 1;
 				  }
 			  }
		  }
		  } catch (IOException e) {
			  e.printStackTrace();
		  } finally {
			  try {
				  brA.close();
				  brB.close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  double partAcc = (double) partialMatches / (double) wordCounter;
		  double complAcc = (double) completeMatches / (double) wordCounter;
		  System.out.println("Evaluation on " + wordCounter 
				  			 + " in " + sentencesCounter + "sentences");
		  System.out.println("Equal(partially): " + partAcc);
		  System.out.println("Equal(completely): " + complAcc);
	}
	
	
	private static BufferedReader getReader(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new DataInputStream(new FileInputStream(file))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}
}
