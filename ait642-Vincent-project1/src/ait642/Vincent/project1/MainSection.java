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
import java.util.Scanner;


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

	/*  Sets up variables for inputs  */
	
    Double ipDry;
    Double ipWet;
    Double ipISnow;
    Double ipPrecip;
    Double ipWind;
    Double ipBUO;
    Double ipHerb;
    
    /* Sets up and Initializes output variables  */
    
    Double opDryFact = 0.0;
    Double opFineFuelMoist = 99.0;
    Double opAdjFuelMoist = 99.0;
    Double opGrassSpreadIndex = 0.0;
    Double opTimberSpredIndex = 0.0;
    Double opFireLoadRate = 0.0;
    Double opBuildUpIndex =0.0;
    
        
    System.out.println("Fine Fuel Moisture: " + opFineFuelMoist);
    System.out.println("Adjusted Fuel Moisture: " + opAdjFuelMoist);
    System.out.println("Drying Factor: "  + opDryFact);
    System.out.println("Fire Load Rating: " + opFireLoadRate);
    
    /* Gets the input data from the users */
    
    Scanner ip = new Scanner(System.in);
    
    System.out.println("Enter Dry Bulb Temperature");
    System.out.println();
    ipDry = ip.nextDouble();
    System .out.println("You entered " + ipDry + " for the Dry Bulb Temperature");
    System.out.println();
    
    
    System.out.println("Enter the Wet Bulb  Temperature");
    System.out.println();
    ipWet = ip.nextDouble();
    System .out.println("You entered " + ipWet + " for the Wet Bulb Temperature");
    System.out.println();
    
    System.out.println("Enter the Amount of Inches of Snow");
    System.out.println();
    ipISnow = ip.nextDouble();
    System .out.println("You entered " + ipISnow + " for the amount of inches of snow");
    System.out.println();
    
    System.out.println("Enter the Amount of Inches of Rain");
    System.out.println();
    ipPrecip = ip.nextDouble();
    System .out.println("You entered " + ipPrecip + " for the amount of inches of rain");
    System.out.println();
    
    System.out.println("Enter the Wind Speed");
    System.out.println();
    ipWind = ip.nextDouble();
    System .out.println("You entered " + ipWind + " for the Wind Speed");
    System.out.println();
    
    System.out.println("Enter the Last Value of the Build Up Index");
    System.out.println();
    ipBUO = ip.nextDouble();
    System .out.println("You entered " + ipBUO + " for the Last Value of the Build Up Index");
    System.out.println();
    
    System.out.println("Enter the Current Herb State");
    System.out.println("1=Cured or 2=Transition or 3=Green");
    System.out.println();
    ipHerb = ip.nextDouble();
    System .out.println("You entered " + ipHerb + " for the Current Herb State");
    System.out.println();
    
    /* closes ip. scanner once all input is obtained */
    
      ip.close();
      
      calcFireDanger(ipDry, ipWet, ipISnow, ipPrecip, ipWind, ipBUO, ipHerb,
                     opDryFact, opFineFuelMoist, opAdjFuelMoist, opGrassSpreadIndex, 
                     opTimberSpredIndex, opFireLoadRate, opBuildUpIndex);  
      
	}
     
    public static void calcFireDanger(Double ipDry, Double ipWet, Double ipISnow, Double ipPrecip,
    		                         Double ipWind, Double ipBUO, Double ipHerb,
    		                         Double opDryFact, Double opFineFuelMoist, Double opAdjFuelMoist,
    		                         Double opGrassSpreadIndex, Double opTimberSpredIndex,
    		                         Double opFireLoadRate, Double opBuildUpIndex)
    {  
    	
    Double calcDif = 0.0;	
      
    if (ipISnow > 0.0){
    	opGrassSpreadIndex = 0.0;
    	opTimberSpredIndex = 0.0;
    	
       if (ipPrecip > 0.1){
    		opBuildUpIndex = -50.0 * Math.log(1.0 -(1.0 - Math.exp(-ipBUO/50.0)) *
    			              	Math.exp( -1.175 * (ipPrecip - 0.1)));
    		 System.out.println("BUO: " + opBuildUpIndex);
        		
    		 if (opBuildUpIndex >= 0.1) {
    			 return; 
    		 }
    		 else {	 
    			 opBuildUpIndex = 0.0;
    			 return;
             }
       }	 
    	else {
    		return;
    	   	}
    }  
    else {
    	 calcDif = ipDry - ipWet;
    	 
    }	
	

//*  ends function    
    }
//* ends main section
}


