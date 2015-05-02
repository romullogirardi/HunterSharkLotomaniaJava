package com.romullogirardi.huntshark.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class ContestManager {
	
	//CONSTANTS
	public final int N = 20;
	public final int K = 10;

	//ATTRIBUTES
	private Vector<Contest> contests = new Vector<>();
	private Vector<NumberFrequency> numbersFrequency;
	private Vector<GameStrategy> gameStrategies;
	
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
		for(int index = 1; index <= N; index++) elements[index - 1] = index; 
		CombinationsGenerator myCombinationsGenerator = new CombinationsGenerator(elements, K) {
			
			@Override
			public void processCombination(Object[] elements, int[] combination) {
				gameStrategies.add(new GameStrategy(combination));
			}
		};
		myCombinationsGenerator.generateCombinations();
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
				System.out.println("Concurso " + lastContestResult.getId() + ": Marquei " + numberOfPoints + " pontos e ganhei R$ " + game.getReward());
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
		Collections.sort(gameStrategies, new Comparator<GameStrategy>() {

			@Override
			public int compare(GameStrategy gameStrategy1, GameStrategy gameStrategy2) {
				if(gameStrategy1.getReward() > gameStrategy2.getReward()) {
					return -1;
				}
				if(gameStrategy1.getReward() < gameStrategy2.getReward()) {
					return 1;
				}
				else {
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
			}
		});
		
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
		
		System.out.println("\nEstratégias(" + gameStrategies.size() + ")");
		for(GameStrategy gameStrategy : getRecommendedStrategies(5 * Constants.GAMES_QUANTITY)) {
//		for(GameStrategy gameStrategy : gameStrategies) {
			System.out.println(gameStrategy.toString());
		}

		float totalInvestment = 0;
		float totalReward = 0;
		for(Contest contest : contests) {
			totalInvestment += contest.getRecommendedInvestment();
			totalReward += contest.getRecommendedReward();
		}
		System.out.println("\nInvestimento total: R$ " + String.format("%.2f", (float) totalInvestment));
		System.out.println("Recompensa total: R$ " + String.format("%.2f", (float) totalReward));
		System.out.println("Lucro total: R$ " + String.format("%.2f", (float) (totalReward - totalInvestment)));
		DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Período avaliado: " + mDateFormat.format(contests.firstElement().getDate().getTime()) + " - " + mDateFormat.format(contests.get(contests.size() - 2).getDate().getTime()));
		
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
		
		//Creating contests by a HTML file
		HTMLParser.readContestsFromHTMLFile();
	}
}