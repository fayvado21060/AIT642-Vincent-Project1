package ait642.Vincent.project1;
/** 
 * AIT642 Vincent Project1
 * 
 * @author Bonita Faye Vincent (fayvado21060)
 * 
 * This program calculates National Fire Danger Rating Indexes
 *
 */

import java.util.ArrayList;

public class MainSection {
	
	
	public static void main(String[] args) {
	
	/** Sets up Arrays for calculations  */	
		
	ArrayList<Double> aTableValues =
    new ArrayList<Double>();
	aTableValues.add(-0.185900);
	aTableValues.add(-.85900);
	aTableValues.add(-.059660);
	aTableValues.add(-.077373);
	
	ArrayList<Double> bTableValues =
    new ArrayList<Double>();
	bTableValues.add(30.0);
	bTableValues.add(19.2);
	bTableValues.add(13.8);
	bTableValues.add(22.5);
	
	ArrayList<Double> cTableValues =
    new ArrayList<Double>();
	cTableValues.add(4.5);
	cTableValues.add(12.5);
	cTableValues.add(27.5);
	
	ArrayList<Double> dTableValues =
	new ArrayList<Double>();
	dTableValues.add(16.0);
	dTableValues.add(10.0);
	dTableValues.add(7.0);
	dTableValues.add(5.0);
	dTableValues.add(4.0);
	dTableValues.add(3.0);
	
	/*  Prints out the Array Values to check Table Values */
	
	System.out.println(aTableValues.get(0));
	System.out.println(bTableValues.get(1));
	System.out.println(cTableValues.get(2));
	System.out.println(dTableValues.get(3));

	}
}


