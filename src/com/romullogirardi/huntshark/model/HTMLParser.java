package com.romullogirardi.huntshark.model;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HTMLParser{ 
	
	//CONSTANTS
	private static final String htmlFilePath = "assets/D_LOTMAN.HTM";
	private static final String htmlCharCode = "UTF-8";
	
	//ATTRIBUTES
	private static Document htmlFile;
	
	//METHODS
	private static void parseHtmlFile() {
		try { 
			htmlFile = Jsoup.parse(new File(htmlFilePath), htmlCharCode); 
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public static void readContestsFromHTMLFile() {
//		String title = htmlFile.title(); 
//		System.out.println("Jsoup can also parse HTML file directly"); 
//		System.out.println("title : " + title);
		
		Element table = htmlFile.select("table").get(0);
		
		int counter = 0;
		
		for(Element tableRow : table.select("tr")) {
			for(Element rowRecord : tableRow.select("td")) {
				System.out.print(rowRecord.text() + "\t");
			}
			System.out.println();
			counter++;
		}
		
		System.out.println("Linhas percorridas = " + counter);
	}
	
	public static void main(String args[]) { 

		//Parsing HTML file
		parseHtmlFile();
		
		//Reading contests from HTML file
		readContestsFromHTMLFile();
	}
}