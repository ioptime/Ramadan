package com.hijri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author http://www.cepmuvakkit.com
 */
public class HijriCalTest {
	private static int year, month, day;

	// public static void main(String[] args)
	{
		// System.out.println("  Gregorian to Hijri Converter This program calculates  Hijri date according to the global moonsighting criteria\n"
		// +
		// "Please keep in mind hijri dates starts with magrib prayer, this converter checks only the gregorian days after 12:00 pm.\n"+
		// "Input  Date must be  later that 1/1/2000 \n"+
		// "***This  code cannot used unless  resource is stated which is www.cepmuvakkit.com and cannot used for commercial purposes without  permission***");

		Date d = new Date();
		year = d.getYear();
		year = year + 1900;
		month = d.getMonth();
		month = month + 1;
		day = d.getDate();
		System.out.print(" " + year + " " + month + " " + " " + day);
		// br = new BufferedReader(new InputStreamReader(System.in));
		// try { year =Integer.parseInt(br.readLine()); } catch (IOException
		// ioe) { System.out.println("Wrong input!"); System.exit(1); }
		// System.out.print("Enter Gregorian Month = ");
		// try { month =Integer.parseInt(br.readLine());} catch (IOException
		// ioe) { System.out.println("Wrong input!"); System.exit(1); }
		// System.out.print("Enter Gregorian Day = ");
		// try { day =Integer.parseInt(br.readLine()); } catch (IOException ioe)
		// { System.out.println("Wrong input!"); System.exit(1); }

		HijriCalendar hijriCalendar = new HijriCalendar(year, month, day);
		String hijriDate = hijriCalendar.getHicriTakvim();
		System.out.println("HijriDate = " + hijriDate);

	}

	public String Islamicdate(int date, int month, int year) {

		HijriCalendar hijriCalendar = new HijriCalendar(year, month, date);
		return hijriCalendar.getHicriTakvim();
	}
}