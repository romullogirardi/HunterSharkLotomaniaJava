package com.romullogirardi.huntshark.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class ContestManager {
	
	//CONSTANTS
	
//	//4 arrays de 25
//	public static final int N = 4;
//	public static final int K = 2;
//	//10 arrays de 10
//	public static final int N = 10;
//	public static final int K = 5;
	//20 arrays de 5
	public static final int N = 20;
	public static final int K = 10;
//	//50 arrays de 2
//	public static final int N = 50;
//	public static final int K = 25;
//	//100
//	public static final int N = 100;
//	public static final int K = 50;

	//ATTRIBUTES
	private Vector<Contest> contests = new Vector<>();
	private Vector<NumberFrequency> numbersFrequency;
	private Vector<GameStrategy> gameStrategies;
	private CombinationsGenerator combinationsGenerator;
	
	//IMPLEMENTING AS A SINGLETON
	private static ContestManager instance = null;
	
	public static void newInstance() {
		instance = null;
	}
	
	public static ContestManager getInstance() {
		if (instance == null) {
			instance = new ContestManager();
		}
		return instance;
	}
	
	//CONSTRUCTORS
	public ContestManager() {
		
		//Initializing numbersFrequency with 0 from 0 to 99
		numbersFrequency = new Vector<>();
		for(int index = 0; index < 100; index++) {
			numbersFrequency.add(new NumberFrequency(index, 0));
		}

		//Initializing gameStrategies with CombinationsGenerator
		gameStrategies = new Vector<>();
		Integer[] elements = new Integer[N];
		for(int index = 1; index <= N; index++) elements[index - 1] = index; 
		CombinationsGenerator myCombinationsGenerator = new CombinationsGenerator(elements, K) {
			
			@Override
			public void processCombination(Object[] elements, int[] combination) {
				gameStrategies.add(new GameStrategy(combination));
			}
		};
		myCombinationsGenerator.generateCombinations();
	}
	
	//GETTERS AND SETTERS
	public CombinationsGenerator getCombinationsGenerator() {
		return combinationsGenerator;
	}

	//METHODS
	public void initializeGameStrategiesByCombinationsGenerator(int lowestIndex, int highestIndex) {

		//Initializing gameStrategies with CombinationsGenerator
		gameStrategies = new Vector<>();
		Integer[] elements = new Integer[N];
		for(int index = 1; index <= N; index++) elements[index - 1] = index; 
		combinationsGenerator = new CombinationsGenerator(elements, K) {
			
			@Override
			public void processCombination(Object[] elements, int[] combination) {
				gameStrategies.add(new GameStrategy(combination));
			}
		};
		if(lowestIndex != -1) combinationsGenerator.setLowestIndex(lowestIndex);
		if(highestIndex != -1) combinationsGenerator.setHighestIndex(highestIndex);
		combinationsGenerator.generateCombinations();
	}
	
	public void computeLastContest(Contest lastContestResult, boolean print) {
		
		//If exists a contest
		if(!contests.isEmpty()) {

			//Checking last recommended games, if it exists
			if(!contests.lastElement().getRecommendedGames().isEmpty()) {
				checkLastGames(lastContestResult);
			}
	
			//Updating last contest
			updateLastContest(lastContestResult);
		}
		
		//Updating numbersFrequency and gameStrategy
		updateControllers(lastContestResult);
		
		//Setting next contest recommended games
		setNextContestRecommendedGames();
		
		//Printing, if necessary
		if(print) {
			print();
		}
	}
	
	private void checkLastGames(Contest lastContestResult) {

//		System.out.println("Computando sorteio " + lastContestResult.getId() + "...");
		
		float totalInvestment = 0;
		float totalReward = 0;
		
		//Calculating the number of points, investment and reward of the last recommended games
		for(Game game: contests.lastElement().getRecommendedGames()) {
			
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
//				System.out.println("Concurso " + lastContestResult.getId() + ": Marquei " + numberOfPoints + " pontos e ganhei R$ " + String.format("%.2f", (float) game.getReward()));
			}
			
			//Increasing total investment and reward
			totalInvestment += game.getInvestment();
			totalReward += game.getReward();
		}
		
		//Setting total investment and reward
		contests.lastElement().setRecommendedInvestment(totalInvestment);
		contests.lastElement().setRecommendedReward(totalReward);
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
		for(GameStrategy gameStrategy : gameStrategies) {
			int points = 0;
			float reward = 0;
			for(int index : indexes) {
				if(gameStrategy.getIndexes().contains(index)) {
					points++;
				}
			}
			
			if(points > 15 || points == 0) {
				switch (points) {
					case 20:
						gameStrategy.setFrequency20points(gameStrategy.getFrequency20points() + 1);
						reward = lastContestResult.getReward20points();
						break;
					case 19:
						gameStrategy.setFrequency19points(gameStrategy.getFrequency19points() + 1);
						reward = lastContestResult.getReward19points();
						break;
					case 18:
						gameStrategy.setFrequency18points(gameStrategy.getFrequency18points() + 1);
						reward = lastContestResult.getReward18points();
						break;
					case 17:
						gameStrategy.setFrequency17points(gameStrategy.getFrequency17points() + 1);
						reward = lastContestResult.getReward17points();
						break;
					case 16:
						gameStrategy.setFrequency16points(gameStrategy.getFrequency16points() + 1);
						reward = lastContestResult.getReward16points();
						break;
					case 0:
						gameStrategy.setFrequency0points(gameStrategy.getFrequency0points() + 1);
						reward = lastContestResult.getReward0points();
						break;
					default:
						break;
				}
			}
			gameStrategy.setReward(gameStrategy.getReward() + reward - Constants.GAME_PRIZE);
			gameStrategy.setPointsAverage(((gameStrategy.getPointsAverage() * contests.size())  + points)/(contests.size() + 1));
		}
		Collections.sort(gameStrategies);
		
		//Sorting numbersFrequency
		Collections.sort(numbersFrequency);
	}
	
	private void setNextContestRecommendedGames() {
		
		ArrayList<Game> recommendedGames = new ArrayList<>();
		
		//Setting recommendedGames
		ArrayList<ArrayList<Integer>> recommendedIndexes = getRecommendedIndexes(Constants.GAMES_QUANTITY);
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
		
//		System.out.println("\nTodos os concursos:\n");
//		for(int index = 0; index < contests.size() - 1; index++) {
//			System.out.println(contests.get(index).toString());
//		}
		
//		System.out.println("\nJogo anterior: ");
//		if((contests.size() - 2) >= 0) {
//			for(Game game : contests.get(contests.size() - 2).getRecommendedGames()) {
//				System.out.println(game.getPoints() + " pontos - Prêmio: R$ " + String.format("%.2f", (float) game.getReward()));
//			}
//		}
//		else {
//			System.out.println("Não há");
//		}
//
//		System.out.println("\nFrequência dos números");
//		for(NumberFrequency numberFrequency : numbersFrequency) {
//			System.out.println(numberFrequency.getNumber() + " => " + numberFrequency.getFrequency());
//		}
//		
//		System.out.println("\nEstratégias(" + gameStrategies.size() + ")");
//		for(GameStrategy gameStrategy : getRecommendedStrategies(5 * Constants.GAMES_QUANTITY)) {
//			System.out.println(gameStrategy.toString());
//		}
//
//		float totalInvestment = 0;
//		float totalReward = 0;
//		for(Contest contest : contests) {
//			totalInvestment += contest.getRecommendedInvestment();
//			totalReward += contest.getRecommendedReward();
//		}
//		System.out.println("\nInvestimento total: R$ " + String.format("%.2f", (float) totalInvestment));
//		System.out.println("Recompensa total: R$ " + String.format("%.2f", (float) totalReward));
//		System.out.println("Lucro total: R$ " + String.format("%.2f", (float) (totalReward - totalInvestment)));
//		DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println("Período avaliado: " + mDateFormat.format(contests.firstElement().getDate().getTime()) + " - " + mDateFormat.format(contests.get(contests.size() - 2).getDate().getTime()));
//		
//		System.out.println("\nPróximo jogo: ");
//		for(Game game : contests.lastElement().getRecommendedGames()) {
//			for(int number : game.getNumbers()) {
//				System.out.print(number + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		//Updating the global GameStrategies ranker
		Main.addToBestGameStrategies(getRecommendedStrategies(10));
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
		
		//Creating contests by a HTML file
		HTMLParser.readContestsFromHTMLFile();
	}
}