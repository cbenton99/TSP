import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.TSP.BruteForce;
import algorithms.TSP.GreedyNN;
import algorithms.TSP.ImprovedAlgorithm;
import problem.TSP.TSPInstance;

public class TSPMain {

	public static void main(String[] args) throws Exception {

		////////////////////////////////////////////////////////////////////////
		// this will test all files in TSP folder to make sure they can be read
		// comment out the distance table to save space in console
		// comment out writeDataToFile if matrices are already stored locally
		////////////////////////////////////////////////////////////////////////

		File dir = new File("TSP/");
		File[] directoryListing = dir.listFiles();
		int numberOfFiles = 0;
		
		boolean run = false; // set to false to skip

		if (directoryListing != null && run) {
			for (File child : directoryListing) {
				if (child.getName().charAt(0) != '.') {

					System.out.println("**************************************");
					TSPInstance t = new TSPInstance(child);
					System.out.println("Name: " + t.getName());
					System.out.println("Comment: " + t.getComment());
					System.out.println("Dimension: " + t.getDimension());
					System.out.println("Edge_Weight_Format: " + t.getEdgeWeightFormat());
					System.out.println("Edge_Weight_Type: " + t.getEdgeWeightType());
					System.out.println(t.getDistanceTable());
					writeDataToFile(t);
					System.out.println("**************************************");

					numberOfFiles++;
				}
			}
			
			System.out.println("Total number of files: " + numberOfFiles);
		}

		

		////////////////////////////////////////////////////////////////////////
		// testing individual files
		////////////////////////////////////////////////////////////////////////

		/*
		 * Add selected TSP Problems and their best known path costs
		 */

		HashMap<String, Integer> bestPaths = new HashMap<String, Integer>();
		bestPaths.put("TSP/gr17.tsp", new Integer(2085));
		bestPaths.put("TSP/gr21.tsp", new Integer(2707));
		bestPaths.put("TSP/gr24.tsp", new Integer(1272));
		bestPaths.put("TSP/fri26.tsp", new Integer(937));
		bestPaths.put("TSP/dantzig42.tsp", new Integer(699));
		bestPaths.put("TSP/hk48.tsp", new Integer(11461));
		bestPaths.put("TSP/gr120.tsp", new Integer(6942));
		bestPaths.put("TSP/brg180.tsp", new Integer(1950));
		bestPaths.put("TSP/si535.tsp", new Integer(48450));
		bestPaths.put("TSP/si1032.tsp", new Integer(92650));

		ArrayList<String> tspProblems = new ArrayList<String>();
		tspProblems.add(new String("TSP/gr17.tsp"));
		tspProblems.add(new String("TSP/gr21.tsp"));
		tspProblems.add(new String("TSP/gr24.tsp"));
		tspProblems.add(new String("TSP/fri26.tsp"));
		tspProblems.add(new String("TSP/dantzig42.tsp"));
		tspProblems.add(new String("TSP/hk48.tsp"));
		tspProblems.add(new String("TSP/gr120.tsp"));
		tspProblems.add(new String("TSP/brg180.tsp"));
		// tspProblems.add(new String("TSP/si535.tsp"));
		// tspProblems.add(new String("TSP/si1032.tsp"));

//		for (String s : tspProblems) {
//			timeTSP(s, bestPaths);
//		}

		// brute force test to show running time

		 TSPInstance t = new TSPInstance(new File("TSP/gr17.tsp"));
		 BruteForce bf = new BruteForce(t);

	}

	/*
	 * Solves a TSP instance with the greedy nearest neighbor approach and prints
	 * the path, time to generate a path, and the best known path.
	 */
	public static void timeNN(String tspName, HashMap<String, Integer> times) throws Exception {
		long startTime, endTime, duration;
		double ms;

		TSPInstance tsp = new TSPInstance(new File(tspName));
		System.out.println("Solving TSP instance: " + tspName + "-------------------------------");
		System.out.println("Best known path cost: " + times.get(tspName) + "\n");

		System.out.println("Greedy Nearest Neighbor Algorithm:");
		startTime = System.nanoTime();
		GreedyNN tspa = new GreedyNN(tsp);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		ms = duration / 1000000.0;
		System.out.println("Greedy NN time in ms: " + ms + "\n");

	}
	
	public static void timeIA(String tspName, HashMap<String, Integer> times) throws Exception {
		long startTime, endTime, duration;
		double ms;

		TSPInstance tsp = new TSPInstance(new File(tspName));
		System.out.println("Solving TSP instance: " + tspName + "-------------------------------");
		System.out.println("Best known path cost: " + times.get(tspName) + "\n");

		System.out.println("Improved Algorithm:");
		startTime = System.nanoTime();
		ImprovedAlgorithm ia = new ImprovedAlgorithm(tsp);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		ms = duration / 1000000.0;
		System.out.println("Improved Algorithm time in ms: " + ms + "\n\n");

	}
	

	/*
	 * Writes data from TSP problem for testing and visual purposes
	 */
	private static void writeDataToFile(TSPInstance t) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("Matrices/" + t.getName() + "_matrix.txt");
		out.print(t.getDistanceTable());
		out.close();
	}

}
