package utils.deprel;

import java.util.LinkedList;
import java.util.List;

public class DepRelSentence {
	public static String SEPARATOR = "###";
	public static String POS_DELIMITER = "->";
	private String [] pos;
	private String [] dependencyTree;
	private String [] relations;
	
	public DepRelSentence (String POS, String dependency, String relations) {
		this.pos = POS.split("\\s");
		this.relations = relations.split("\\s");
		this.dependencyTree = dependency.split("\\s");
	}
	
	public String getPOSAsWord() {
		return arrayToString(pos);
	}
	
	public String getDependency() {
		return arrayToString(dependencyTree);
	}
	
	public String getRepr() {
		return getPOSAsWord() + SEPARATOR + getDependency();
	}
	
	public List<String> getPOSPOS() {
		List<String> res = new LinkedList<String>();
		for (int i = 0; i < pos.length; i++) {
			String POS = pos[i];
			int toPOS = Integer.parseInt(dependencyTree[i]);
			String startOfEdgePOS = "ROOT";
			if (toPOS != 0) { // ROOT
				startOfEdgePOS = pos[toPOS - 1];
			}
			String POSPOS = startOfEdgePOS + POS_DELIMITER + POS;
			res.add(POSPOS);
		}
		return res;
	}
	
	public List<String> getPOSPOSRel() {
		List<String> res = new LinkedList<String>();
		for (int i = 0; i < pos.length; i++) {
			String POS = pos[i];
			int toPOS = Integer.parseInt(dependencyTree[i]);
			String startOfEdgePOS = "ROOT";
			if (toPOS != 0) { // ROOT
				startOfEdgePOS = pos[toPOS - 1];
			}
			String rel = relations[i];
			String POSPOS = startOfEdgePOS + POS_DELIMITER + POS + SEPARATOR + rel;
			System.out.println(POSPOS);
			res.add(POSPOS);
		}
		return res;
	}
	
	
	private static String arrayToString(String [] a) {
		StringBuilder result = new StringBuilder();
		if (a != null) {
			for (String word : a) {
				if (word != null && word.length() > 0) {
					result.append(word);
					result.append(POS_DELIMITER);
				}
			}
		}
		return result.toString();
	}
	
}
