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
	private int pointsMax = 0;
	private float pointsAverage = 0;
	private static int counter = 0;
	
	//CONSTRUCTOR
	public GameStrategy(int[] combinationIndexes) {
		
		System.out.println("Criando a " + ++counter + "ª estratégia de jogo...");
		
		//Setting indexes
		
		//4 arrays de 25
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

		//10 arrays de 10
//		Integer[] arrayIndex1 = new Integer[10];
//		Integer[] arrayIndex2 = new Integer[10];
//		Integer[] arrayIndex3 = new Integer[10];
//		Integer[] arrayIndex4 = new Integer[10];
//		Integer[] arrayIndex5 = new Integer[10];
//		Integer[] arrayIndex6 = new Integer[10];
//		Integer[] arrayIndex7 = new Integer[10];
//		Integer[] arrayIndex8 = new Integer[10];
//		Integer[] arrayIndex9 = new Integer[10];
//		Integer[] arrayIndex10 = new Integer[10];
//		for(int index = 0; index < 100; index++) {
//			if(index < 10) {
//				arrayIndex1[index] = index;
//			}
//			else if(index < 20) {
//				arrayIndex2[index - 10] = index;
//			}
//			else if(index < 30) {
//				arrayIndex3[index - 20] = index;
//			}
//			else if(index < 40) {
//				arrayIndex4[index - 30] = index;
//			}
//			else if(index < 50) {
//				arrayIndex5[index - 40] = index;
//			}
//			else if(index < 60) {
//				arrayIndex6[index - 50] = index;
//			}
//			else if(index < 70) {
//				arrayIndex7[index - 60] = index;
//			}
//			else if(index < 80) {
//				arrayIndex8[index - 70] = index;
//			}
//			else if(index < 90) {
//				arrayIndex9[index - 80] = index;
//			}
//			else if(index < 100) {
//				arrayIndex10[index - 90] = index;
//			}
//		}
//		Integer[][] arrayIndexes = {arrayIndex1, arrayIndex2, arrayIndex3, arrayIndex4, arrayIndex5, arrayIndex6, arrayIndex7, arrayIndex8, arrayIndex9, arrayIndex10};

//		//20 arrays de 5
//		Integer[] arrayIndex1 = new Integer[5];
//		Integer[] arrayIndex2 = new Integer[5];
//		Integer[] arrayIndex3 = new Integer[5];
//		Integer[] arrayIndex4 = new Integer[5];
//		Integer[] arrayIndex5 = new Integer[5];
//		Integer[] arrayIndex6 = new Integer[5];
//		Integer[] arrayIndex7 = new Integer[5];
//		Integer[] arrayIndex8 = new Integer[5];
//		Integer[] arrayIndex9 = new Integer[5];
//		Integer[] arrayIndex10 = new Integer[5];
//		Integer[] arrayIndex11 = new Integer[5];
//		Integer[] arrayIndex12 = new Integer[5];
//		Integer[] arrayIndex13 = new Integer[5];
//		Integer[] arrayIndex14 = new Integer[5];
//		Integer[] arrayIndex15 = new Integer[5];
//		Integer[] arrayIndex16 = new Integer[5];
//		Integer[] arrayIndex17 = new Integer[5];
//		Integer[] arrayIndex18 = new Integer[5];
//		Integer[] arrayIndex19 = new Integer[5];
//		Integer[] arrayIndex20 = new Integer[5];
//		for(int index = 0; index < 100; index++) {
//			if(index < 5) {
//				arrayIndex1[index] = index;
//			}
//			else if(index < 10) {
//				arrayIndex2[index - 5] = index;
//			}
//			else if(index < 15) {
//				arrayIndex3[index - 10] = index;
//			}
//			else if(index < 20) {
//				arrayIndex4[index - 15] = index;
//			}
//			else if(index < 25) {
//				arrayIndex5[index - 20] = index;
//			}
//			else if(index < 30) {
//				arrayIndex6[index - 25] = index;
//			}
//			else if(index < 35) {
//				arrayIndex7[index - 30] = index;
//			}
//			else if(index < 40) {
//				arrayIndex8[index - 35] = index;
//			}
//			else if(index < 45) {
//				arrayIndex9[index - 40] = index;
//			}
//			else if(index < 50) {
//				arrayIndex10[index - 45] = index;
//			}
//			else if(index < 55) {
//				arrayIndex11[index - 50] = index;
//			}
//			else if(index < 60) {
//				arrayIndex12[index - 55] = index;
//			}
//			else if(index < 65) {
//				arrayIndex13[index - 60] = index;
//			}
//			else if(index < 70) {
//				arrayIndex14[index - 65] = index;
//			}
//			else if(index < 75) {
//				arrayIndex15[index - 70] = index;
//			}
//			else if(index < 80) {
//				arrayIndex16[index - 75] = index;
//			}
//			else if(index < 85) {
//				arrayIndex17[index - 80] = index;
//			}
//			else if(index < 90) {
//				arrayIndex18[index - 85] = index;
//			}
//			else if(index < 95) {
//				arrayIndex19[index - 90] = index;
//			}
//			else if(index < 100) {
//				arrayIndex20[index - 95] = index;
//			}
//		}
//		Integer[][] arrayIndexes = {arrayIndex1, arrayIndex2, arrayIndex3, arrayIndex4, arrayIndex5, arrayIndex6, arrayIndex7, arrayIndex8, arrayIndex9, arrayIndex10, arrayIndex11, arrayIndex12, arrayIndex13, arrayIndex14, arrayIndex15, arrayIndex16, arrayIndex17, arrayIndex18, arrayIndex19, arrayIndex20};
		
//		ArrayList<Integer> indexes = new ArrayList<Integer>();
//		String name = new String();
//		for(int combinationIndex : combinationIndexes) {
//			for(int index : arrayIndexes[combinationIndex]) {
//				indexes.add(index);
//			}
//			name += (combinationIndex + 1) + "º, ";
//		}
//		this.indexes = indexes;

		ArrayList<Integer> indexes = new ArrayList<Integer>();
		String name = new String();
		for(int combinationIndex : combinationIndexes) {
			indexes.add(combinationIndex);
			name += combinationIndex + ", ";
		}
		this.indexes = indexes;

		//Setting name
//		name += "partes";
		name += "índices";
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

	public int getPointsMax() {
		return pointsMax;
	}

	public void setPointsMax(int pointsMax) {
		this.pointsMax = pointsMax;
	}

	public float getPointsAverage() {
		return pointsAverage;
	}

	public void setPointsAverage(float pointsAverage) {
		this.pointsAverage = pointsAverage;
	}
}