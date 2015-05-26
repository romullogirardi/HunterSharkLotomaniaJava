package com.romullogirardi.huntshark.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import com.romullogirardi.huntshark.model.ContestManager.State;

public class Main {
	
	//Collection which stores the 10 best GameStrategies
	public static ArrayList<GameStrategy> bestGameStrategies = new ArrayList<GameStrategy>();
	
	public static void main(String[] args) throws IOException {
		
		//Executing iterations in intervals to discover the best 10 gameStrategies
//		int interval = 1000;
//		int index = 1;
//		long numberOfIterations = 0;
//		do {
//			if(numberOfIterations != 0) {
//				System.out.println("Computando iteração " + index + "/" + numberOfIterations + ", de " + (index - 1) * interval + " a " + index * interval);
//			}
//			ContestManager.newInstance(); 
//			ContestManager.getInstance().initializeGameStrategiesByCombinationsGenerator((index - 1) * interval, index * interval);
//			ContestManager.getInstance().populateContests();
//			System.out.println("RANKING DE ESTRATÉGIAS APÓS A " + index + "ª ITERAÇÃO:");
//			for(GameStrategy gameStrategy : bestGameStrategies) {
//				System.out.println(gameStrategy.toString());
//			}
//			System.out.println();
//			index++;
//			if(numberOfIterations == 0) {
//				numberOfIterations = (Math.abs(ContestManager.getInstance().getCombinationsGenerator().c(ContestManager.N, ContestManager.K) / interval)) + 1;
//			}
//		} while (index <= numberOfIterations);
		
		//Executing a specific group of gameStrategies
		int[] indexes1 = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 25, 26, 27, 28, 29, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 80, 81, 82, 83, 84, 95, 96, 97, 98, 99};
		int[] indexes2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 16, 17, 18, 19, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 70, 71, 72, 73, 74};
		int[] indexes3 = {0, 1, 2, 3, 4, 10, 11, 12, 13, 14, 25, 26, 27, 28, 29, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89};
		int[] indexes4 = {5, 6, 7, 8, 9, 15, 16, 17, 18, 19, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 60, 61, 62, 63, 64, 70, 71, 72, 73, 74, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
		Vector<GameStrategy> gameStrategies = new Vector<GameStrategy>();
		gameStrategies.add(new GameStrategy(indexes1));
		gameStrategies.add(new GameStrategy(indexes2));
		gameStrategies.add(new GameStrategy(indexes3));
		gameStrategies.add(new GameStrategy(indexes4));
		ContestManager.getInstance().setGameStrategies(gameStrategies);
		ContestManager.getInstance().setState(State.FINAL);
		ContestManager.getInstance().populateContests();
	}
	
	public static void addToBestGameStrategies(ArrayList<GameStrategy> gameStrategies) {
		bestGameStrategies.addAll(gameStrategies);
		Collections.sort(bestGameStrategies);
		ArrayList<GameStrategy> temp = new ArrayList<>();
		for(int index = 0; index < 10; index++) {
			temp.add(bestGameStrategies.get(index));
		}
		bestGameStrategies = temp;
	}
}