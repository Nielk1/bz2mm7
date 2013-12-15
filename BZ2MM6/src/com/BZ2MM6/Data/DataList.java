package com.BZ2MM6.Data;

import java.util.Vector;

public class DataList implements DataItem
{
	private Vector<DataItem> items;
	
	public DataList()
	{
		this.items = new Vector<DataItem>();
	}
	
	public String ToJSON()
	{
		String retVal = "[";
		
		for(int x = 0; x < items.size(); x++)
		{
			retVal += items.get(x).ToJSON();
			if(x + 1 < items.size()) retVal += ",";
		}
		
		retVal += "]";
		return retVal;
	}
}