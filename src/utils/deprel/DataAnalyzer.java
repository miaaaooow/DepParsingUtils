package utils.deprel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataAnalyzer {
	private final static String FILENAME = "resources/BTB-ALL-MST.mst";
	private final static int CYCLE = 5;
	private final static int POS = 1; // POS TAGS
	private final static int REL = 2; // REL TAGS
	private final static int DEP_TREE = 3; // DEPENDENCIES
	
	public static void main(String[] args) {
		try {
			File data = new File(FILENAME); 
			BufferedReader br = new BufferedReader(new FileReader(data));
			String line = null;
			int counter = 0;
			String POSLine = "";
			String relations = "";
			String depRelLine = "";
			Stat stat = new Stat();
			while ((line = br.readLine()) != null) {
				if (counter % CYCLE == POS) {
					POSLine = line;
					//System.out.println(POSLine + "POS");
				} else if (counter %CYCLE == REL)  {
					relations = line;
					//System.out.println(relations + "REL");
				} else if (counter % CYCLE == DEP_TREE) {
					depRelLine = line;
					//System.out.println(depRelLine + "DEP_REL");
					DepRelSentence sentenceEntity = new DepRelSentence(POSLine, 
							 depRelLine, relations);
					stat.addSentence(sentenceEntity);
				} 
				counter++;
			}
			stat.getStat();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}

}
