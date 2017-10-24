package SimulatedAnnealing;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	
	private int cost;
	private int iterations;
	private double temperature;
	private List<Integer> solution;

	public Solution(List<Integer> solution, int cost, int iterations, double temperature) {
		
		 this.solution = solution;
		 this.cost = cost;
		 this.iterations = iterations;
		 this.temperature = temperature;
		 
	}
	
	public List<Integer> getSolution() {
		return solution;
		
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getIterations() {
		return iterations;
	}
	
	public double getTemperature() {
		return temperature;
	}
}
