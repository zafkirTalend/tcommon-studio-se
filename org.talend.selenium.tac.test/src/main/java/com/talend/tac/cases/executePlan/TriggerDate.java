package com.talend.tac.cases.executePlan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TriggerDate{
	public String minutes = "";
	public String hours = "";
	public String days = "";
	public String months = "";
	public String years = "";
	public TriggerDate getFuture(){
		TriggerDate time = new TriggerDate();
		Date date = new Date();
		DateFormat   df   =   new   SimpleDateFormat( "yyyy-MM-dd hh:MM:ss"); 
		date.setHours(date.getHours()+24);
		String s = df.format(date);//system date 
		s.replaceAll(" ", "-");
		s.replaceAll(":", "-");
		String after = (s.replaceAll(" ", "-").replaceAll(":", "-"));
		String dates[]=after.split("-");
		time.years +=dates[0] ;
		time.months +=dates[1];
		time.days += dates[2];
		time.hours += dates[3];
		time.minutes += dates[4];
		return time;
		
	}
	public TriggerDate getPast(){
		TriggerDate time = new TriggerDate();
		Date date = new Date();
		DateFormat   df   =   new   SimpleDateFormat( "yyyy-MM-dd hh:MM:ss"); 
		date.setHours(date.getHours()-24);
		String s = df.format(date);//system date 
		s.replaceAll(" ", "-");
		s.replaceAll(":", "-");
		String after = (s.replaceAll(" ", "-").replaceAll(":", "-"));
		String dates[]=after.split("-");
		time.years +=dates[0] ;
		time.months +=dates[1];
		time.days += dates[2];
		time.hours += dates[3];
		time.minutes += dates[4];
		return time;
	}
	public  static void main(String args[]){
		TriggerDate a = new TriggerDate();
		System.out.println(a.getFuture().days);
		System.out.println(a.getPast().days);
	}
	}
