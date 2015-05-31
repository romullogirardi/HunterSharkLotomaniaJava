package com.romullogirardi.huntshark.model;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser{ 
	
	//CONSTANTS
	private static final String HTML_FILE_PATH = "assets/D_LOTMAN.HTM";
	private static final String HTML_CHAR_CODE = "UTF-8";
	
	private static final int CONTEST_ID_INDEX = 0;
	private static final int CONTEST_DATE_INDEX = 1;
	private static final int CONTEST_SELECTED_NUMBER_1_INDEX = 2;	
	private static final int CONTEST_SELECTED_NUMBER_2_INDEX = 3;	
	private static final int CONTEST_SELECTED_NUMBER_3_INDEX = 4;	
	private static final int CONTEST_SELECTED_NUMBER_4_INDEX = 5;	
	private static final int CONTEST_SELECTED_NUMBER_5_INDEX = 6;	
	private static final int CONTEST_SELECTED_NUMBER_6_INDEX = 7;	
	private static final int CONTEST_SELECTED_NUMBER_7_INDEX = 8;	
	private static final int CONTEST_SELECTED_NUMBER_8_INDEX = 9;	
	private static final int CONTEST_SELECTED_NUMBER_9_INDEX = 10;	
	private static final int CONTEST_SELECTED_NUMBER_10_INDEX = 11;	
	private static final int CONTEST_SELECTED_NUMBER_11_INDEX = 12;	
	private static final int CONTEST_SELECTED_NUMBER_12_INDEX = 13;	
	private static final int CONTEST_SELECTED_NUMBER_13_INDEX = 14;	
	private static final int CONTEST_SELECTED_NUMBER_14_INDEX = 15;	
	private static final int CONTEST_SELECTED_NUMBER_15_INDEX = 16;	
	private static final int CONTEST_SELECTED_NUMBER_16_INDEX = 17;	
	private static final int CONTEST_SELECTED_NUMBER_17_INDEX = 18;	
	private static final int CONTEST_SELECTED_NUMBER_18_INDEX = 19;	
	private static final int CONTEST_SELECTED_NUMBER_19_INDEX = 20;	
	private static final int CONTEST_SELECTED_NUMBER_20_INDEX = 21;	
	private static final int CONTEST_CITY_INDEX = 24;	
	private static final int CONTEST_STATE_INDEX = 25;	
	private static final int CONTEST_REWARD_20_POINTS_INDEX = 31;	
	private static final int CONTEST_REWARD_19_POINTS_INDEX = 32;	
	private static final int CONTEST_REWARD_18_POINTS_INDEX = 33;	
	private static final int CONTEST_REWARD_17_POINTS_INDEX = 34;	
	private static final int CONTEST_REWARD_16_POINTS_INDEX = 35;	
	private static final int CONTEST_REWARD_0_POINTS_INDEX = 36;
	
	public static final float DEFAULT_REWARD_20_POINTS = 1000000;
	public static final float DEFAULT_REWARD_19_POINTS = 30000;
	public static final float DEFAULT_REWARD_18_POINTS = 2000;
	public static final float DEFAULT_REWARD_17_POINTS = 150;
	public static final float DEFAULT_REWARD_16_POINTS = 25;
	public static final float DEFAULT_REWARD_0_POINTS = 100000;

	//ATTRIBUTES
	private static Document htmlFile;
	private static boolean isHtmlEnough = false;
	
	//METHODS
	private static void parseHtmlFile() {
		try { 
			htmlFile = Jsoup.parse(new File(HTML_FILE_PATH), HTML_CHAR_CODE); 
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public static void readContestsFromHTMLFile() {
		
		//Parsing HTML file
		parseHtmlFile();

		//Reading contests from HTML file		
		Element table = htmlFile.select("table").get(0);
		Elements tableRows = table.select("tr");
		for(int rowIndex = 0; rowIndex < tableRows.size(); rowIndex++) {
			
			//Setting contest variables with default values
			int id = -1;
			Calendar date = Calendar.getInstance();
			String place = new String();
			int[] numbers = new int[20];
			float reward20points = DEFAULT_REWARD_20_POINTS;
			float reward19points = DEFAULT_REWARD_19_POINTS;
			float reward18points = DEFAULT_REWARD_18_POINTS;
			float reward17points = DEFAULT_REWARD_17_POINTS;
			float reward16points = DEFAULT_REWARD_16_POINTS;
			float reward0points = DEFAULT_REWARD_0_POINTS;
			
			//Creating a contest by a table row
			Elements rowElements = tableRows.get(rowIndex).select("td");
			for(int columnIndex = 0; columnIndex < rowElements.size(); columnIndex++) {
				if(!rowElements.get(columnIndex).text().isEmpty()) {
					switch(columnIndex) {
						case CONTEST_ID_INDEX:
							try {
								id = Integer.parseInt(rowElements.get(columnIndex).text());
							} catch (Exception e) {
//								System.out.println("ID inválido");
							}
							break;
						case CONTEST_DATE_INDEX:
							String readDate = rowElements.get(columnIndex).text();
							if(readDate.length() == 10) {
								String dayStr = readDate.substring(0, 2);
								String monthStr = readDate.substring(3, 5);
								String yearStr = readDate.substring(6);
								try {
									int day = Integer.parseInt(dayStr);
									int month = Integer.parseInt(monthStr);
									int year = Integer.parseInt(yearStr);
									date = new GregorianCalendar(year, month - 1, day);
								} catch (Exception e) {
//									System.out.println("Data inválida");
								}
							}
							break;
						case CONTEST_SELECTED_NUMBER_1_INDEX:
							numbers[0] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_2_INDEX:
							numbers[1] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_3_INDEX:
							numbers[2] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_4_INDEX:
							numbers[3] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_5_INDEX:
							numbers[4] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_6_INDEX:
							numbers[5] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_7_INDEX:
							numbers[6] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_8_INDEX:
							numbers[7] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_9_INDEX:
							numbers[8] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_10_INDEX:
							numbers[9] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_11_INDEX:
							numbers[10] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_12_INDEX:
							numbers[11] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_13_INDEX:
							numbers[12] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_14_INDEX:
							numbers[13] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_15_INDEX:
							numbers[14] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_16_INDEX:
							numbers[15] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_17_INDEX:
							numbers[16] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_18_INDEX:
							numbers[17] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_19_INDEX:
							numbers[18] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_SELECTED_NUMBER_20_INDEX:
							numbers[19] = Integer.parseInt(rowElements.get(columnIndex).text());
							break;
						case CONTEST_CITY_INDEX:
							place = rowElements.get(columnIndex).text();
							break;
						case CONTEST_STATE_INDEX:
							place += "/" + rowElements.get(columnIndex).text();
							break;
						case CONTEST_REWARD_20_POINTS_INDEX:
							String readReward20PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward20Points = Float.parseFloat(readReward20PointsStr);
							if(readReward20Points != 0) {
								reward20points = readReward20Points;
							}
							break;
						case CONTEST_REWARD_19_POINTS_INDEX:
							String readReward19PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward19Points = Float.parseFloat(readReward19PointsStr);
							if(readReward19Points != 0) {
								reward19points = readReward19Points;
							}
							break;
						case CONTEST_REWARD_18_POINTS_INDEX:
							String readReward18PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward18Points = Float.parseFloat(readReward18PointsStr);
							if(readReward18Points != 0) {
								reward18points = readReward18Points;
							}
							break;
						case CONTEST_REWARD_17_POINTS_INDEX:
							String readReward17PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward17Points = Float.parseFloat(readReward17PointsStr);
							if(readReward17Points != 0) {
								reward17points = readReward17Points;
							}
							break;
						case CONTEST_REWARD_16_POINTS_INDEX:
							String readReward16PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward16Points = Float.parseFloat(readReward16PointsStr);
							if(readReward16Points != 0) {
								reward16points = readReward16Points;
							}
							break;
						case CONTEST_REWARD_0_POINTS_INDEX:
							String readReward0PointsStr = toFloatStringFormat(rowElements.get(columnIndex).text());
							float readReward0Points = Float.parseFloat(readReward0PointsStr);
							if(readReward0Points != 0) {
								reward0points = readReward0Points;
							}
							break;
					}
				}
			}
			
			//Adding a contest, if it´s valid
			boolean isLastContest = ((rowIndex == (tableRows.size() - 1)) && isHtmlEnough) ? true : false;
			if(id != -1) {
				ContestManager.getInstance().computeLastContest(new Contest(id, date, place, numbers, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points, false), isLastContest);
			}
		}
		
		//Reading additional contests, if necessary
		if(!isHtmlEnough) {
			readAdditionalContests();
		}
	}
	
	private static String toFloatStringFormat(String notFloatStringFormat) {
		String temp = notFloatStringFormat.replace(".", "");
		String floatStringFormat = temp.replace(",", ".");
		return floatStringFormat;
	}
	
	private static void readAdditionalContests() {
		int[] numbers1 = {13, 27, 28, 30, 34, 39, 41, 47, 48, 51, 54, 57, 67, 74, 75, 83, 84, 93, 96, 99};
		ContestManager.getInstance().computeLastContest(new Contest(1556, new GregorianCalendar(2015, 4, 20), "IPORÁ/GO", numbers1, (float) 0, (float) 19642.18, (float) 1976.57, (float) 135.18, (float) 23.25, (float) 0, false), false);
		int[] numbers2 = {0, 2, 6, 8, 13, 17, 23, 29, 33, 36, 40, 42, 47, 49, 57, 67, 75, 80, 91, 95};
		ContestManager.getInstance().computeLastContest(new Contest(1557, new GregorianCalendar(2015, 4, 23), "IPORÁ/GO", numbers2, (float) 0, (float) 20786.95 , (float) 1491.05 , (float) 116.18, (float) 20.44, (float) 176689.07, false), false);
		int[] numbers3 = {4, 14, 15, 21, 25, 28, 29, 31, 49, 53, 55, 56, 62, 67, 70, 71, 80, 83, 85, 92};
		ContestManager.getInstance().computeLastContest(new Contest(1558, new GregorianCalendar(2015, 4, 27), "JAPERI/RJ", numbers3, (float) 0, (float) 54753.28 , (float) 2566.56 , (float) 182.67, (float) 28.95, (float) 164259.86, true), true);
	}
}