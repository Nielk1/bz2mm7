package com.BZ2MM6.Event;

import com.BZ2MM6.Event.Event;

public class ConsoleLogEvent implements Event {

	String line;
	public ConsoleLogEvent(String line) {
		this.line = line;
	}
	
	public String toString()
	{
		return this.line;
	}

}
