package com.BZ2MM6.Data;

public class DataString implements DataItem {

	private String value;
	
	public DataString()
	{
		this.value = "";
	}
	
	public DataString(String value)
	{
		this.value = value;
	}
	
	@Override
	public String ToJSON() {
		return "\"" + value
				.replace("\\", "\\\\")
				.replace("\n", "\\n")
				.replace("\t", "\\t")
				.replace("\t","\\t") + "\"";
	}

}
