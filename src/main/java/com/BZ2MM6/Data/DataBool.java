package com.BZ2MM6.Data;

public class DataBool implements DataItem {

	private boolean value;
	
	public DataBool()
	{
		this.value = false;
	}
	
	public DataBool(boolean value)
	{
		this.value = value;
	}
	
	@Override
	public String ToJSON() {
		return value ? "true" : "false";
	}

}
