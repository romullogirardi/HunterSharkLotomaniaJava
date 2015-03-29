package com.romullogirardi.huntshark.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public enum GameStrategy {

	FIRST_HALF("Primeira metade"),
	LAST_HALF("Última metade"),
	FIRST_THIRD_QUARTER("Primeiro e Terceiro quarto"),
	FIRST_LAST_QUARTER("Primeiro e Último quarto"),
	SECOND_THIRD_QUARTER("Segundo e Terceiro quarto"),
	SECOND_LAST_QUARTER("Segundo e Último quarto");
	
	//ATTRIBUTTES
	private String name;
	private ArrayList<Integer> indexes;
	private int frequency20points;
	private int frequency19points;
	private int frequency18points;
	private int frequency17points;
	private int frequency16points;
	private int frequency0points;
	private float pointsAverage = 0;
	
	//CONSTRUCTOR
	private GameStrategy(String name) {
		this.name = name;
//		this.indexes = setGameStrategyIndexes();
		
		ArrayList<Integer> indexes = new ArrayList<>();
		if(name.equals("Primeira metade")) {
			for(int index = 0; index < 50; index++) {
					indexes.add(index);
				}
		}
		else if(name.equals("Última metade")) {
			for(int index = 50; index < 100; index++) {
				indexes.add(index);
			}
		}
		else if(name.equals("Primeiro e Terceiro quarto")) {
			for(int index = 0; index < 25; index++) {
				indexes.add(index);
			}
			for(int index = 50; index < 75; index++) {
				indexes.add(index);
			}
		}
		else if(name.equals("Primeiro e Último quarto")) {
			for(int index = 0; index < 25; index++) {
				indexes.add(index);
			}
			for(int index = 75; index < 100; index++) {
				indexes.add(index);
			}
		}
		else if(name.equals("Segundo e Terceiro quarto")) {
			for(int index = 25; index < 75; index++) {
				indexes.add(index);
			}
		}
		else if(name.equals("Segundo e Último quarto")) {
			for(int index = 25; index < 50; index++) {
				indexes.add(index);
			}
			for(int index = 75; index < 100; index++) {
				indexes.add(index);
			}
		}
		this.indexes = indexes;

		
		this.frequency20points = 0;
		this.frequency19points = 0;
		this.frequency18points = 0;
		this.frequency17points = 0;
		this.frequency16points = 0;
		this.frequency0points = 0;
	}
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getIndexes() {
		return indexes;
	}

	public void setIndexes(ArrayList<Integer> indexes) {
		this.indexes = indexes;
	}

	public int getFrequency20points() {
		return frequency20points;
	}

	public void setFrequency20points(int frequency20points) {
		this.frequency20points = frequency20points;
	}

	public int getFrequency19points() {
		return frequency19points;
	}

	public void setFrequency19points(int frequency19points) {
		this.frequency19points = frequency19points;
	}

	public int getFrequency18points() {
		return frequency18points;
	}

	public void setFrequency18points(int frequency18points) {
		this.frequency18points = frequency18points;
	}

	public int getFrequency17points() {
		return frequency17points;
	}

	public void setFrequency17points(int frequency17points) {
		this.frequency17points = frequency17points;
	}

	public int getFrequency16points() {
		return frequency16points;
	}

	public void setFrequency16points(int frequency16points) {
		this.frequency16points = frequency16points;
	}

	public int getFrequency0points() {
		return frequency0points;
	}

	public void setFrequency0points(int frequency0points) {
		this.frequency0points = frequency0points;
	}

	public float getPointsAverage() {
		return pointsAverage;
	}

	public void setPointsAverage(float pointsAverage) {
		this.pointsAverage = pointsAverage;
	}

	//METHOD TO GENERATE INDEXES
	private ArrayList<Integer> setGameStrategyIndexes() {
		
		ArrayList<Integer> indexes = new ArrayList<>();
		switch (this) {
			case FIRST_HALF:
				for(int index = 0; index < 50; index++) {
					indexes.add(index);
				}
				break;
			case LAST_HALF:
				for(int index = 50; index < 100; index++) {
					indexes.add(index);
				}
				break;
			case FIRST_THIRD_QUARTER:
				for(int index = 0; index < 25; index++) {
					indexes.add(index);
				}
				for(int index = 50; index < 75; index++) {
					indexes.add(index);
				}
				break;
			case FIRST_LAST_QUARTER:
				for(int index = 0; index < 25; index++) {
					indexes.add(index);
				}
				for(int index = 75; index < 100; index++) {
					indexes.add(index);
				}
				break;
			case SECOND_THIRD_QUARTER:
				for(int index = 25; index < 75; index++) {
					indexes.add(index);
				}
				break;
			case SECOND_LAST_QUARTER:
				for(int index = 25; index < 50; index++) {
					indexes.add(index);
				}
				for(int index = 75; index < 100; index++) {
					indexes.add(index);
				}
				break;
			default:
				break;
		}
		return indexes;
	}
	
	//METHOD TO GET RECOMMENDED INDEXES
	public static ArrayList<ArrayList<Integer>> getRecommendedIndexes(int gamesQuantity) {
		
		ArrayList<ArrayList<Integer>> indexes = new ArrayList<>();
		
		ArrayList<GameStrategy> gameStrategies = new ArrayList<>();
		for(GameStrategy gameStrategy : values()) {
			gameStrategies.add(gameStrategy);
		}
		Collections.sort(gameStrategies, new Comparator<GameStrategy>() {

			@Override
			public int compare(GameStrategy gameStrategy1, GameStrategy gameStrategy2) {
				if(gameStrategy1.pointsAverage > gameStrategy2.pointsAverage) {
					return -1;
				}
				if(gameStrategy1.pointsAverage < gameStrategy2.pointsAverage) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		
		for(int index = 0; index < gamesQuantity; index++) {
			indexes.add(gameStrategies.get(index).getIndexes());
		}
		
		return indexes;
	}
	
	//METHOD TO GET RECOMMENDED STRATEGIES
	public static ArrayList<GameStrategy> getRecommendedStrategies(int gamesQuantity) {
		
		ArrayList<GameStrategy> gameStrategiesSelected = new ArrayList<>();
		
		ArrayList<GameStrategy> gameStrategies = new ArrayList<>();
		for(GameStrategy gameStrategy : values()) {
			gameStrategies.add(gameStrategy);
		}
		Collections.sort(gameStrategies, new Comparator<GameStrategy>() {

			@Override
			public int compare(GameStrategy gameStrategy1, GameStrategy gameStrategy2) {
				if(gameStrategy1.pointsAverage > gameStrategy2.pointsAverage) {
					return -1;
				}
				if(gameStrategy1.pointsAverage < gameStrategy2.pointsAverage) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		
		for(int index = 0; index < gamesQuantity; index++) {
			gameStrategiesSelected.add(gameStrategies.get(index));
		}
		
		return gameStrategiesSelected;
	}
}