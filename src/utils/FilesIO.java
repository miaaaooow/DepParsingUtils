package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class FilesIO {
	public static void mergeFiles(String file1, String file2, String output) {
		File f1 = new File(file1);
		File f2 = new File(file2);
		File[] files = { f1, f2 };
		File fOut = new File(output);
		Writer writer;
		try {
			Scanner sc = null;
			writer = new FileWriter(fOut);
			for (File f : files) {
				sc = new Scanner(f);
				while (sc.hasNext()) {
					String line = sc.next();
					writer.write(line);
				}
			}
			sc.close();
			writer.close();
		} catch (IOException ioe) {
			System.err.println("File not found!");
		}
	}
	
	public static void splitFileIntoPieces(String filename, String suffix, int pieces) {
		File file = new File(filename + "." + suffix);
		try {
			Scanner sc = new Scanner(file);
			int lineCounter = 0;
			while (sc.hasNext()) {
				lineCounter += 1;
				sc.next();
			}
			int approximateCount = lineCounter / pieces;
			if (approximateCount < 4) {
				System.err.println("Too small file for " + pieces + " pieces.");
			}
			
			for (int i = 0; i < pieces; i++) {
				
			}
		
		} catch (IOException ioe) {
			
		}
	}
}
