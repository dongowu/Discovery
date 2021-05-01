package com.nepxion.discovery.common.apollo.operation;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.google.common.collect.Sets;

public class ApolloOperation {
    @Autowired
    private Config apolloConfig;

    public String getConfig(String group, String serviceId) {
        return getConfig(group + "-" + serviceId);
    }

    public String getConfig(String key) {
        return apolloConfig.getProperty(key, null);
    }

    public ConfigChangeListener subscribeConfig(String group, String serviceId, ApolloSubscribeCallback apolloSubscribeCallback) {
        return subscribeConfig(group + "-" + serviceId, apolloSubscribeCallback);
    }

    public ConfigChangeListener subscribeConfig(String key, ApolloSubscribeCallback apolloSubscribeCallback) {
        ConfigChangeListener configListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                ConfigChange change = changeEvent.getChange(key);
                String config = change.getNewValue();
                apolloSubscribeCallback.callback(config);
            }
        };

        apolloConfig.addChangeListener(configListener, Sets.newHashSet(key));

        return configListener;
    }

    public void unsubscribeConfig(String group, String serviceId, ConfigChangeListener configListener) {
        unsubscribeConfig(group + "-" + serviceId, configListener);
    }

    public void unsubscribeConfig(String key, ConfigChangeListener configListener) {
        apolloConfig.removeChangeListener(configListener);
    }
}