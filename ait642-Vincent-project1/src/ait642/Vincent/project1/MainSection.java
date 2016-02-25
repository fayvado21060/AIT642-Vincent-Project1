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
    Double opTimberSpreadIndex = 0.0;
    Double opFireLoadRate = 0.0;
    Double opBuildUpIndex =0.0;
    
    ArrayList<Double> opValues =
            new ArrayList<Double>();
        	opValues.add(opDryFact);
        	opValues.add(opFineFuelMoist);
        	opValues.add(opAdjFuelMoist);
        	opValues.add(opGrassSpreadIndex);
        	opValues.add(opTimberSpreadIndex);
        	opValues.add(opFireLoadRate);
        	opValues.add(opBuildUpIndex);
    
       
    /* Gets the input data from the users */
    
    Scanner ip = new Scanner(System.in);
    
    System.out.println("Enter Dry Bulb Temperature");
    ipDry = ip.nextDouble();
    System.out.println("Enter the Wet Bulb  Temperature");
    ipWet = ip.nextDouble();
    System.out.println("Enter the Amount of Inches of Snow");
    ipISnow = ip.nextDouble();
    System.out.println("Enter the Amount of Inches of Rain");
    ipPrecip = ip.nextDouble();
    System.out.println("Enter the Wind Speed");
    ipWind = ip.nextDouble();
    System.out.println("Enter the Last Value of the Build Up Index");
    ipBUO = ip.nextDouble();
    System.out.println("Enter the Current Herb State");
    System.out.println("1=Cured or 2=Transition or 3=Green");
    ipHerb = ip.nextDouble();
    
    System.out.println("You entered " + ipDry + " for the Dry Bulb Temperature");
    System.out.println("You entered " + ipWet + " for the Wet Bulb Temperature");
    System.out.println("You entered " + ipISnow + " for the amount of inches of snow");
    System.out.println("You entered " + ipPrecip + " for the amount of inches of rain");
    System.out.println("You entered " + ipWind + " for the Wind Speed");
    System.out.println("You entered " + ipBUO + " for the Last Value of the Build Up Index");
    System.out.println("You entered " + ipHerb + " for the Current Herb State");
    System.out.println();
    
    /* closes ip. scanner once all input is obtained */
    
      ip.close();
      
 //*     
 //*  Call to function to calculate Fire Danger                 
 //*                  
      
      calcFireDanger(ipDry, ipWet, ipISnow, ipPrecip, ipWind, ipBUO, ipHerb,
                        opValues); 
      
 //*
 //*  Prints output values  
 //*     
      
      System.out.println();
      System.out.println("Drying Factor: "  + opValues.get(0));
      System.out.println("Fine Fuel Moisture: " + opValues.get(1));
      System.out.println("Adjusted Fuel Moisture: " + opValues.get(2));
      System.out.println("Grass Spread Index: " + opValues.get(3));
      System.out.println("Timber Spread Index: " + opValues.get(4));
      System.out.println("Fire Load Rating: " + opValues.get(5));
      System.out.println("Build Up Index: " + opValues.get(6));
      
	}
     
    //*		                         
    //*  Function to calculate Fire Danger   		                        
    //*		                          
	
	 public static void calcFireDanger(Double ipDry, Double ipWet, Double ipISnow, Double ipPrecip,
    		                         Double ipWind, Double ipBUO, Double ipHerb, ArrayList<Double> calcValues)
    {  
    	
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
    	
  //* Initializes values 
    	
    	Double calcDryFact = 0.0;
        Double calcFineFuelMoist = 0.0;
        Double calcAdjFuelMoist = 0.0;
        Double calcGrassSpreadIndex = 0.0;
        Double calcTimberSpreadIndex = 0.0;
        Double calcFireLoadRate = 0.0;
        Double calcBuildUpIndex =0.0;
    	
 //*  Set values of index used for passing function values using an array       
        
        int iDryFact = 0;
        int iFineFuelMoist = 1;
        int iAdjFuelMoist = 2;
        int iGrassSpreadIndex = 3;
        int iTimberSpreadIndex = 4;
        int iFireLoadRate = 5;
        int iBuildUpIndex = 6;
    	     	
        Double calcDif = 0.0;	
      
 //*
 //*  If snow is on the ground then Grass and Timber Spread are set to zero
 //* 
        
    if (ipISnow > 0.0){
    	calcGrassSpreadIndex = 0.0;
    	calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    	calcTimberSpreadIndex = 0.0;
    	calcValues.set(iTimberSpreadIndex, calcTimberSpreadIndex);
    	
 //*
 //*  If precipitation exceeds .1 inches the Build Up Index are reduced 
 //*    
    	
       if (ipPrecip > 0.1){
    		calcBuildUpIndex = -50.0 * Math.log(1.0 -(1.0 - Math.exp(-ipBUO/50.0)) *
    			              	Math.exp( -1.175 * (ipPrecip - 0.1)));
    		calcValues.set(iBuildUpIndex, calcBuildUpIndex);
    		 System.out.println("BUO: " + calcBuildUpIndex);
        		
    		 if (calcBuildUpIndex >= 0.1) {
    			 return; 
    		 }
    		 else {	 
    			 calcBuildUpIndex = 0.0;
    			 return;
             }
       }	 
    	else {
    		return;
    	   	}
    }  
    else {
 //*
 //*  If there is no snow on the ground the spread indexes are computed
 //*  Fine Fuel Moist is calculated and then used to calculate Drying Factor
 //*  
    	calcDif = ipDry - ipWet;
    	 
    	 for (int i = 1; i <= 3; i++) {
    		 if (calcDif - cTableValues.get(i-1) < 0){
    	    	 calcFineFuelMoist = bTableValues.get(i-1)* 
    		        Math.exp(aTableValues.get(i-1)*calcDif);
    	    	 calcValues.set(iFineFuelMoist, calcFineFuelMoist);
    	    	 i = 4;
    	    	 }
    		 else {
    			 if ((i > 3)){
    				 calcFineFuelMoist = bTableValues.get(i)* 
    		    		 Math.exp(aTableValues.get(i)*calcDif);
    				 calcValues.set(iFineFuelMoist, calcFineFuelMoist);
    		    	    	 }
    			 }	 
    		 }
    	  for (int j = 1; j <= 6; j++){
    		  if (calcFineFuelMoist - dTableValues.get(j-1) > 0){
    			  calcDryFact = (double) (j - 1);
    			  calcValues.set(iDryFact,calcDryFact);
    		  }
    		  else {
    			  if ((j >= 6)){
    				 calcDryFact = 7.0; 
    				 calcValues.set(iDryFact,calcDryFact);
    			  }
    		  }
    	  }
 //*   	  
 //*  Sets values or calculates Fine Fuel Moisture   	  
 //*   	  
    	  if ((calcFineFuelMoist <= 1.0)){
    		  calcFineFuelMoist = 1.0;
    		  calcValues.set(iFineFuelMoist, calcFineFuelMoist);
    	  }
    	  else {
    		  calcFineFuelMoist = calcFineFuelMoist + ((ipHerb -1.0) *5);
    		  calcValues.set(iFineFuelMoist, calcFineFuelMoist);
    	  }
 //*   	  
 //*   If Precipitation exceeds .10 inches the Build Up Index is reduced by an 
 //*   amount equal to the rainfall   	  
 //*   	     	  
    	  if ((ipPrecip - 0.1 > 0)){
    		  calcBuildUpIndex = -50.0 * Math.log(1.0 -(1.0 - Math.exp(-ipBUO/50.0)) *
		              	Math.exp( -1.175 * (ipPrecip - 0.1)));
    		  calcValues.set(iBuildUpIndex, calcBuildUpIndex);
 
      		  if (calcBuildUpIndex >= 0){
    			  calcBuildUpIndex = 0.0;}
    		  else {
    			  calcBuildUpIndex = calcBuildUpIndex + calcDryFact;
    			  calcValues.set(iBuildUpIndex, calcBuildUpIndex);
    		  }
    	  }
 //*    		  
 //*  Test to see if he Fuel Moistures are greater that 30%, if they are their index values are   		  
 //*  set to 0 		  
 //*
    	  calcAdjFuelMoist = (0.9 * calcFineFuelMoist) + .5 + (Math.exp(9.5)) * 
    			  (calcBuildUpIndex /50.0);
    	  calcValues.set(iAdjFuelMoist, calcAdjFuelMoist);
    	  
    	  if (calcAdjFuelMoist >= 30.0){
    		  if (calcFineFuelMoist >= 30.0){
    			  calcGrassSpreadIndex = 1.0;
    			  calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    			  calcTimberSpreadIndex = 1.0;
    			  calcValues.set(iTimberSpreadIndex, calcTimberSpreadIndex);
    			  return;
    		  }
    		  else {
    			  calcTimberSpreadIndex = 1.0;
    			  calcValues.set(iTimberSpreadIndex, calcTimberSpreadIndex);
 //*
 //* If Winds speed are greater than 14 Timber and Grass Spreads are calculated
 //*   			  
    			  
    			  if (ipWind >= 14.0){
    				  calcGrassSpreadIndex = (0.00918 * (ipWind + 14) *
    						  Math.pow((33.0 - calcFineFuelMoist), 1.65) -3.0);
    				  if (calcGrassSpreadIndex > 99.0) {
    					  calcGrassSpreadIndex = 99.0;
    					  calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    				  }
    				  else {
 //*   					  
 //* Once spreads are calculated Fired Load Rate is calculated
 //*
    					  
    					 if (calcTimberSpreadIndex > 0.0) {    				
    					     if (calcBuildUpIndex > 0.0){
    							 calcFireLoadRate = (1.75 * Math.log10(calcTimberSpreadIndex) +
    										  0.32 * Math.log10(calcBuildUpIndex) - 1.640);
    							 calcValues.set(iFireLoadRate, calcFireLoadRate);
    								  
    								if (calcFireLoadRate > 0.0){
    									  calcFireLoadRate = Math.pow(calcFireLoadRate, 10.0);
    									  calcValues.set(iFireLoadRate, calcFireLoadRate);
    								  }
    								  else {
    									  calcFireLoadRate = 0.0;
    									  calcValues.set(iFireLoadRate, calcFireLoadRate);
    									  return;
    								       }
    								 }
    							else {
    							 return;
    								 }
    					 } 
    					 
    				  else {
    					  return;
    				  }
    		          }
    			   }
    			  else {
    				  calcGrassSpreadIndex = (0.01312 * (ipWind +6) * 
    						  Math.pow((33.0 - calcFineFuelMoist), 1.65) -3.0);
    				  calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    			  }
    			  }
    		   }
    	  else {
    		  if (ipWind >= 14.0){
    			  calcTimberSpreadIndex = (0.01312 * (ipWind +6) * 
						  Math.pow((33.0 - calcAdjFuelMoist), 1.65) -3.0);
    			  calcGrassSpreadIndex = (0.00918 * (ipWind + 14) *
						  Math.pow((33.0 - calcFineFuelMoist), 1.65) -3.0);
    			  calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    			  calcValues.set(iTimberSpreadIndex, calcTimberSpreadIndex);
    			  
    			  
    			  if (calcGrassSpreadIndex > 99.00){
    				  calcGrassSpreadIndex = 99.00;
    				  calcValues.set(iGrassSpreadIndex, calcGrassSpreadIndex);
    				  if (calcTimberSpreadIndex > 99.00){
    					  calcTimberSpreadIndex = 99.00;
    					  calcValues.set(iTimberSpreadIndex, calcTimberSpreadIndex);    					  
    				  }
    				  else {
    					  if ((calcTimberSpreadIndex < 0.0) 
    						  & (calcBuildUpIndex < 0.0)) {
    						  return;
    					  }	  
    				     else {
    				    	 calcFireLoadRate = (1.75 * Math.log10(calcTimberSpreadIndex) +
									  0.32 * Math.log10(calcBuildUpIndex) - 1.640);
    				    	 calcValues.set(iFireLoadRate, calcFireLoadRate);
    				    	 
    				    	 if (calcFireLoadRate <= 0.0){
    				    		 calcFireLoadRate = 0.0;
    				    		 return;
    				    	 }
    				    	 else {
    				    		 calcFireLoadRate = Math.pow(10,calcFireLoadRate);
    				    		 calcValues.set(iFireLoadRate, calcFireLoadRate);
    				    		 return;
    				    	 }
    					  }
    				  }
    			  }
    		  }
    	  }
      }
      
//*  ends function    
    }
//* ends main section
}


