package com.romullogirardi.huntshark.model;

public class Main {

	public static void main(String[] args) {
		
		//Populating contests
		ContestManager.getInstance().populateContests();
		
//		//Computing last contest
//		int[] lastContest = {3, 6, 14, 21, 37, 46, 49, 56, 59, 60, 63, 64, 66, 74, 78, 79, 84, 92, 94, 95};
//		ContestManager.getInstance().computeLastContest(new Contest(1549, new GregorianCalendar(2015, 4, 25), "INTANHAEM-SP", lastContest, (float) 3554440.58, (float) 35315.39, (float) 3116.06, (float) 183.25, (float) 28.81, (float) 158919.25), true);
	}
}