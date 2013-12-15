package com.BZ2MM6.Data;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class DataObject implements DataItem {

	private HashMap<String,DataItem> data;
	
	public DataObject()
	{
		this.data = new HashMap<String,DataItem>();
	}
	
	public DataItem add(String key, DataItem item)
	{
		return data.put(key, item);
	}
	
	public DataItem get(String key)
	{
		return data.get(key);
	}

	@Override
	public String ToJSON() {
		String retVal = "{";
		Object[] entries = data.entrySet().toArray();
		for(int x = 0; x < entries.length; x++)
		{
			Entry<String,DataItem> entry = (Entry<String,DataItem>)entries[x]; 
			retVal += "\"" + entry.getKey() + "\":";
			retVal += entry.getValue().ToJSON();
			if(x + 1 < entries.length - 1)
				retVal += ",";
		}
		retVal += "}";
		return retVal;
	}
}