package com.example.administrator.japanhouse.im;

import java.util.ArrayList;
import java.util.List;

import io.rong.callkit.AudioPlugin;
import io.rong.callkit.VideoPlugin;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.LocationPlugin;
import io.rong.imlib.model.Conversation;

/**
 * Created by   admin on 2018/4/28.
 */

public class DetailsExtensionModule extends DefaultExtensionModule {
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  new ArrayList<>();
        pluginModules.add(new AudioPlugin());
        pluginModules.add(new VideoPlugin());
        pluginModules.add(new ImagePlugin());
        pluginModules.add(new BaiduLocationPlugin());
        pluginModules.add(new CollectPlugin());
        pluginModules.add(new HousePlugin());
        pluginModules.add(new MaskPlugin());
        return pluginModules;
    }
}
