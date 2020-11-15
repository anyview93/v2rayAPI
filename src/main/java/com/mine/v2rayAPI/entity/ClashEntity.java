package com.mine.v2rayAPI.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ClashEntity {
    private String name;
    private String server;
    private String port;
    private String type;
    private String cipher;
    private String password;
    private String plugin;
    @JSONField(name = "plugin-opts")
    private Map<String,String> pluginOpts;
    private String uuid;
    private String alterId;
    private Boolean udp;
    private Boolean tls;
    @JSONField(name = "skip-cert-verify")
    private Boolean skipCertVerify;
    private String network;
    @JSONField(name = "ws-path")
    private String wsPpath;
    @JSONField(name = "ws-headers")
    private Map<String,String> wsHeaders;
    private List<String> alpn;
    private String sni;
}
