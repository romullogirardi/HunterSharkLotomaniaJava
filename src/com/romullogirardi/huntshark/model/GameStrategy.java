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
//		int numberOfArrays = 100 / ContestManager.getInstance().N;
//		int arrayLength = 100 / numberOfArrays;		
//		Integer[][] arrayIndexes = new Integer[numberOfArrays][arrayLength];
//		Integer[] array = new Integer[arrayLength];
//		int lowLimit = 0, highLimit = 10;
//		for(int index = 0; index < 100; index++) {
//			if(lowLimit <= index && index < highLimit) {
//				if(lowLimit == 0) {
//					array[index] = index;
//				}
//				else {
//					array[index % lowLimit] = index;
//				}
//			}
//			else {
//				arrayIndexes[lowLimit / 10] = array;
//				array = new Integer[arrayLength];
//				array[0] = index;
//				lowLimit += 10;
//				highLimit += 10;
//			}
//		}
		
//		Integer[] arrayIndex1 = new Integer[25];
//		Integer[] arrayIndex2 = new Integer[25];
//		Integer[] arrayIndex3 = new Integer[25];
//		Integer[] arrayIndex4 = new Integer[25];
//		for(int index = 0; index < 100; index++) {
//			if(index < 25) {
//				arrayIndex1[index] = index;
//			}
//			else if(index < 50) {
//				arrayIndex2[index - 25] = index;
//			}
//			else if(index < 75) {
//				arrayIndex3[index - 50] = index;
//			}
//			else if(index < 100) {
//				arrayIndex4[index - 75] = index;
//			}
//		}
//		Integer[][] arrayIndexes = {arrayIndex1, arrayIndex2, arrayIndex3, arrayIndex4};

		Integer[] arrayIndex1 = new Integer[10];
		Integer[] arrayIndex2 = new Integer[10];
		Integer[] arrayIndex3 = new Integer[10];
		Integer[] arrayIndex4 = new Integer[10];
		Integer[] arrayIndex5 = new Integer[10];
		Integer[] arrayIndex6 = new Integer[10];
		Integer[] arrayIndex7 = new Integer[10];
		Integer[] arrayIndex8 = new Integer[10];
		Integer[] arrayIndex9 = new Integer[10];
		Integer[] arrayIndex10 = new Integer[10];
		for(int index = 0; index < 100; index++) {
			if(index < 10) {
				arrayIndex1[index] = index;
			}
			else if(index < 20) {
				arrayIndex2[index - 10] = index;
			}
			else if(index < 30) {
				arrayIndex3[index - 20] = index;
			}
			else if(index < 40) {
				arrayIndex4[index - 30] = index;
			}
			else if(index < 50) {
				arrayIndex5[index - 40] = index;
			}
			else if(index < 60) {
				arrayIndex6[index - 50] = index;
			}
			else if(index < 70) {
				arrayIndex7[index - 60] = index;
			}
			else if(index < 80) {
				arrayIndex8[index - 70] = index;
			}
			else if(index < 90) {
				arrayIndex9[index - 80] = index;
			}
			else if(index < 100) {
				arrayIndex10[index - 90] = index;
			}
		}
		Integer[][] arrayIndexes = {arrayIndex1, arrayIndex2, arrayIndex3, arrayIndex4, arrayIndex5, arrayIndex6, arrayIndex7, arrayIndex8, arrayIndex9, arrayIndex10};
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		String name = new String();
		for(int combinationIndex : combinationIndexes) {
			for(int index : arrayIndexes[combinationIndex]) {
				indexes.add(index);
			}
			name += (combinationIndex + 1) + "º, ";
		}
		this.indexes = indexes;

		//Setting name
		name += "quartos";
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