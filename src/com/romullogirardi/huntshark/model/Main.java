package com.romullogirardi.huntshark.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	//Collection which stores the 10 best GameStrategies
	public static ArrayList<GameStrategy> bestGameStrategies = new ArrayList<GameStrategy>();
	
	public static void main(String[] args) throws IOException {
		
		//Realizar iterações de 100 em 100
		for(int index = 1; index < 3; index++) {
			ContestManager.getInstance().initializeGameStrategiesByCombinationsGenerator(index, index * 100);
			ContestManager.getInstance().populateContests();
			System.out.println("\nRANKING DE ESTRATÉGIAS APÓS A 1ª ITERAÇÃO:");
			for(GameStrategy gameStrategy : bestGameStrategies) {
				System.out.println(gameStrategy.toString());
			}
			System.out.println();
		}
	}
	
	public static void addToBestGameStrategies(ArrayList<GameStrategy> gameStrategies) {
		bestGameStrategies.addAll(gameStrategies);
		Collections.sort(bestGameStrategies);
		for(int index = 1; index <= (bestGameStrategies.size() - 10); index++) {
			bestGameStrategies.remove(bestGameStrategies.size() - 1);
		}
	}
}