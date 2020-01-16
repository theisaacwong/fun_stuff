package JesusGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class JesusGame {
	
	public static final String START = "start";
	
	public static void main(String[] args) throws IOException {
		
//		System.out.println("Java version: " + System.getProperty("java.version"));
//		System.out.println("Heap Size: " + getHeapSize());
		
		args = new String[] {"--start https://en.wikipedia.org/wiki/Tea"};
		
		String start = "https://en.wikipedia.org/wiki/Tea";
		String end = "https://en.wikipedia.org/wiki/Jesus";
		int maxDepth = 7;
		
		if(args.length==0 || args[0].contains("-help") || args[0].contains("-h")) {
			print("Usage: java -jar JesusGame [OPTIONs]");
			print("From a starting webpage, find a series of links to  reach a destination webpage");
			print("Usage example: ");
			print("java -jar JesusGame --start https://en.wikipedia.org/wiki/DNA --end https://en.wikipedia.org/wiki/Tea --depth 10");
			print("\nMandatory arguments:");
			print("-s,\t--start\t\tstarting URL\n");
			print("Options:");
			print("-e,\t--end\t\tending URL to reach, default: https://en.wikipedia.org/wiki/Jesus");
			print("-d,\t--depth\t\tmaximum depth to search, default: 7");
			return;
		} 
		
		for(int i = 0; i < args.length; i+=2) {
			if(args[i].equals("-s") || args[i].equals("--start")) {
				start = args[i+1];
			} else if(args[i].equals("-e") || args[i].equals("--end")) {
				end = args[i+1];
			} else if(args[i].equals("-d") || args[i].equals("--depth")) {
				maxDepth = Integer.parseInt(args[i+1]);
			}
		}
		
		bfs(start, end, maxDepth);
		System.out.println(end);
		
	}
	
	public static void bfs(String start, String end, int maxDepth) throws IOException {
		if(start.equalsIgnoreCase(end)) {System.out.println("Start equals end"); return;}
		
		HashMap<String, String> traversalPath = new HashMap<>(); // child to parent
		HashMap<String, Integer> depth = new HashMap<>();
		
		traversalPath.put(start, START);
		depth.put(start, 0);
		
		ArrayList<String> frontier = new ArrayList<>();
		ArrayList<String> explored = new ArrayList<>();
		
		frontier.add(start);
		
		while(true) {
			if(frontier.isEmpty()) {
				System.out.println("failure, goal URL unreachable");
				return;
			}
			
			String parent = frontier.remove(0);
			explored.add(parent); explored.add("\"https://en.wikipedia.org/wiki/Wikipedia:Featured_articles\"");
			ArrayList<String> children = getLinks(parent);
			
			int currDepth = depth.get(parent);
			if(currDepth > maxDepth) {
				System.out.println("Max depth reached, no solution found");
				return;
			}
			
			for(String child : children) {
				
				if(explored.contains(child) == false && frontier.contains(child) == false) {
					traversalPath.put(child, parent);
					depth.put(child, depth.get(parent) + 1);
					if(child.equalsIgnoreCase(end)) {
						print("");
						print("Solution found:");
						printSolution(traversalPath, end, new ArrayList<String>());
						return;
					}
					frontier.add(child);
				}
			}
		}
	}
	
	public static void print(String str) {
		System.out.println(str);
	}
	
	public static ArrayList<String> getLinks(String url) throws IOException{
		ArrayList<String> rval = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(url).ignoreContentType(true).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String str = link.attr("abs:href");
				if(!str.contains("#") && !str.contains(".jpg") && !str.contains("File:"))
					rval.add(str);
			}
		} catch(Exception e) {
			return rval;
		}

		System.out.print("\r" + url + "   "); 
		return rval;
	}
	
	public static void printSolution(HashMap<String, String> traversalPath, String node, ArrayList<String> output) {
		if(traversalPath.get(node).equalsIgnoreCase(START)) {
			for(int i = output.size()-1; i >= 0; i--) {
				System.out.println(output.get(i));
			}
			return;
		} else {
			output.add(traversalPath.get(node));
			printSolution(traversalPath, traversalPath.get(node), output);
		}
	}
	
	/**
	 * @return returns the current JVM heap size in human readable format
	 */
	public static String getHeapSize() {
		return humanReadableByteCount(Runtime.getRuntime().totalMemory());
	}
	
	/**
	 * @param bytes
	 * @return human readable version of bytes
	 */
	public static String humanReadableByteCount(long bytes) {
		if (bytes < 1024) return bytes + " B";
		int exp = (int) (Math.log(bytes) / 6.907755278982137);
		char pre = "KMGTPE".charAt(exp-1);
		return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
	}

	
	
}
