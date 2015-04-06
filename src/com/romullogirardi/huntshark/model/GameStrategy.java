package com.romullogirardi.huntshark.model;

import java.util.ArrayList;

public class GameStrategy {

	//ATTRIBUTTES
	private String name;
	private ArrayList<Integer> indexes;
	private int frequency20points = 0;
	private int frequency19points = 0;
	private int frequency18points = 0;
	private int frequency17points = 0;
	private int frequency16points = 0;
	private int frequency0points = 0;
	private float pointsAverage = 0;
	
	//CONSTRUCTOR
	public GameStrategy(int[] combinationIndexes) {
		
		//Setting indexes
		int numberOfArrays = 100 / ContestManager.getInstance().N;
		Integer[][] arrayIndexes = new Integer[numberOfArrays][];
		Integer[] array = new Integer[100 / numberOfArrays];
		for(int index = 0; index < 100; index++) {
			
		}
		Integer[] array0 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Integer[] array1 = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
		Integer[] array2 = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
		Integer[] array3 = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39};
		Integer[] array4 = {40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
		Integer[] array5 = {50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
		Integer[] array6 = {60, 61, 62, 63, 64, 65, 66, 67, 68, 69};
		Integer[] array7 = {70, 71, 72, 73, 74, 75, 76, 77, 78, 79};
		Integer[] array8 = {80, 81, 82, 83, 84, 85, 86, 87, 88, 89};
		Integer[] array9 = {90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
//		Integer[][] arrayIndexes = {array0, array1, array2, array3, array4, array5, array6, array7, array8, array9};
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		String name = new String();;
		for(int combinationIndex : combinationIndexes) {
			for(int index : arrayIndexes[combinationIndex]) {
				indexes.add(index);
			}
			name += (combinationIndex + 1) + "º, ";
		}
		this.indexes = indexes;

		//Setting name
		name += "décimos";
		this.name = name;
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
}