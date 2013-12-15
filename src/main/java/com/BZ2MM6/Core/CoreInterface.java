package com.BZ2MM6.Core;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import com.BZ2MM6.Data.DataObject;
import com.BZ2MM6.Data.DataString;
import com.BZ2MM6.Event.ConsoleCommandEvent;
import com.BZ2MM6.Event.ConsoleLogEvent;
import com.BZ2MM6.MainFrame.MainFrame;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class CoreInterface {

	private MainFrame mainFrame;
	private DataObject data;
	private EventBus defaultBus;
	
	public CoreInterface(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.data = new DataObject();
		data.add("launch", new DataObject());
		((DataObject)data.get("launch")).add("exec", new DataString("bzone.exe"));
		
		defaultBus = EventBusSet.getInstance().getDefaultBus();
		defaultBus.register(this);
	}
	
	public void addTab(String name, Component component, int priority)
	{
		mainFrame.addTab(name, component, priority);
	}
	
	public DataObject getData()
	{
		return data;
	}
	
	@Subscribe
	public void ConsoleCommandEventListener(ConsoleCommandEvent event)
	{
		String text = event.toString();
		if(text.trim().equals("data"))
		{
			defaultBus.post(new ConsoleLogEvent(data.ToJSON()));
		}else if(text.trim().equals("help")){
			defaultBus.post(new ConsoleLogEvent("help // list commands"));
			defaultBus.post(new ConsoleLogEvent("data // list data object contents"));
		}
	}
}
