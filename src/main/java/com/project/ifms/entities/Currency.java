package com.project.ifms.entities;

import java.util.Arrays;

public enum Currency
{
	INR("inr"),
	USD("usd");
	
	private String value;

	private Currency(String value)					{
		this.value = value;
	}

	public static Currency fromValue(String value)	{
		for (Currency category : values())
			if (category.value.equalsIgnoreCase(value))
				return category;

		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
}
