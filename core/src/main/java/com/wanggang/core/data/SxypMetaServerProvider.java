package com.wanggang.core.data;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.core.spi.MetaServerProvider;
import com.ctrip.framework.foundation.Foundation;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 14:21
 **/
public class SxypMetaServerProvider implements MetaServerProvider {
    public static final int ORDER = MetaServerProvider.LOWEST_PRECEDENCE;
    private static final Logger logger = LoggerFactory.getLogger(SxypMetaServerProvider.class);

    private final String metaServerAddress;

    public SxypMetaServerProvider() {
        metaServerAddress = initMetaServerAddress();
    }

    private String initMetaServerAddress() {
        // 1. Get from System Property
        String metaAddress = System.getProperty(ConfigConsts.APOLLO_META_KEY);
        if (Strings.isNullOrEmpty(metaAddress)) {
            // 2. Get from OS environment variable, which could not contain dot and is normally in UPPER case
            metaAddress = System.getenv("APOLLO_META");
        }
        if (Strings.isNullOrEmpty(metaAddress)) {
            // 3. Get from server.properties
            metaAddress = Foundation.server().getProperty(ConfigConsts.APOLLO_META_KEY, null);
        }
        if (Strings.isNullOrEmpty(metaAddress)) {
            // 4. Get from app.properties
            metaAddress = Foundation.app().getProperty(ConfigConsts.APOLLO_META_KEY, null);
        }

        if (Strings.isNullOrEmpty(metaAddress)) {
            return "http://106.12.25.204:8080";
        }

        metaAddress = metaAddress.trim();

        return metaAddress;
    }

    @Override
    public String getMetaServerAddress(Env targetEnv) {
        //for default meta server provider, we don't care the actual environment
        return metaServerAddress;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
