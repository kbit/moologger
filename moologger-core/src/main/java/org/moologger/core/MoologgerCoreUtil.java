package org.moologger.core;

import org.apache.commons.lang3.StringUtils;

public final class MoologgerCoreUtil {

    public static String buildAliasKey(String client, String protocol, String identifier) {
        return client + "+" + protocol + '=' + identifier;
    }

    public static String getClient(String key) {
        return StringUtils.substringBefore(key, "+");
    }

    public static String getProtocol(String key) {
        return StringUtils.substringBetween(key, "+", "=");
    }

    public static String getIdentifier(String key) {
        return StringUtils.substringAfter(key, "=");
    }

}