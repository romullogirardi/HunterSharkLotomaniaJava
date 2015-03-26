package com.romullogirardi.huntshark.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Vector;

public class ContestManager {

	//ATTRIBUTES
	private Vector<Contest> contests;
	private Vector<NumberFrequency> numbersFrequency;
	
	//IMPLEMENTING AS A SINGLETON
	private static ContestManager instance = null;
	
	public static ContestManager getInstance() {
		if (instance == null)
			instance = new ContestManager();
		return instance;
	}
	
	//CONSTRUCTOR
	public ContestManager() {
		
		//Initializing contests and numbersFrequency with 0 from 1 to 100
		contests = new Vector<>();
		numbersFrequency = new Vector<>();
		for(int index = 0; index < 100; index++) {
			numbersFrequency.add(new NumberFrequency(index, 0));
		}
	}
	
	//METHODS
	public void addContest(Contest contest) {
		
		//Adding contest to contests
		contests.add(contest);
		
		//Updating indexesFrequency and numbersFrequency
		ArrayList<Integer> indexes = new ArrayList<>();
		for(int number : contest.getNumbers()) {
			for(NumberFrequency numberFrequency : numbersFrequency) {
				if(numberFrequency.getNumber() == number) {
					indexes.add(numbersFrequency.indexOf(numberFrequency));
					numberFrequency.setFrequency(numberFrequency.getFrequency() + 1);
				}
			}
		}
		boolean found = false;
		Collections.sort(indexes);
//		for(IndexesFrequency indexesFrequency : this.indexesFrequency) {
//			if(indexesFrequency.getIndexes().equals(indexes)) {
//				indexesFrequency.setFrequency(indexesFrequency.getFrequency() + 1);
//				found = true;
//			}
//		}
//		if(!found) {
//			indexesFrequency.add(new IndexesFrequency(indexes, 1));
//		}
//		
//		
//		//Sorting indexesFrequency
//		Collections.sort(indexesFrequency, new Comparator<IndexesFrequency>() {
//
//			@Override
//			public int compare(IndexesFrequency indexesFrequency1, IndexesFrequency indexesFrequency2) {
//				if(indexesFrequency1.getFrequency() > indexesFrequency2.getFrequency()) {
//					return -1;
//				}
//				if(indexesFrequency1.getFrequency() < indexesFrequency2.getFrequency()) {
//					return 1;
//				}
//				else {
//					return 0;
//				}
//			}
//		});
		
		//Sorting numbersFrequency
		Collections.sort(numbersFrequency, new Comparator<NumberFrequency>() {

			@Override
			public int compare(NumberFrequency numberFrequency1, NumberFrequency numberFrequency2) {
				if(numberFrequency1.getFrequency() > numberFrequency2.getFrequency()) {
					return -1;
				}
				if(numberFrequency1.getFrequency() < numberFrequency2.getFrequency()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
	}
	
	public ArrayList<Integer> nextGame() {
		
		//Getting the 50 most popular indexes
		ArrayList<Integer> indexes = new ArrayList<>();
		int counter = 0;
//		for(IndexesFrequency indexesFrequency : this.indexesFrequency) {
//			for(Integer index : indexesFrequency.getIndexes()) {
//				if(!indexes.contains(index)) {
//					indexes.add(index);
//					counter++;
//					if(counter == 50) {
//						return indexes;
//					}
//				}
//			}
//		}
		
		//Completing the indexes, if necessary
		for(int index = 1; index <= 100; index++) {
			if(!indexes.contains(index)) {
				indexes.add(index);
				counter++;
				if(counter == 50) {
					return indexes;
				}
			}
		}
		
		Collections.sort(indexes);
		return indexes;
	}
	
	public void print() {
		
		for(NumberFrequency numberFrequency : numbersFrequency) {
			System.out.println(numberFrequency.getNumber() + " => " + numberFrequency.getFrequency());
		}
		
		System.out.println("Próximo jogo: ");
		for(Integer index : nextGame()) {
			System.out.print(numbersFrequency.get(index).getNumber() + "\t");
		}
		System.out.println();
	}
	
	public void populateContests() {
		
		float reward20points = 1000000;
		float reward19points = 20000;
		float reward18points = 10000;
		float reward17points = 5000;
		float reward16points = 2500;
		float reward0points = 1000000;
		
		int[] numbers1 = {2, 5, 10, 14, 19, 20, 22, 23, 38, 40, 49, 51, 53, 54, 56, 75, 79, 82, 85, 93};
		contests.add(new Contest(1518, new GregorianCalendar(2015, 0, 3), "POSSE-GO", numbers1, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers2 = {3, 6, 13, 19, 32, 37, 42, 44, 45, 50, 51, 56, 64, 69, 70, 73, 75, 82, 84, 99};
		contests.add(new Contest(1519, new GregorianCalendar(2015, 0, 7), "SANTA FÉ DO SUL-SP", numbers2, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers3 = {5, 6, 7, 12, 15, 20, 30, 33, 37, 57, 58, 60, 64, 67, 72, 82, 87, 88, 95, 96};
		contests.add(new Contest(1520, new GregorianCalendar(2015, 0, 10), "SANTA FÉ DO SUL-SP", numbers3, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers4 = {1, 2, 6, 8, 19, 20, 21, 28, 38, 41, 46, 51, 55, 57, 68, 81, 87, 91, 92, 97};
		contests.add(new Contest(1521, new GregorianCalendar(2015, 0, 14), "ARAGUAÍNA-TO", numbers4, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers5 = {1, 3, 11, 18, 19, 35, 44, 54, 59, 60, 64, 68, 70, 71, 76, 77, 79, 80, 84, 96};
		contests.add(new Contest(1522, new GregorianCalendar(2015, 0, 17), "BRASÍLIA-DF", numbers5, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers6 = {0, 4, 10, 12, 18, 24, 28, 36, 39,	42, 43, 47, 49, 50, 54, 64, 67, 81, 86, 98};
		contests.add(new Contest(1523, new GregorianCalendar(2015, 0, 21), "GURUPI-TO", numbers6, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers7 = {2, 7, 8, 14, 16, 21, 22, 26, 30, 32, 36, 42, 48, 59, 72, 82, 87, 90, 93, 95};
		contests.add(new Contest(1524, new GregorianCalendar(2015, 2, 24), "GURUPI-TO", numbers7, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers8 = {7, 15, 23, 24, 26, 29, 30, 43, 49, 53, 54, 61, 63, 67, 68, 78, 80, 85, 93, 94};
		contests.add(new Contest(1525, new GregorianCalendar(2015, 0, 28), "RIO DAS OSTRAS-RJ", numbers8, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers9 = {3, 6, 7, 17, 18, 23, 25, 26, 38, 50, 51, 62, 66, 70, 78, 82, 86, 88, 90, 98};
		contests.add(new Contest(1526, new GregorianCalendar(2015, 0, 31), "RIO DAS OSTRAS-RJ", numbers9, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers10 = {1, 4, 8, 10, 18, 23, 24, 26, 37, 39, 40,	47, 72, 81, 84, 89, 93, 94, 96, 99};
		contests.add(new Contest(1527, new GregorianCalendar(2015, 1, 4), "VALENÇA-BA", numbers10, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers11 = {7, 11, 17, 41, 46, 48, 50, 52, 56, 60, 61, 62, 69, 71, 78, 79, 81, 82, 92, 94};
		contests.add(new Contest(1528, new GregorianCalendar(2015, 1, 7), "VALENÇA-BA", numbers11, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers12 = {2, 3, 6, 8, 9, 15, 20, 26, 34, 49, 54, 55, 63, 64, 67, 74, 76, 82, 87, 98};
		contests.add(new Contest(1529, new GregorianCalendar(2015, 1, 11), "PORTO SEGURO-BA", numbers12, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers13 = {0, 7, 13, 15, 17, 32, 41, 43, 45, 48, 51, 53, 54, 61, 63, 66, 72, 79, 84, 88};
		contests.add(new Contest(1530, new GregorianCalendar(2015, 1, 14), "PORTO SEGURO-BA", numbers13, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers14 = {13, 14, 15, 25, 26, 27, 35, 39, 50, 51, 52, 61, 65, 69, 70, 72, 75, 77, 92, 94};
		contests.add(new Contest(1531, new GregorianCalendar(2015, 1, 18), "FLORES DA CUNHA-RS", numbers14, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers15 = {5, 8, 14, 16, 17, 21, 27, 38, 49, 50, 51, 54, 57, 61, 66, 75, 77, 83, 92, 94};
		contests.add(new Contest(1532, new GregorianCalendar(2015, 1, 21), "FLORES DA CUNHA-RS", numbers15, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers16 = {8, 10, 25, 34, 36, 38, 53, 54, 55, 56, 58, 69, 76, 81, 84, 85, 86, 88, 98, 99};
		contests.add(new Contest(1533, new GregorianCalendar(2015, 1, 25), "IBIRUBÁ-RS", numbers16, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers17 = {0, 3, 4, 6, 13, 14, 20, 35, 39, 40, 42, 45, 48, 70, 72, 76, 80, 81, 87, 94};
		contests.add(new Contest(1534, new GregorianCalendar(2015, 1, 28), "IBIRUBÁ-RS", numbers17, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers18 = {0, 1, 8, 19, 30, 35, 37, 38, 48, 50, 59, 61, 65, 68, 69, 71, 74, 76, 84, 97};
		contests.add(new Contest(1535, new GregorianCalendar(2015, 2, 4), "CANELINHA-SC", numbers18, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers19 = {0, 1, 4, 12, 15, 16, 17, 23, 28, 29, 30, 32, 34, 43, 45, 50, 52, 61, 63, 85};
		contests.add(new Contest(1536, new GregorianCalendar(2015, 2, 7), "CANELINHA-SC", numbers19, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
		int[] numbers20 = {7, 10, 16, 22, 24, 32, 35, 40, 45, 46, 58, 63, 65, 66, 71, 72, 83, 86, 92, 97};
		contests.add(new Contest(1537, new GregorianCalendar(2015, 2, 11), "ROSEIRA-SP", numbers20, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points));
//		int[] numbers21 = {2, 3, 7, 9, 11, 15, 16, 18, 34, 50, 57, 63, 68, 74, 83, 85, 86, 87, 94, 96};
//		contests.add(new Contest(1538, new GregorianCalendar(2015, 2, 14), "ROSEIRA-SP", numbers21));
	}
	
	public void computeLastContest(Contest contest) {
		
		//Checking last recommended game, if it exists
		if(!contests.isEmpty() && contests.lastElement().getRecommendedGame() != null) {
			checkLastRecommendedGame(contest.getNumbers());
		}
		
		//Adding last contest to contests
		contests.add(contest);
		
		//Computing contests
		computeContests();
	}
	
	private void checkLastRecommendedGame(int[] numbers) {
		
		//Calculating the number of points of the last game
		int numberOfPoints = 0;
		for(int number : numbers) {
			if(contests.lastElement().getRecommendedGame().getNumbers().contains(number)) {
				numberOfPoints++;
			}
		}
		
		//Setting the reward, if it exists
		if(numberOfPoints > 15 || numberOfPoints == 0) {
			switch (numberOfPoints) {
				case 20:
					
					break;
				case 19:
					
					break;
				case 18:
					
					break;
				case 17:
					
					break;
				case 16:
					
					break;
				case 0:
					
					break;
				default:
					break;
			}
//			contests.lastElement().setReward(myScanner.nextFloat());
//			myScanner.close();
		}
		else {
			System.out.println("Que pena! Você marcou apenas " + numberOfPoints + " pontos. Esta pontuação não recebe prêmio");
		}
	}
	
	private void computeContests() {
		
	}
}