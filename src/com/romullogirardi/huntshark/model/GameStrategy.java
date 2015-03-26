package com.romullogirardi.huntshark.model;

import java.util.ArrayList;

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
	private int frequency;
	
	//CONSTRUCTOR
	private GameStrategy(String name) {
		this.name = name;
		this.indexes = setGameStrategyIndexes();
		this.frequency = 0;
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

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
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
}