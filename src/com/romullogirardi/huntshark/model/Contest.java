package com.romullogirardi.huntshark.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Contest { 

	//ATTRIBUTES
	private int id;
	private Calendar date;
	private String place;
	private int[] numbers;
	private float reward20points;
	private float reward19points;
	private float reward18points;
	private float reward17points;
	private float reward16points;
	private float reward0points;
	private ArrayList<Game> recommendedGames = new ArrayList<>();
	private float recommendedInvestment = 0;
	private float recommendedReward = 0;
	private boolean bet = false;
	
	//CONSTRUCTORS
	public Contest(int id, Calendar date, String place, int[] numbers,
			float reward20points, float reward19points, float reward18points,
			float reward17points, float reward16points, float reward0points) {
		this.id = id;
		this.date = date;
		this.place = place;
		this.numbers = numbers;
		this.reward20points = reward20points;
		this.reward19points = reward19points;
		this.reward18points = reward18points;
		this.reward17points = reward17points;
		this.reward16points = reward16points;
		this.reward0points = reward0points;
	}

	public Contest(ArrayList<Game> recommendedGames) {
		this.recommendedGames = recommendedGames;
	}

	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public float getReward20points() {
		return reward20points;
	}

	public void setReward20points(float reward20points) {
		this.reward20points = reward20points;
	}

	public float getReward19points() {
		return reward19points;
	}

	public void setReward19points(float reward19points) {
		this.reward19points = reward19points;
	}

	public float getReward18points() {
		return reward18points;
	}

	public void setReward18points(float reward18points) {
		this.reward18points = reward18points;
	}

	public float getReward17points() {
		return reward17points;
	}

	public void setReward17points(float reward17points) {
		this.reward17points = reward17points;
	}

	public float getReward16points() {
		return reward16points;
	}

	public void setReward16points(float reward16points) {
		this.reward16points = reward16points;
	}

	public float getReward0points() {
		return reward0points;
	}

	public void setReward0points(float reward0points) {
		this.reward0points = reward0points;
	}

	public ArrayList<Game> getRecommendedGames() {
		return recommendedGames;
	}

	public void setRecommendedGames(ArrayList<Game> recommendedGames) {
		this.recommendedGames = recommendedGames;
	}

	public float getRecommendedInvestment() {
		return recommendedInvestment;
	}

	public void setRecommendedInvestment(float recommendedInvestment) {
		this.recommendedInvestment = recommendedInvestment;
	}

	public float getRecommendedReward() {
		return recommendedReward;
	}

	public void setRecommendedReward(float recommendedReward) {
		this.recommendedReward = recommendedReward;
	}
	
	public boolean isBet() {
		return bet;
	}

	public void setBet(boolean bet) {
		this.bet = bet;
	}

	//OTHER METHODS
	public String toString() {
		DateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(id) + "\t" + mDateFormat.format(date.getTime()) + "\t" + numbers[0] + " ... " + numbers[19] + "\t" + place + "\t" + reward20points + "\t" + reward19points + "\t" + reward18points + "\t" + reward17points + "\t" + reward16points + "\t" + reward0points + "\t" +
				((recommendedGames.isEmpty()) ? "Sem jogos" : recommendedGames.get(0).getPoints() + " pontos - R$ " + recommendedGames.get(0).getReward()) + "\t" + 
				"Saldo total: " + String.valueOf(recommendedReward - recommendedInvestment);
	}
}