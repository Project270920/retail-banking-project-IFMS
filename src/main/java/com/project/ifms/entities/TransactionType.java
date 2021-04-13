package com.project.ifms.entities;

import java.util.Arrays;
import java.util.Random;

public enum TransactionType
{
	CREDIT("credit"),
	DEBIT("debit");

	private String value;

	private TransactionType(String value) 					{
		this.value = value;
	}

	public static TransactionType fromValue(String value)	{
		for (TransactionType category : values())
			if (category.value.equalsIgnoreCase(value))
				return category;

		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	public static TransactionType getRandomTypes()			{
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
