package com.romullogirardi.huntshark.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Main {
	
	//Collection which stores the 10 best GameStrategies
	public static ArrayList<GameStrategy> bestGameStrategies = new ArrayList<GameStrategy>();
	
	public static void main(String[] args) throws IOException {
		
//		ContestManager.getInstance().populateContests();
		
		//Executing iterations in intervals to discover the best 10 gameStrategies
		int interval = 1000;
		int index = 1;
		long numberOfIterations = 0;
		do {
			if(numberOfIterations != 0) {
				System.out.println("Computando iteração " + index + "/" + numberOfIterations + ", de " + (index - 1) * interval + " a " + index * interval);
			}
			ContestManager.newInstance(); 
			ContestManager.getInstance().initializeGameStrategiesByCombinationsGenerator((index - 1) * interval, index * interval);
			ContestManager.getInstance().populateContests();
			System.out.println("RANKING DE ESTRATÉGIAS APÓS A " + index + "ª ITERAÇÃO:");
			for(GameStrategy gameStrategy : bestGameStrategies) {
				System.out.println(gameStrategy.toString());
			}
			System.out.println();
			index++;
			if(numberOfIterations == 0) {
				numberOfIterations = (Math.abs(ContestManager.getInstance().getCombinationsGenerator().c(ContestManager.N, ContestManager.K) / interval)) + 1;
			}
		} while (index <= numberOfIterations);
		
		//Analysing what is the best group of 4 gameStrategies in the top 10
		Integer[] elements = new Integer[10];
		for(int i = 1; i <= 10; i++) {
			elements[i - 1] = i;
		}
		CombinationsGenerator mCombinationsGenerator = new CombinationsGenerator(elements, 4) {
			
			@Override
			public void processCombination(Object[] elements, int[] combination) {
				Vector<GameStrategy> gameStrategies = new Vector<>();
				for(int index = 0; index < combination.length; index++) {
					gameStrategies.add(new GameStrategy(bestGameStrategies.get(combination[index]).getIndexes()));
				}
				ContestManager.newInstance();
				ContestManager.getInstance().setGameStrategies(gameStrategies);
				ContestManager.getInstance().populateContests();
				
			}
		};
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