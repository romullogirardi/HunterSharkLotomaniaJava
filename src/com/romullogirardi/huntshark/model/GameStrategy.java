package com.romullogirardi.huntshark.model;

import java.util.ArrayList;

public class GameStrategy implements Comparable<GameStrategy> {

	//ATTRIBUTTES
	private ArrayList<Integer> indexes;
	private int frequency20points = 0;
	private int frequency19points = 0;
	private int frequency18points = 0;
	private int frequency17points = 0;
	private int frequency16points = 0;
	private int frequency0points = 0;
	private float pointsAverage = 0;
	private float reward = 0;
	private static int counter = 0;
	
	//CONSTRUCTOR
	public GameStrategy(int[] combinationIndexes) {
		
		System.out.println("Criando a " + ++counter + "ª estratégia de jogo...");
		
		//Setting indexes
		
//		//4 arrays de 25
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

//		//50 arrays de 2
//		Integer[] arrayIndex1 = new Integer[2];
//		Integer[] arrayIndex2 = new Integer[2];
//		Integer[] arrayIndex3 = new Integer[2];
//		Integer[] arrayIndex4 = new Integer[2];
//		Integer[] arrayIndex5 = new Integer[2];
//		Integer[] arrayIndex6 = new Integer[2];
//		Integer[] arrayIndex7 = new Integer[2];
//		Integer[] arrayIndex8 = new Integer[2];
//		Integer[] arrayIndex9 = new Integer[2];
//		Integer[] arrayIndex10 = new Integer[2];
//		Integer[] arrayIndex11 = new Integer[2];
//		Integer[] arrayIndex12 = new Integer[2];
//		Integer[] arrayIndex13 = new Integer[2];
//		Integer[] arrayIndex14 = new Integer[2];
//		Integer[] arrayIndex15 = new Integer[2];
//		Integer[] arrayIndex16 = new Integer[2];
//		Integer[] arrayIndex17 = new Integer[2];
//		Integer[] arrayIndex18 = new Integer[2];
//		Integer[] arrayIndex19 = new Integer[2];
//		Integer[] arrayIndex20 = new Integer[2];
//		Integer[] arrayIndex21 = new Integer[2];
//		Integer[] arrayIndex22 = new Integer[2];
//		Integer[] arrayIndex23 = new Integer[2];
//		Integer[] arrayIndex24 = new Integer[2];
//		Integer[] arrayIndex25 = new Integer[2];
//		Integer[] arrayIndex26 = new Integer[2];
//		Integer[] arrayIndex27 = new Integer[2];
//		Integer[] arrayIndex28 = new Integer[2];
//		Integer[] arrayIndex29 = new Integer[2];
//		Integer[] arrayIndex30 = new Integer[2];
//		Integer[] arrayIndex31 = new Integer[2];
//		Integer[] arrayIndex32 = new Integer[2];
//		Integer[] arrayIndex33 = new Integer[2];
//		Integer[] arrayIndex34 = new Integer[2];
//		Integer[] arrayIndex35 = new Integer[2];
//		Integer[] arrayIndex36 = new Integer[2];
//		Integer[] arrayIndex37 = new Integer[2];
//		Integer[] arrayIndex38 = new Integer[2];
//		Integer[] arrayIndex39 = new Integer[2];
//		Integer[] arrayIndex40 = new Integer[2];
//		Integer[] arrayIndex41 = new Integer[2];
//		Integer[] arrayIndex42 = new Integer[2];
//		Integer[] arrayIndex43 = new Integer[2];
//		Integer[] arrayIndex44 = new Integer[2];
//		Integer[] arrayIndex45 = new Integer[2];
//		Integer[] arrayIndex46 = new Integer[2];
//		Integer[] arrayIndex47 = new Integer[2];
//		Integer[] arrayIndex48 = new Integer[2];
//		Integer[] arrayIndex49 = new Integer[2];
//		Integer[] arrayIndex50 = new Integer[2];
//		for(int index = 0; index < 100; index++) {
//			if(index < 2) {
//				arrayIndex1[index] = index;
//			}
//			else if(index < 4) {
//				arrayIndex2[index - 2] = index;
//			}
//			else if(index < 6) {
//				arrayIndex3[index - 4] = index;
//			}
//			else if(index < 8) {
//				arrayIndex4[index - 6] = index;
//			}
//			else if(index < 10) {
//				arrayIndex5[index - 8] = index;
//			}
//			else if(index < 12) {
//				arrayIndex6[index - 10] = index;
//			}
//			else if(index < 14) {
//				arrayIndex7[index - 12] = index;
//			}
//			else if(index < 16) {
//				arrayIndex8[index - 14] = index;
//			}
//			else if(index < 18) {
//				arrayIndex9[index - 16] = index;
//			}
//			else if(index < 20) {
//				arrayIndex10[index - 18] = index;
//			}
//			else if(index < 22) {
//				arrayIndex11[index - 20] = index;
//			}
//			else if(index < 24) {
//				arrayIndex12[index - 22] = index;
//			}
//			else if(index < 26) {
//				arrayIndex13[index - 24] = index;
//			}
//			else if(index < 28) {
//				arrayIndex14[index - 26] = index;
//			}
//			else if(index < 30) {
//				arrayIndex15[index - 28] = index;
//			}
//			else if(index < 32) {
//				arrayIndex16[index - 30] = index;
//			}
//			else if(index < 34) {
//				arrayIndex17[index - 32] = index;
//			}
//			else if(index < 36) {
//				arrayIndex18[index - 34] = index;
//			}
//			else if(index < 38) {
//				arrayIndex19[index - 36] = index;
//			}
//			else if(index < 40) {
//				arrayIndex20[index - 38] = index;
//			}
//			else if(index < 42) {
//				arrayIndex21[index - 40] = index;
//			}
//			else if(index < 44) {
//				arrayIndex22[index - 42] = index;
//			}
//			else if(index < 46) {
//				arrayIndex23[index - 44] = index;
//			}
//			else if(index < 48) {
//				arrayIndex24[index - 46] = index;
//			}
//			else if(index < 50) {
//				arrayIndex25[index - 48] = index;
//			}
//			else if(index < 52) {
//				arrayIndex26[index - 50] = index;
//			}
//			else if(index < 54) {
//				arrayIndex27[index - 52] = index;
//			}
//			else if(index < 56) {
//				arrayIndex28[index - 54] = index;
//			}
//			else if(index < 58) {
//				arrayIndex29[index - 56] = index;
//			}
//			else if(index < 60) {
//				arrayIndex30[index - 58] = index;
//			}
//			else if(index < 62) {
//				arrayIndex31[index - 60] = index;
//			}
//			else if(index < 64) {
//				arrayIndex32[index - 62] = index;
//			}
//			else if(index < 66) {
//				arrayIndex33[index - 64] = index;
//			}
//			else if(index < 68) {
//				arrayIndex34[index - 66] = index;
//			}
//			else if(index < 70) {
//				arrayIndex35[index - 68] = index;
//			}
//			else if(index < 72) {
//				arrayIndex36[index - 70] = index;
//			}
//			else if(index < 74) {
//				arrayIndex37[index - 72] = index;
//			}
//			else if(index < 76) {
//				arrayIndex38[index - 74] = index;
//			}
//			else if(index < 78) {
//				arrayIndex39[index - 76] = index;
//			}
//			else if(index < 80) {
//				arrayIndex40[index - 78] = index;
//			}
//			else if(index < 82) {
//				arrayIndex41[index - 80] = index;
//			}
//			else if(index < 84) {
//				arrayIndex42[index - 82] = index;
//			}
//			else if(index < 86) {
//				arrayIndex43[index - 84] = index;
//			}
//			else if(index < 88) {
//				arrayIndex44[index - 86] = index;
//			}
//			else if(index < 90) {
//				arrayIndex45[index - 88] = index;
//			}
//			else if(index < 92) {
//				arrayIndex46[index - 90] = index;
//			}
//			else if(index < 94) {
//				arrayIndex47[index - 92] = index;
//			}
//			else if(index < 96) {
//				arrayIndex48[index - 94] = index;
//			}
//			else if(index < 98) {
//				arrayIndex49[index - 96] = index;
//			}
//			else if(index < 100) {
//				arrayIndex50[index - 98] = index;
//			}
//		}
//		Integer[][] arrayIndexes = {arrayIndex1, arrayIndex2, arrayIndex3, arrayIndex4, arrayIndex5, arrayIndex6, arrayIndex7, arrayIndex8, arrayIndex9, arrayIndex10, 
//				arrayIndex11, arrayIndex12, arrayIndex13, arrayIndex14, arrayIndex15, arrayIndex16, arrayIndex17, arrayIndex18, arrayIndex19, arrayIndex20,
//				arrayIndex21, arrayIndex22, arrayIndex23, arrayIndex24, arrayIndex25, arrayIndex26, arrayIndex27, arrayIndex28, arrayIndex29, arrayIndex30,
//				arrayIndex31, arrayIndex32, arrayIndex33, arrayIndex34, arrayIndex35, arrayIndex36, arrayIndex37, arrayIndex38, arrayIndex39, arrayIndex40,
//				arrayIndex41, arrayIndex42, arrayIndex43, arrayIndex44, arrayIndex45, arrayIndex46, arrayIndex47, arrayIndex48, arrayIndex49, arrayIndex50};
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int combinationIndex : combinationIndexes) {
			for(int index : arrayIndexes[combinationIndex]) {
				indexes.add(index);
			}
		}
		this.indexes = indexes;
	}
	
	//GETTERS AND SETTERS
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
	
	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}

	//OTHER METHODS
	public float getScore() {
		return (float) (6 * frequency20points + 4 * frequency19points + 3 * frequency18points + 2 * frequency17points + 1 * frequency16points + 6 * frequency0points) / 22;
	}
	
	public String toString() {
		String name = "{";
		for(int index = 0; index < indexes.size(); index++) {
			if(index == (indexes.size() - 1)) {
				name += indexes.get(index) + "}";
			}
			else {
				name += indexes.get(index) + ", ";
			}
		}
		return name + " => " + "20(" + frequency20points + "), " + "19(" + frequency19points + "), " + "18(" + frequency18points + "), " + "17(" + frequency17points + "), " + "16(" + frequency16points + "), " + "0(" + frequency0points + ") - Média: " + pointsAverage + " - R$ " + String.format("%.2f", (float) reward) + " - Score: " + String.format("%.2f", (float) getScore()); 
	}

	@Override
	public int compareTo(GameStrategy otherGameStrategy) {
		if(getScore() > otherGameStrategy.getScore()) {
			return -1;
		}
		else if(getScore() < otherGameStrategy.getScore()) {
			return 1;
		}
		else {
			if(reward > otherGameStrategy.getReward()) {
				return -1;
			}
			else if(reward < otherGameStrategy.getReward()) {
				return 1;
			}
			else {
				if(pointsAverage > otherGameStrategy.getPointsAverage()) {
					return -1;
				}
				else if(pointsAverage < otherGameStrategy.getPointsAverage()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}
}