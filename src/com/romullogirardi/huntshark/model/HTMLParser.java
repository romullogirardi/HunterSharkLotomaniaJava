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
	
	private static final float DEFAULT_REWARD_20_POINTS = 1000000;
	private static final float DEFAULT_REWARD_19_POINTS = 100000;
	private static final float DEFAULT_REWARD_18_POINTS = 10000;
	private static final float DEFAULT_REWARD_17_POINTS = 1000;
	private static final float DEFAULT_REWARD_16_POINTS = 100;
	private static final float DEFAULT_REWARD_0_POINTS = 1000000;

	//ATTRIBUTES
	private static Document htmlFile;
	
	//METHODS
	private static void parseHtmlFile() {
		try { 
			htmlFile = Jsoup.parse(new File(HTML_FILE_PATH), HTML_CHAR_CODE); 
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public static void readContestsFromHTMLFile() {
		
		Element table = htmlFile.select("table").get(0);
		for(Element row : table.select("tr")) {
			
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
			Elements rowElements = row.select("td");
			for(int columnIndex = 0; columnIndex < rowElements.size(); columnIndex++) {
				if(!rowElements.get(columnIndex).text().isEmpty()) {
					switch(columnIndex) {
						case CONTEST_ID_INDEX:
							try {
								id = Integer.parseInt(rowElements.get(columnIndex).text());
							} catch (Exception e) {
								System.out.println("ID inválido");
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
									System.out.println("Data inválida");
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
							float readReward20Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward20Points != 0) {
								reward20points = readReward20Points;
							}
							break;
						case CONTEST_REWARD_19_POINTS_INDEX:
							float readReward19Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward19Points != 0) {
								reward19points = readReward19Points;
							}
							break;
						case CONTEST_REWARD_18_POINTS_INDEX:
							float readReward18Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward18Points != 0) {
								reward18points = readReward18Points;
							}
							break;
						case CONTEST_REWARD_17_POINTS_INDEX:
							float readReward17Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward17Points != 0) {
								reward17points = readReward17Points;
							}
							break;
						case CONTEST_REWARD_16_POINTS_INDEX:
							float readReward16Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward16Points != 0) {
								reward16points = readReward16Points;
							}
							break;
						case CONTEST_REWARD_0_POINTS_INDEX:
							float readReward0Points = Float.parseFloat(rowElements.get(columnIndex).text());
							if(readReward0Points != 0) {
								reward0points = readReward0Points;
							}
							break;
					}
				}
			}
			
			//Adding a contest, if it´s valid
			if(id != -1) {
				ContestManager.getInstance().computeLastContest(new Contest(id, date, place, numbers, reward20points, reward19points, reward18points, reward17points, reward16points, reward0points), false);
			}
		}
	}
	
	public static void main(String args[]) { 

		//Parsing HTML file
		parseHtmlFile();
		
		//Reading contests from HTML file
		readContestsFromHTMLFile();
	}
}