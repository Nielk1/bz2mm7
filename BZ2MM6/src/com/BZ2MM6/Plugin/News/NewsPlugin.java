package com.BZ2MM6.Plugin.News;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.BZ2MM6.Core.CoreInterface;
import com.BZ2MM6.Core.EventBusSet;
import com.BZ2MM6.Core.IDPlugin;
import com.BZ2MM6.Event.ConsoleLogEvent;
import com.google.common.eventbus.EventBus;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;
import net.xeoh.plugins.base.annotations.events.Shutdown;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;
//import net.xeoh.plugins.remote.RemoteAPI;

@PluginImplementation
@Author(name="Nielk1")
@Version(version = 00001)//0.00.01
public class NewsPlugin implements IDPlugin {

	EventBus mainBus;
	CoreInterface coreInterface;
	JPanel newsPanel;
	
	public NewsPlugin()
	{
		mainBus = EventBusSet.getInstance().getDefaultBus();
		
		newsPanel = new JPanel();
		newsPanel.setLayout(new BorderLayout());
		
		//newsPanel.add(, BorderLayout.CENTER);
	}
	
	/**
     * Magic. Will be called after the plugin is fully loaded.
     */
    @Init
    public void init() {
    	mainBus.post(new ConsoleLogEvent("NewsPlugin Init"));
        //System.out.println("LauncherPlugin Init");
    }
    
    //assume all other plugins are dead, even if you depend on them, die quick and alone!
    @Shutdown
    public void shutdown() {
    	System.out.println("NewsPlugin Shutdown");
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
		coreInterface.addTab("News", newsPanel, 0);
	}
}
