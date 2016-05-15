package com.vs.util;

public class Messages {

	public static void welcomeMessage(){
		System.out.println("*********************************************");
		System.out.println("***Please have patience while initializing***");
	}
	public static void totalRecord(int no){
		System.out.println("***     Total record found : "+no+"        ***");
	}
	public static void processedRecord(int no, int t){
		System.out.println("***     Record Stored : "+no+"/"+t+"        ***");
	}
}
