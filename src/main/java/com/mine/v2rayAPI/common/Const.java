package com.mine.v2rayAPI.common;

import java.util.regex.Pattern;

public final class Const {
    private Const(){}

    public static final Pattern SS_V2RAY_SEP = Pattern.compile("://|@|:|#");

    public static final Pattern SS_V2RAY_CIPHER_SEP = Pattern.compile(":");

    public static final Pattern TROJAN_V2RAY_SEP = Pattern.compile("://|@|:|#");

    public static final Pattern VMESS_V2RAY_SEP = Pattern.compile("://");

    public static final Pattern V2RAY_LF_SEP = Pattern.compile("\\s");

    public static final String NAME_FORMATER = "%s - %s";
}
