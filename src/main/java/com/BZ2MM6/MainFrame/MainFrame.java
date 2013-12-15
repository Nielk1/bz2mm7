package com.BZ2MM6.MainFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.BZ2MM6.Core.EventBusSet;
import com.BZ2MM6.Core.MainFrameTab;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8873515210914989758L;
	
	JTabbedPane tpMain;
	JPanel pStart;
	
	JButton btnStart;
	
	LoggingPanel pLogging;
	private List<MainFrameTab> tabList;
	
	public MainFrame()
	{
		tabList = new Vector<MainFrameTab>();
		
		// panels for frame
		tpMain = new JTabbedPane();
		pStart = new JPanel();
		
		// construct logging tab
		pLogging = new LoggingPanel();
		
		// add tabs to tab pane
		tpMain.addTab("Log",  pLogging);
		tpMain.addTab("EventBus", EventBusSet.getInstance().getTabPanel());
		
		// child elements for start panel
		btnStart = new JButton("Play");
		
		pStart.add(btnStart, BorderLayout.CENTER);
		
		// set up frame
		this.setTitle("Mod Manager 6");
		this.add(tpMain, BorderLayout.CENTER);
		this.add(pStart, BorderLayout.SOUTH);
		
		this.pack();
		this.setSize(Math.max(this.getWidth(),800), Math.max(this.getHeight(),600));
	}
	
	public void addTab(String name, Component component, int priority)
	{
		MainFrameTab newTab = new MainFrameTab(name, component, priority);
		tabList.add(newTab);
		Collections.sort(tabList);
		int index = tabList.indexOf(newTab);
		tpMain.insertTab(name, null, component, null, index);
	}

	public void selectFirstTab() {
		tpMain.setSelectedIndex(0);
	}
}
