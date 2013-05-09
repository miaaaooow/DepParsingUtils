package utils.deprel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Stat {

	private HashMap<String, Integer> POSPOScount;
	private HashMap<String, Integer> POSPOSRelCount;
	
	public Stat() {
		POSPOScount = new HashMap<String, Integer>();
		POSPOSRelCount = new HashMap<String, Integer>();
	}
	
	public void addSentence(DepRelSentence sent) {
		add(sent.getPOSPOS(), POSPOScount);
		add(sent.getPOSPOSRel(), POSPOSRelCount);		
	}
	
	private static void add(List<String> pospos, HashMap<String, Integer> map) {
		for (String pp : pospos) {
			if (map.containsKey(pp)) {
				map.put(pp, map.get(pp).intValue() + 1);
			} else {
				map.put(pp, new Integer(1));
			}
		}
	}
	public void getStat() {
		getPOSStat(POSPOScount);
		getPOSStat(POSPOSRelCount);
	}
	
	private void getPOSStat(Map<String, Integer> map) {
		Set<Entry<String, Integer>> set = map.entrySet();
		List<Entry<String, Integer>> l = new ArrayList<Entry<String, Integer>>();
		for (Entry<String, Integer> e : set) {
			l.add(e);
		}
		Collections.sort(l, comp);
		for (Entry<String, Integer> e : l) {
			System.out.println(e.toString());
		}
	}
	
	public static Comparator<Entry<String, Integer>> comp = new Comparator<Entry<String,Integer>>()  {
		public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
			return e2.getValue().compareTo(e1.getValue());
		}
	};
}
