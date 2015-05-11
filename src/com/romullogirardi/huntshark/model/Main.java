package com.romullogirardi.huntshark.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	//Collection which stores the 10 best GameStrategies
	public static ArrayList<GameStrategy> bestGameStrategies = new ArrayList<GameStrategy>();
	
	public static void main(String[] args) throws IOException {
		
//		ContestManager.getInstance().populateContests();
		
		//Realizar iterações de interval em interval
		int interval = 100;
		int index = 1;
		do {
			ContestManager.getInstance().initializeGameStrategiesByCombinationsGenerator((index - 1) * interval, index * interval);
			ContestManager.getInstance().populateContests();
			System.out.println("\nRANKING DE ESTRATÉGIAS APÓS A " + index + "ª ITERAÇÃO:");
			for(GameStrategy gameStrategy : bestGameStrategies) {
				System.out.println(gameStrategy.toString());
			}
			System.out.println();
			index++;
		} while (index <= ((Math.abs(ContestManager.getInstance().getCombinationsGenerator().c(ContestManager.N, ContestManager.K) / interval) + 1)));
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