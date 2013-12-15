package com.BZ2MM6.Core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.BZ2MM6.Event.Event;
import com.google.common.eventbus.Subscribe;

public class EventBusSetConfigTabPanel extends JPanel implements
		ActionListener, EventBusSetModifiedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3672267459682679283L;

	private EventBusSet eventBusSet;

	private JButton btnRefresh;
	private JList<String> lstListeners;
	private DefaultListModel<String> lstListenersModel;
	private JTextArea txtLog;

	public EventBusSetConfigTabPanel(EventBusSet eventBusSet) {
		this.eventBusSet = eventBusSet;

		this.setLayout(new BorderLayout());

		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
		lstListenersModel = new DefaultListModel<String>();
		lstListeners = new JList<String>(lstListenersModel);
		txtLog = new JTextArea();
		txtLog.setEditable(false);

		JPanel pnlLeft = new JPanel(new BorderLayout());
		// JPanel pnlRight = new JPanel(new BorderLayout());

		pnlLeft.add(BorderLayout.CENTER, new JScrollPane(lstListeners));
		pnlLeft.add(BorderLayout.SOUTH, btnRefresh);

		// pnlRight.add(BorderLayout.CENTER,txtLog);

		this.add(BorderLayout.WEST, pnlLeft);
		// this.add(BorderLayout.EAST,pnlRight);
		this.add(BorderLayout.CENTER, new JScrollPane(txtLog));

		refreshList();

		// this.setTitle("Event Bus Config");
		// this.pack();

		eventBusSet.addEventBusSetModifiedListener(this);
		eventBusSet.getDefaultBus().register(this.getEventHandler("Default"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(btnRefresh)) {
			refreshList();
		}
	}

	private void refreshList() {
		lstListenersModel.clear();
		String[] list = eventBusSet.getBusList().toArray(new String[0]);
		lstListenersModel.add(0, "[Default Bus]");
		for (int x = 0; x < list.length; x++) {
			lstListenersModel.add(x + 1, list[x]);
		}
	}

	@Override
	public void newEventBusAdded(String newBusName) {
		refreshList();
		eventBusSet.getNamedBus(newBusName).register(
				getEventHandler(newBusName));
	}

	private Object getEventHandler(final String string) {
		return new Object() {
			@Subscribe
			public void sendEvent(Event event) {
				final AbstractMap.SimpleEntry entry = new AbstractMap.SimpleEntry<String, Event>(
						string, event);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Entry<String, Event> event = entry;
						txtLog.append(event.getKey() + "  "
								+ event.getValue().getClass().toString()
								+ "  \"" + event.getValue().toString() + "\"\n");
					}
				});
			}
		};
	}
}
