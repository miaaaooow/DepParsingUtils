package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class CountCombinedParsers {

	public static void countParsersFromFile(String input, String output) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					input)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					output)));
			HashMap<String, Integer> map = new HashMap<String, Integer>(100);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parsers = line.split(" ");
				for (String p : parsers) {
					if (map.containsKey(p)) {
						int curr = map.get(p);
						map.put(p, curr + 1);
					} else {
						map.put(p, 1);
					}
				}
			}
			Set<Entry<String, Integer>> set = map.entrySet();
			List<Entry<String, Integer>> l = new ArrayList<Entry<String, Integer>>();
			for (Entry<String, Integer> e : set) {
				l.add(e);
			}
			Collections.sort(l, comp);
			for (Entry<String, Integer> e : l) {
				System.out.println(e.toString());
				writer.write(e.toString());
				writer.write("\n");
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			System.err.println("I/O error! ");
			e.printStackTrace();
		}

	}

	public static Comparator<Entry<String, Integer>> comp = new Comparator<Entry<String, Integer>>() {
		public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
			return e2.getValue().compareTo(e1.getValue());
		}
	};

	public static void main(String[] args) {
		countParsersFromFile("resources/__eql.txt", "___eql.txt");
		countParsersFromFile("resources/__acc.txt", "___acc.txt");
		countParsersFromFile("resources/__avg.txt", "___avg.txt");
	}

}
