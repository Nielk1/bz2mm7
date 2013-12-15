package com.BZ2MM6.Data;

public class DataInteger implements DataItem {

	private int value;
	
	public DataInteger()
	{
		this.value = 0;
	}
	
	public DataInteger(int value)
	{
		this.value = value;
	}
	
	@Override
	public String ToJSON() {
		return "" + value;
	}


}
