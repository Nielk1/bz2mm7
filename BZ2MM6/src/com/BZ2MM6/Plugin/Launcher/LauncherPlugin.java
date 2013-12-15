package com.BZ2MM6.Plugin.Launcher;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;
import net.xeoh.plugins.base.annotations.events.Shutdown;
import net.xeoh.plugins.remote.RemoteAPI;

import com.BZ2MM6.Core.CoreInterface;
import com.BZ2MM6.Core.EventBusSet;
import com.BZ2MM6.Core.IDPlugin;
import com.BZ2MM6.Event.ConsoleLogEvent;
import com.BZ2MM6.Event.Event;
import com.google.common.eventbus.EventBus;

@PluginImplementation
@Author(name="Nielk1")
@Version(version = 00001)//0.00.01
public class LauncherPlugin implements IDPlugin {

	EventBus mainBus;
	CoreInterface coreInterface;
	JPanel launcherPanel;
	JList lvModConfigs;
	ListModel lmModConfigs;
	
	public LauncherPlugin()
	{
		mainBus = EventBusSet.getInstance().getDefaultBus();
		
		launcherPanel = new JPanel();
		launcherPanel.setLayout(new BorderLayout());
		
		lmModConfigs = new DefaultListModel();
		lvModConfigs = new JList(lmModConfigs);
		
		launcherPanel.add(lvModConfigs, BorderLayout.CENTER);
	}
	
	/**
     * Magic. Will be called after the plugin is fully loaded.
     */
    @Init
    public void init() {
    	mainBus.post(new ConsoleLogEvent("LauncherPlugin Init"));
        //System.out.println("LauncherPlugin Init");
    }
    
    //@Override
    //public boolean initPlugin() {
    //	System.out.println("LauncherPlugin Init2");
    //    return true;
    //}

	//@Override
	//public String getName() {
	//	return "Launcher Plugin";
	//}
	
    //assume all other plugins are dead, even if you depend on them, die quick and alone!
    @Shutdown
    public void shutdown() {
    	System.out.println("LauncherPlugin Shutdown");
        //sayGoodbye()
    }

    //@Capabilities
    //public String[] caps() {
    //    return new String[] {"mimetype/big", "functionality:example"};
    //}

    // called when plugin of type RemoteAPI is loaded
    //@PluginLoaded
    //public void newPlugin(RemoteAPI plugin) {
    //    System.out.println("Detected new plugin " + plugin);
    //}

	@Override
	public void shareCoreInterface(CoreInterface coreInterface) {
		if(this.coreInterface != null) return;
		this.coreInterface = coreInterface;
		coreInterface.addTab("Launch", launcherPanel, 99999);
	}
	
	 // Display an icon and a string for each object in the list.

	 

}
