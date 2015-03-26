package com.romullogirardi.huntshark.model;

import java.util.ArrayList;

public class Game {

	//ATTRIBUTES
	private ArrayList<Integer> numbers = new ArrayList<>();
	private float investment = 0;
	private float reward = 0;
	
	//CONSTRUCTOR
	public Game(ArrayList<Integer> numbers, float investment, float reward) {
		this.numbers = numbers;
		this.investment = investment;
		this.reward = reward;
	}
	
	//GETTERS AND SETTERS
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
	
	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}
	
	public float getInvestment() {
		return investment;
	}
	
	public void setInvestment(float investment) {
		this.investment = investment;
	}

	public float getReward() {
		return reward;
	}
	
	public void setReward(float reward) {
		this.reward = reward;
	}
}