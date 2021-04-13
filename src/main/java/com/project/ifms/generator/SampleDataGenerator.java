package com.project.ifms.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.project.ifms.entities.Transaction;
import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SampleDataGenerator
{		
	private String AlphaNumericString = "0123456789";
	private final String[] branchCode = {"5050", "6329", "6655", "3577", "7702", "9018", "5056", "9548", "0997", "9204", "6920",
			"4829", "9516", "5089", "3187", "0989",	"9509",	"1217",	"5302",	"5785",	"1194",	"2330",	"8516", "6458",	"2575"};
	
	public String generateTransactionId()                  	{
		return "TX-" + generateRandomNumberofFixedLength(10);
	}	
	public String generateAccountNumber()                  	{
		return getBranchCode() + generateRandomNumberofFixedLength(6);
	}
	public String getTransactionDate(int start, int end)	{
		Integer year;
		List<Integer> years = new ArrayList<>();
		if(start == end)
			year = start;
		else
		{
			int base = start;
			for(int i=start;i<end;i++)
				years.add(base++);
			
			year = years.get(new Random().nextInt(years.size()));
		}
		
		List<Integer> oddDays = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12, 13, 14,15, 16, 17, 18, 19,20,21,22,23,24,25,26,27,28,29,30,31);
		List<Integer> evenDays = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12, 13, 14,15, 16, 17, 18, 19,20,21,22,23,24,25,26,27,28,29,30);
		List<Integer> leapYearDays = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12, 13, 14,15, 16, 17, 18, 19,20,21,22,23,24,25,26,27,28,29);
		List<Integer> nonLeapYearDays = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12, 13, 14,15, 16, 17, 18, 19,20,21,22,23,24,25,26,27,28);
		List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec");
		
		Integer day = 0;
		String month = months.get(new Random().nextInt(months.size()));
		
		switch(month)
		{
			case "Jan" :	day = oddDays.get(new Random().nextInt(oddDays.size()));
					 		break;

			case "Feb" : 	if (year%4 == 0)
								if (year%100 == 0)
									if (year%400 == 0)
										day = leapYearDays.get(new Random().nextInt(leapYearDays.size()));
									else
										day = nonLeapYearDays.get(new Random().nextInt(nonLeapYearDays.size()));
								else
									day = leapYearDays.get(new Random().nextInt(leapYearDays.size()));
				    	 	else
				    	 		day = leapYearDays.get(new Random().nextInt(leapYearDays.size()));

							break;
			
			case "Mar" : 	day = oddDays.get(new Random().nextInt(oddDays.size()));
					 		break;
					 
			case "Apr" : 	day = evenDays.get(new Random().nextInt(evenDays.size()));
					 		break;
			
			case "May" : 	day = oddDays.get(new Random().nextInt(oddDays.size()));
					 		break;
			
			case "Jun" : 	day = evenDays.get(new Random().nextInt(evenDays.size()));
					 		break;
			
			case "Jul" : 	day = oddDays.get(new Random().nextInt(oddDays.size()));
					 		break;
			
			case "Aug" :	day = oddDays.get(new Random().nextInt(oddDays.size()));
							break;
			
			case "Sep" : 	day = evenDays.get(new Random().nextInt(evenDays.size()));
							break;
			
			case "Oct" :	day = oddDays.get(new Random().nextInt(oddDays.size()));
					  		break;
			
			case "Nov" :	day = evenDays.get(new Random().nextInt(evenDays.size()));
					  		break;
			
			case "Dec" : day = oddDays.get(new Random().nextInt(oddDays.size()));
					  break;
			
			default: break;
		}
		return day.toString() + "-" + month.toString() + "-" + year.toString();  
	}
	public TransactionMode getTransactionMode()				{
		return TransactionMode.getRandomModes();
	}
	public TransactionType getTransactionType()				{
		return TransactionType.getRandomTypes();
	}
	public Double getTransactionAmount()					{
		return roundOffDoubleValue(getRandomDoubleinRange(0.0, 99999.999), 2);
	}
	public String getBranchCode()							{
		return getRandomValuefromList(branchCode);
	}
	
	public String generateRandomNumberofFixedLength(int n)			{
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++)
            sb.append(AlphaNumericString.charAt((int)(AlphaNumericString.length() * Math.random())));
  
        return sb.toString();
	}
	public double generateRandomNumberInDouble()            		{
		return 0.0 + new Random().nextDouble() * (99999.0 - 0.0);
	}
	public int getRandomIntegerinRange(int start, int end)          {
		return start + (int)Math.round(Math.random() * (end - start));
	}
	public double getRandomDoubleinRange(double start, double end)	{
		return start + (double)Math.round(Math.random() * (end - start));
	}
	public String getRandomValuefromList(String[] list)				{
        return list[new Random().nextInt(list.length)];
    }
	private double roundOffDoubleValue(double d, int places) 		{
		 
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }


	
	public Transaction getTransaction() throws ParseException
	{
		return new Transaction().setTransactionId(generateTransactionId())
				.setAssociatedAccount(generateAccountNumber())
				.setTransactionDate(new SimpleDateFormat("dd-MMM-yyyy").parse(getTransactionDate(2019,2020)))
				.setAmount(getTransactionAmount())
				.setTransactionType(getTransactionType())
				.setTransactonMode(getTransactionMode());
	}
	
	public List<Transaction> getTransactionList() throws ParseException
	{	
		List<Transaction> txList = new ArrayList<>();
		for(int i=0;i<100;i++)
			txList.add(new Transaction().setTransactionId(generateTransactionId())
										.setAssociatedAccount(generateAccountNumber())
										.setTransactionDate(new SimpleDateFormat("dd-MMM-yyyy").parse(getTransactionDate(2019,2020)))
										.setAmount(getTransactionAmount())
										.setTransactionType(getTransactionType())
										.setTransactonMode(getTransactionMode()));		
		return txList;
	}
}