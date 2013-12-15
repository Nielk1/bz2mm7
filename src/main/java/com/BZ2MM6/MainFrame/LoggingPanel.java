package com.BZ2MM6.MainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.BZ2MM6.Core.EventBusSet;
import com.BZ2MM6.Event.ConsoleCommandEvent;
import com.BZ2MM6.Event.ConsoleLogEvent;
import com.google.common.eventbus.Subscribe;

public class LoggingPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -136993420121624855L;

	private JTextArea txtLog;
	private JTextField txtInput;
	
	public LoggingPanel()
	{
		txtLog = new JTextArea();
		txtLog.setEditable(false);
		txtInput = new JTextField();
		
		txtInput.addActionListener(this);

		this.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(txtLog);
		scrollPane.setAutoscrolls(true);
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(txtInput, BorderLayout.SOUTH);

		EventBusSet.getInstance().getDefaultBus().register(this);
	}
	
	@Subscribe
	public void ConsoleLogEventListener(ConsoleLogEvent event)
	{
		final String text = event.toString();
		SwingUtilities.invokeLater(new Runnable()
		  {
		       public void run()
		       {
		           txtLog.append(text + "\n");
		       }
		  });
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(txtInput)) {
			EventBusSet.getInstance().getDefaultBus().post(new ConsoleCommandEvent(txtInput.getText()));
			txtInput.setText("");
		}
	}
}
