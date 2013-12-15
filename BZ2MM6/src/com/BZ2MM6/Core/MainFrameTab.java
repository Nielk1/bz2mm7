package com.BZ2MM6.Core;

import java.awt.Component;

public class MainFrameTab implements Comparable<MainFrameTab> {
	private final String name;
	private final Component component;
	private final int priority;

	public MainFrameTab(String name, Component component, int priority) {
		this.name = name;
		this.component = component;
		this.priority = priority;
	}

	@Override
	public int compareTo(MainFrameTab other) {
		return Integer.valueOf(priority).compareTo(other.priority);
	}

	// also implement equals() and hashCode()
}