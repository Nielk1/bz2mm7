package com.BZ2MM6.Core;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.BZ2MM6.Event.ConsoleLogEvent;
import com.BZ2MM6.MainFrame.MainFrame;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;
import net.xeoh.plugins.base.util.uri.ClassURI;

public class BZ2MM6Core {

	private Collection<IDPlugin> idPlugins;
	// private Vector<PluginInterface> pluginInterfaceList;
	private MainFrame frame;

	private volatile CoreInterface coreInterface;
	
	private volatile PluginManager pm;
	private volatile PluginManagerUtil pmu;
	
	public BZ2MM6Core() {
		pm = PluginManagerFactory.createPluginManager();
		pmu = new PluginManagerUtil(pm);
		Runtime.getRuntime().addShutdownHook(new ShutdownHook(pmu));
		
		showGUI();

		coreInterface = new CoreInterface(frame);

		EventBusSet
		.getInstance()
		.getDefaultBus()
		.post(new ConsoleLogEvent("Welcome to Mod Manager 6"));
		
		//EventBusSet
		//.getInstance()
		//.getDefaultBus()
		//.post(new ConsoleLogEvent("You better refactor this code before you continue to fix unsafe multi-thread access to core variables!\nMutexes needed. or volatile variables!"));
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				loadPlugins();
			}
		}).start();

		// showGUI2();

	}

	private void loadPlugins() {
		// EventBus pluginStatus =
		// EventBusSet.getInstance().getNamedBus("pluginstatus");

		EventBusSet
		.getInstance()
		.getDefaultBus()
		.post(new ConsoleLogEvent("Loading plugins"));
		
		//PluginManager pm = PluginManagerFactory.createPluginManager();
		pm.addPluginsFrom(ClassURI.CLASSPATH);
		// pm.addPluginsFrom(ClassURI.PLUGIN(PluginImpl.class));
		pm.addPluginsFrom(new File("plugins/").toURI());

		// pm.getPlugin(arg0, arg1)

		//PluginManagerUtil pmu = new PluginManagerUtil(pm);
		// final Collection<OutputService> plugins =
		// pmu.getPlugins(OutputService.class);

		// for (final OutputService s : plugins) {
		// s.doSomething();
		// }

		// final Collection<IDPlugin> idPlugins =
		// pmu.getPlugins(IDPlugin.class);
		idPlugins = pmu.getPlugins(IDPlugin.class);
		// pluginInterfaceList = new Vector<PluginInterface>();

		EventBusSet
		.getInstance()
		.getDefaultBus()
		.post(new ConsoleLogEvent("Loaded plugins"));
		
		// pluginInterfaceList.add(EventBusSet.getInstance());

		EventBusSet
		.getInstance()
		.getDefaultBus()
		.post(new ConsoleLogEvent("Sharing core interface with plugins"));
		
		for (IDPlugin p : idPlugins) {
			// PluginInterface plugin = (PluginInterface) p;

			// pluginInterfaceList.add(plugin);

			p.shareCoreInterface(coreInterface);
			
			EventBusSet
					.getInstance()
					.getDefaultBus()
					.post(new ConsoleLogEvent("Finalized plugin: "
							+ p.getClass().getCanonicalName()));
			// + plugin.getName()));
		}

		/*
		 * Vector<PluginInterface> inactivePlugins = new
		 * Vector<PluginInterface>();
		 * inactivePlugins.addAll(pluginInterfaceList); for (;;) { boolean
		 * somethingLoaded = false; for (int i = inactivePlugins.size() - 1; i
		 * >= 0;i--) { PluginInterface p = inactivePlugins.get(i); if
		 * (p.initPlugin()) // did init { EventBusSet .getInstance()
		 * .getDefaultBus() .post(new ConsoleLogEvent("Initialized plugin: " +
		 * p.getName())); somethingLoaded = true; inactivePlugins.remove(i); } }
		 * if (!somethingLoaded) break; } for (PluginInterface p :
		 * inactivePlugins) { EventBusSet .getInstance() .getDefaultBus()
		 * .post(new ConsoleLogEvent("Failed to initialize plugin: " +
		 * p.getName())); }
		 */
		
		EventBusSet
		.getInstance()
		.getDefaultBus()
		.post(new ConsoleLogEvent("Selecting highest priority tab"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.selectFirstTab();
	}

	private void showGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// private void showGUI2()
	// {
	// IDFrame frmGUI = new IDFrame(pluginInterfaceList);
	// frmGUI.setVisible(true);
	//
	// //frmGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // temporary
	// till we get a core window/console/service w/ shell-icon
	// }

	private class ShutdownHook extends Thread {
		PluginManagerUtil pmu;

		public ShutdownHook(PluginManagerUtil pmu)
		{
			this.pmu = pmu;
		}
		
		@Override
		public void run() {
			try {
				if (pmu != null) {
					pmu.shutdown();
				}

			} catch (IllegalStateException e) {
				// ignore
			}
		}
	}
}
