package com.BZ2MM6.Core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import com.google.common.eventbus.EventBus;

public class EventBusSet {
	private static EventBusSet instance;
	private EventBus defaultEventBus;
	private Map<String, EventBus> namedEventBus;
	
	private EventBusSetConfigTabPanel pnlSettings;
	
	private LinkedList<EventBusSetModifiedListener> listenerList;
	
	private EventBusSet()
	{
		defaultEventBus = new EventBus();
		namedEventBus = new HashMap<String, EventBus>();
		
		listenerList = new LinkedList<EventBusSetModifiedListener>();
		
		pnlSettings = new EventBusSetConfigTabPanel(this);
	}
	
	public static EventBusSet getInstance()
	{
		if(instance == null)
			instance = new EventBusSet();
		return instance;
	}
	
	public EventBus getDefaultBus()
	{
		return defaultEventBus;
	}
	
	public EventBus getNamedBus(String name)
	{
		if(!namedEventBus.containsKey(name))
		{
			namedEventBus.put(name, new EventBus(name));
			fireNewBusEvent(name);
		}
		return namedEventBus.get(name);
	}
	
	public Set<String> getBusList()
	{
		return namedEventBus.keySet();
	}

	public JPanel getTabPanel()
	{
		return (JPanel)pnlSettings;
	}
	
	public void addEventBusSetModifiedListener(EventBusSetModifiedListener listener)
	{
		listenerList.add(listener);
	}
	
	private void fireNewBusEvent(String newBusName)
	{
		Iterator<EventBusSetModifiedListener> iterator = listenerList.iterator();
		
		while (iterator.hasNext()) {
			iterator.next().newEventBusAdded(newBusName);
		}
	}
}
