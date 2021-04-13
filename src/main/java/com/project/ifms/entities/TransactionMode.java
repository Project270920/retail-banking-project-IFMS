package com.project.ifms.entities;

import java.util.Arrays;
import java.util.Random;

public enum TransactionMode
{
	UPI("upi"),
	IMPS("imps"),
	NEFT("neft"),
	
	CHEQUE("cheque"),
	DEMAND_DRAFT("dd"),

	WITHDRAWAL("withdrawal"),
	DEBIT_CARD("creditcard"),
	CREDIT_CARD("debitcard");
	
	private String value;

	private TransactionMode(String value) 					{
		this.value = value;
	}

	public static TransactionMode fromValue(String value)	{
		for (TransactionMode category : values())
			if (category.value.equalsIgnoreCase(value))
				return category;

		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	public static TransactionMode getRandomModes() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
