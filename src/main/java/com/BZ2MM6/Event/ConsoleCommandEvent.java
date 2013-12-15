package com.BZ2MM6.Event;

import com.BZ2MM6.Event.Event;

public class ConsoleCommandEvent implements Event {

	String line;
	public ConsoleCommandEvent(String line) {
		this.line = line;
	}
	
	public String toString()
	{
		return this.line;
	}

}
