package com.romullogirardi.huntshark.model;

import java.util.GregorianCalendar;


public class Main {

	public static void main(String[] args) {
		
		//Populating old contests
		ContestManager.getInstance().populateContests();
		
		//Computing last contest
		int[] numbers21 = {2, 3, 7, 9, 11, 15, 16, 18, 34, 50, 57, 63, 68, 74, 83, 85, 86, 87, 94, 96};
		ContestManager.getInstance().computeLastContest(new Contest(1538, new GregorianCalendar(2015, 2, 14), "ROSEIRA-SP", numbers21, 1000000, 20000, 10000, 5000, 2500, 1000000), true);
	}
}