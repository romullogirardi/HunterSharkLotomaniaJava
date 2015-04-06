package com.romullogirardi.huntshark.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ContestManager {
	
	//CONSTANTS
	public final int N = 10;
	public final int K = 5;

	//ATTRIBUTES
	private Vector<Contest> contests = new Vector<>();
	private Vector<NumberFrequency> numbersFrequency;
	private Vector<GameStrategy> gameStrategies;
	private int[] gamesQuantityFrequency;
	
	//IMPLEMENTING AS A SINGLETON
	private static ContestManager instance = null;
	
	public static ContestManager getInstance() {
		if (instance == null)
			instance = new ContestManager();
		return instance;
	}
	
	//CONSTRUCTOR
	public ContestManager() {
		
		//Initializing numbersFrequency with 0 from 0 to 99
		numbersFrequency = new Vector<>();
		for(int index = 0; index < 100; index++) {
			numbersFrequency.add(new NumberFrequency(index, 0));
		}

		//Initializing gameStrategies with CombinationsGenerator
		gameStrategies = new Vector<>();
		Integer[] elements = new Integer[N];
		for(int index = 1; index <= N; index++) elements[index] = index; 
		CombinationsGenerator myCombinationsGenerator = new CombinationsGenerator(elements, K) {
			
			@Override
			public void processCombination(Object[] elements, int[] combination) {
				gameStrategies.add(new GameStrategy(combination));
			}
		};
		myCombinationsGenerator.generateCombinations();
		
		//Initializing gamesQuantityFrequency with 0 from 0 to (Constants.GAME_MAX - 1)
		gamesQuantityFrequency = new int[Constants.GAMES_QUANTITY_MAX + 1];
		for(int index = 0; index < gamesQuantityFrequency.length; index++) {
			gamesQuantityFrequency[index] = 0;
		}
	}
	
	//METHODS
	public void computeLastContest(Contest lastContestResult, boolean print) {
		
		//If exists a contest
		if(!contests.isEmpty()) {

			//Checking last recommended games, if it exists
			if(!contests.lastElement().getRecommendedGames().isEmpty()) {
				checkLastGames(true, lastContestResult);
			}
	
			//Checking last bet games, if it exists
			if(!contests.lastElement().getBetGames().isEmpty()) {
				checkLastGames(false, lastContestResult);
			}
	
			//Updating last contest
			updateLastContest(lastContestResult);
		}
		
		//Updating numbersFrequency, gameStrategy and gamesQuantityFrequency
		updateControllers(lastContestResult);
		
		//Setting next contest recommended games
		setNextContestRecommendedGames();
		
		//Printing, if necessary
		if(print) {
			print();
		}
	}
	
	private void checkLastGames(boolean recommended, Contest lastContestResult) {

		float totalInvestment = 0;
		float totalReward = 0;
		
		//Calculating the number of points, investment and reward of the last recommended games
		ArrayList<Game> games = (recommended) ? contests.lastElement().getRecommendedGames() : contests.lastElement().getBetGames();
		for(Game game: games) {
			
			int numberOfPoints = 0;
			for(int number : lastContestResult.getNumbers()) {
				if(game.getNumbers().contains(number)) {
					numberOfPoints++;
				}
			}
			
			//Setting points and reward, if it exists
			game.setPoints(numberOfPoints);
			if(numberOfPoints > 15 || numberOfPoints == 0) {
				switch (numberOfPoints) {
					case 20:
						game.setReward(lastContestResult.getReward20points());
						break;
					case 19:
						game.setReward(lastContestResult.getReward19points());
						break;
					case 18:
						game.setReward(lastContestResult.getReward18points());
						break;
					case 17:
						game.setReward(lastContestResult.getReward17points());
						break;
					case 16:
						game.setReward(lastContestResult.getReward16points());
						break;
					case 0:
						game.setReward(lastContestResult.getReward0points());
						break;
					default:
						break;
				}
			}
			
			//Increasing total investment and reward
			totalInvestment += game.getInvestment();
			totalReward += game.getReward();
		}
		
		//Setting total investment and reward
		if(recommended) {
			contests.lastElement().setRecommendedInvestment(totalInvestment);
			contests.lastElement().setRecommendedReward(totalReward);
		}
		else {
			contests.lastElement().setBetInvestment(totalInvestment);
			contests.lastElement().setBetReward(totalReward);
		}
	}
	
	private void updateLastContest(Contest lastContestResult) {
		
		contests.lastElement().setId(lastContestResult.getId());
		contests.lastElement().setDate(lastContestResult.getDate());
		contests.lastElement().setPlace(lastContestResult.getPlace());
		contests.lastElement().setNumbers(lastContestResult.getNumbers());
		contests.lastElement().setReward20points(lastContestResult.getReward20points());
		contests.lastElement().setReward19points(lastContestResult.getReward19points());
		contests.lastElement().setReward18points(lastContestResult.getReward18points());
		contests.lastElement().setReward17points(lastContestResult.getReward17points());
		contests.lastElement().setReward16points(lastContestResult.getReward16points());
		contests.lastElement().setReward0points(lastContestResult.getReward0points());
	}
	
	private void updateControllers(Contest lastContestResult) {
		
		//Updating numbersFrequency and getting selected indexes
		ArrayList<Integer> indexes = new ArrayList<>();
		for(int number : lastContestResult.getNumbers()) {
			for(NumberFrequency numberFrequency : numbersFrequency) {
				if(numberFrequency.getNumber() == number) {
					indexes.add(numbersFrequency.indexOf(numberFrequency));
					numberFrequency.setFrequency(numberFrequency.getFrequency() + 1);
				}
			}
		}
		
		//Updating gameStrategy
		Collections.sort(indexes);
		ArrayList<Float> rewards = new ArrayList<>();
		for(GameStrategy gameStrategy : gameStrategies) {
			int points = 0;
			for(int index : indexes) {
				if(gameStrategy.getIndexes().contains(index)) {
					points++;
				}
			}
			
			if(points > 15 || points == 0) {
				switch (points) {
					case 20:
						gameStrategy.setFrequency20points(gameStrategy.getFrequency20points() + 1);
						rewards.add(lastContestResult.getReward20points() - Constants.GAME_PRIZE);
						break;
					case 19:
						gameStrategy.setFrequency19points(gameStrategy.getFrequency19points() + 1);
						rewards.add(lastContestResult.getReward19points() - Constants.GAME_PRIZE);
						break;
					case 18:
						gameStrategy.setFrequency18points(gameStrategy.getFrequency18points() + 1);
						rewards.add(lastContestResult.getReward18points() - Constants.GAME_PRIZE);
						break;
					case 17:
						gameStrategy.setFrequency17points(gameStrategy.getFrequency17points() + 1);
						rewards.add(lastContestResult.getReward17points() - Constants.GAME_PRIZE);
						break;
					case 16:
						gameStrategy.setFrequency16points(gameStrategy.getFrequency16points() + 1);
						rewards.add(lastContestResult.getReward16points() - Constants.GAME_PRIZE);
						break;
					case 0:
						gameStrategy.setFrequency0points(gameStrategy.getFrequency0points() + 1);
						rewards.add(lastContestResult.getReward0points() - Constants.GAME_PRIZE);
						break;
					default:
						rewards.add((float) 0);
						break;
				}
			}
			gameStrategy.setPointsAverage(((gameStrategy.getPointsAverage() * contests.size())  + points)/(contests.size() + 1));
		}
		Collections.sort(gameStrategies, new Comparator<GameStrategy>() {

			@Override
			public int compare(GameStrategy gameStrategy1, GameStrategy gameStrategy2) {
				if(gameStrategy1.getPointsAverage() > gameStrategy2.getPointsAverage()) {
					return -1;
				}
				if(gameStrategy1.getPointsAverage() < gameStrategy2.getPointsAverage()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		
		//Updating gamesQuantityFrequency
		Collections.sort(rewards);
		int gamesQuantity = 1;
		for(int index = 1; index < rewards.size(); index++) {
			if(rewards.get(index) > 0 && index < Constants.GAMES_QUANTITY_MAX) {
				gamesQuantity++;
			}
			else {
				break;
			}
		}
		gamesQuantityFrequency[gamesQuantity]++;

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
	
	private void setNextContestRecommendedGames() {
		
		ArrayList<Game> recommendedGames = new ArrayList<>();
		
		//Getting the most frequent gamesQuantity
		int max = 0;
		int gamesQuantity = 1;
		for(int index = 1; index < gamesQuantityFrequency.length; index++) {
			if(gamesQuantityFrequency[index] > max) {
				gamesQuantity = index;
			}
		}
		
		//Setting recommendedGames
		ArrayList<ArrayList<Integer>> recommendedIndexes = getRecommendedIndexes(gamesQuantity);
		for(ArrayList<Integer> indexes : recommendedIndexes) {
			ArrayList<Integer> numbers = new ArrayList<>();
			for(Integer index : indexes) {
				numbers.add(numbersFrequency.get(index).getNumber());
			}
			Collections.sort(numbers);
			recommendedGames.add(new Game(numbers));
		}
		
		//Adding a new contest with recommendedGames
		contests.add(new Contest(recommendedGames));
	}

	public void print() {
		
		System.out.println("\nJogo anterior: ");
		if((contests.size() - 2) >= 0) {
			for(Game game : contests.get(contests.size() - 2).getRecommendedGames()) {
				System.out.println(game.getPoints() + " pontos - Prêmio: R$ " + game.getReward());
			}
		}
		else {
			System.out.println("Não há");
		}

		System.out.println("\nFrequência dos números");
		for(NumberFrequency numberFrequency : numbersFrequency) {
			System.out.println(numberFrequency.getNumber() + " => " + numberFrequency.getFrequency());
		}
		
		System.out.println("\nComparativo de número de jogos");
		for(int index = 0; index < gamesQuantityFrequency.length; index++) {
			System.out.println(index + " => " + gamesQuantityFrequency[index]);
		}

		System.out.println("\nEstratégias");
		for(GameStrategy gameStrategy : getRecommendedStrategies(Constants.GAMES_QUANTITY_MAX)) {
			System.out.println(gameStrategy.getName() + " => " + gameStrategy.getPointsAverage());
		}

		float totalInvestment = 0;
		float totalReward = 0;
		for(Contest contest : contests) {
			totalInvestment += contest.getRecommendedInvestment();
			totalReward += contest.getRecommendedReward();
		}
		System.out.println("\nInvestimento total: R$ " + totalInvestment);
		System.out.println("Recompensa total: R$ " + totalReward);
		System.out.println("Lucro total: R$ " + (totalReward - totalInvestment));
		
		System.out.println("\nPróximo jogo: ");
		for(Game game : contests.lastElement().getRecommendedGames()) {
			for(int number : game.getNumbers()) {
				System.out.print(number + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private ArrayList<ArrayList<Integer>> getRecommendedIndexes(int gamesQuantity) {
		
		ArrayList<ArrayList<Integer>> indexes = new ArrayList<>();

		for(int index = 0; index < gamesQuantity; index++) {
			indexes.add(gameStrategies.get(index).getIndexes());
		}
		
		return indexes;
	}
	
	private ArrayList<GameStrategy> getRecommendedStrategies(int gamesQuantity) {
		
		ArrayList<GameStrategy> gameStrategiesSelected = new ArrayList<>();
		
		for(int index = 0; index < gamesQuantity; index++) {
			gameStrategiesSelected.add(gameStrategies.get(index));
		}
		
		return gameStrategiesSelected;
	}

	public void populateContests() {
		
		float reward20points = 1000000;
		float reward19points = 20000;
		float reward18points = 10000;
		float reward17points = 5000;
		float reward16points = 2500;
		float reward0points = 1000000;
		
		int[] numbers1 = {2, 5, 10, 14, 19, 20, 22, 23, 38, 40, 49, 51, 53, 54, 56, 75, 79, 82, 85, 93};
		computeLastContest(new Contest(1518, new GregorianCalendar(2015, 0, 3), "POSSE-GO", numbers1, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers2 = {3, 6, 13, 19, 32, 37, 42, 44, 45, 50, 51, 56, 64, 69, 70, 73, 75, 82, 84, 99};
		computeLastContest(new Contest(1519, new GregorianCalendar(2015, 0, 7), "SANTA FÉ DO SUL-SP", numbers2, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers3 = {5, 6, 7, 12, 15, 20, 30, 33, 37, 57, 58, 60, 64, 67, 72, 82, 87, 88, 95, 96};
		computeLastContest(new Contest(1520, new GregorianCalendar(2015, 0, 10), "SANTA FÉ DO SUL-SP", numbers3, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers4 = {1, 2, 6, 8, 19, 20, 21, 28, 38, 41, 46, 51, 55, 57, 68, 81, 87, 91, 92, 97};
		computeLastContest(new Contest(1521, new GregorianCalendar(2015, 0, 14), "ARAGUAÍNA-TO", numbers4, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers5 = {1, 3, 11, 18, 19, 35, 44, 54, 59, 60, 64, 68, 70, 71, 76, 77, 79, 80, 84, 96};
		computeLastContest(new Contest(1522, new GregorianCalendar(2015, 0, 17), "BRASÍLIA-DF", numbers5, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers6 = {0, 4, 10, 12, 18, 24, 28, 36, 39,	42, 43, 47, 49, 50, 54, 64, 67, 81, 86, 98};
		computeLastContest(new Contest(1523, new GregorianCalendar(2015, 0, 21), "GURUPI-TO", numbers6, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers7 = {2, 7, 8, 14, 16, 21, 22, 26, 30, 32, 36, 42, 48, 59, 72, 82, 87, 90, 93, 95};
		computeLastContest(new Contest(1524, new GregorianCalendar(2015, 2, 24), "GURUPI-TO", numbers7, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers8 = {7, 15, 23, 24, 26, 29, 30, 43, 49, 53, 54, 61, 63, 67, 68, 78, 80, 85, 93, 94};
		computeLastContest(new Contest(1525, new GregorianCalendar(2015, 0, 28), "RIO DAS OSTRAS-RJ", numbers8, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers9 = {3, 6, 7, 17, 18, 23, 25, 26, 38, 50, 51, 62, 66, 70, 78, 82, 86, 88, 90, 98};
		computeLastContest(new Contest(1526, new GregorianCalendar(2015, 0, 31), "RIO DAS OSTRAS-RJ", numbers9, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers10 = {1, 4, 8, 10, 18, 23, 24, 26, 37, 39, 40,	47, 72, 81, 84, 89, 93, 94, 96, 99};
		computeLastContest(new Contest(1527, new GregorianCalendar(2015, 1, 4), "VALENÇA-BA", numbers10, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers11 = {7, 11, 17, 41, 46, 48, 50, 52, 56, 60, 61, 62, 69, 71, 78, 79, 81, 82, 92, 94};
		computeLastContest(new Contest(1528, new GregorianCalendar(2015, 1, 7), "VALENÇA-BA", numbers11, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers12 = {2, 3, 6, 8, 9, 15, 20, 26, 34, 49, 54, 55, 63, 64, 67, 74, 76, 82, 87, 98};
		computeLastContest(new Contest(1529, new GregorianCalendar(2015, 1, 11), "PORTO SEGURO-BA", numbers12, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers13 = {0, 7, 13, 15, 17, 32, 41, 43, 45, 48, 51, 53, 54, 61, 63, 66, 72, 79, 84, 88};
		computeLastContest(new Contest(1530, new GregorianCalendar(2015, 1, 14), "PORTO SEGURO-BA", numbers13, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers14 = {13, 14, 15, 25, 26, 27, 35, 39, 50, 51, 52, 61, 65, 69, 70, 72, 75, 77, 92, 94};
		computeLastContest(new Contest(1531, new GregorianCalendar(2015, 1, 18), "FLORES DA CUNHA-RS", numbers14, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers15 = {5, 8, 14, 16, 17, 21, 27, 38, 49, 50, 51, 54, 57, 61, 66, 75, 77, 83, 92, 94};
		computeLastContest(new Contest(1532, new GregorianCalendar(2015, 1, 21), "FLORES DA CUNHA-RS", numbers15, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers16 = {8, 10, 25, 34, 36, 38, 53, 54, 55, 56, 58, 69, 76, 81, 84, 85, 86, 88, 98, 99};
		computeLastContest(new Contest(1533, new GregorianCalendar(2015, 1, 25), "IBIRUBÁ-RS", numbers16, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers17 = {0, 3, 4, 6, 13, 14, 20, 35, 39, 40, 42, 45, 48, 70, 72, 76, 80, 81, 87, 94};
		computeLastContest(new Contest(1534, new GregorianCalendar(2015, 1, 28), "IBIRUBÁ-RS", numbers17, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers18 = {0, 1, 8, 19, 30, 35, 37, 38, 48, 50, 59, 61, 65, 68, 69, 71, 74, 76, 84, 97};
		computeLastContest(new Contest(1535, new GregorianCalendar(2015, 2, 4), "CANELINHA-SC", numbers18, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers19 = {0, 1, 4, 12, 15, 16, 17, 23, 28, 29, 30, 32, 34, 43, 45, 50, 52, 61, 63, 85};
		computeLastContest(new Contest(1536, new GregorianCalendar(2015, 2, 7), "CANELINHA-SC", numbers19, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
		int[] numbers20 = {7, 10, 16, 22, 24, 32, 35, 40, 45, 46, 58, 63, 65, 66, 71, 72, 83, 86, 92, 97};
		computeLastContest(new Contest(1537, new GregorianCalendar(2015, 2, 11), "ROSEIRA-SP", numbers20, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
//		int[] numbers21 = {2, 3, 7, 9, 11, 15, 16, 18, 34, 50, 57, 63, 68, 74, 83, 85, 86, 87, 94, 96};
//		computeLastContest(new Contest(1538, new GregorianCalendar(2015, 2, 14), "ROSEIRA-SP", numbers21, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
	}
}