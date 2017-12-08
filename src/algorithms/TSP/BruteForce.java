package algorithms.TSP;
import java.util.Arrays;

import problem.TSP.TSPInstance;

public class BruteForce {
	
	long startTime;
	long endTime;
	long deltaTime;
	long totalTime;

	private TSPInstance tsp;
	private int[] solutionPath;
	private int[] possiblePath;
	private double solutionDistance;
	private int permutation = 0;

	public BruteForce(TSPInstance t) {
		this.tsp = t;
		solutionPath = new int[this.tsp.getDimension()];
		possiblePath = Arrays.copyOf(tsp.getDistanceTable().listVertices(), tsp.getDimension());
		solutionDistance = Integer.MAX_VALUE;
		solve();
		System.out.println("solution distance is " + solutionDistance);
		print(solutionPath);
	}

	public void solve() {
		startTime = System.currentTimeMillis();
		perm(1);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
//		System.out.println("time: " + totalTime + " milliseconds");
	}

	private void perm(int index) {

		if (index >= possiblePath.length - 1) {
			double tempDist = findDistance(possiblePath);
			++permutation;
//			System.out.println("Permutation " + (++permutation));
//			System.out.println("Total distance is " + tempDist);
//			print(possiblePath);
			
//			if (permutation % 1000000 == 0) {
//				long tempTime = System.currentTimeMillis();
//				deltaTime = (tempTime - startTime) / 1000;
//				System.out.println(permutation + " permutations in " + deltaTime + "seconds");
//				System.out.println("Total distance is " + solutionDistance);
//				print(solutionPath);
//			}
			
			

			if (tempDist < solutionDistance) {
				solutionDistance = tempDist;
				solutionPath = Arrays.copyOf(possiblePath, possiblePath.length);
			}

			return;
		}

		for (int index2 = index; index2 < possiblePath.length; index2++) {
			swap(index2, index);
			perm(index + 1);
			swap(index2, index);
		}
	}

	private void swap(int a, int b) {
		int temp = possiblePath[a];
		possiblePath[a] = possiblePath[b];
		possiblePath[b] = temp;

	}

	private double findDistance(int[] path) {
		double total = 0;
		for (int index = 0; index < path.length-1; index++) {
			total += tsp.getDistanceTable().getDistanceBetween(path[index], path[index + 1]);
		}

		total += tsp.getDistanceTable().getDistanceBetween(path[path.length-1], 1);
		return total;
	}

	private void print(int[] arr) {
		double sum = 0;
		
		System.out.println(Arrays.toString(arr));
		for (int index = 0; index < solutionPath.length-1; index++) {
			sum += tsp.getDistanceTable().getDistanceBetween(solutionPath[index], solutionPath[index + 1]);
			
			System.out.print(solutionPath[index] + " -> " + solutionPath[index + 1] + " : ");
			System.out.println(tsp.getDistanceTable().getDistanceBetween(solutionPath[index], solutionPath[index + 1]));
		}
		sum += tsp.getDistanceTable().getDistanceBetween(solutionPath[solutionPath.length-1], 1);
		System.out.print(solutionPath[solutionPath.length-1] + " -> " + 1 + " : ");
		System.out.println(tsp.getDistanceTable().getDistanceBetween(solutionPath[solutionPath.length-1], 1));
		System.out.println("real sum: " + sum);
	}

}
