package com.mine.v2rayAPI.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mine.v2rayAPI.common.Const;
import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.service.ProtocolConverter;
import com.mine.v2rayAPI.utils.V2rayUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

@Slf4j
public class VmessConverter implements ProtocolConverter {
    @Override
    public ClashEntity v2rayToClash(String protocal) {
        String[] split = Const.VMESS_V2RAY_SEP.split(protocal);
        String s = new String(Base64.getDecoder().decode(split[1]), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(s);
        String name = jsonObject.getString("ps");
        String port = jsonObject.getString("port");
        name = V2rayUtil.concatName(name,port);
        HashMap<String, String> wsHeads = new HashMap<>();
        wsHeads.put("Host",jsonObject.getString("host"));
        return ClashEntity.builder()
                .name(name)
                .server(jsonObject.getString("add"))
                .port(port)
                .uuid(jsonObject.getString("id"))
                .alterId(jsonObject.getString("aid"))
                .network(jsonObject.getString("net"))
                .type(jsonObject.getString("none"))
                .type(split[0])
                .cipher("auto")
                .udp("udp".equals(jsonObject.getString("udp")))
                .tls("tls".equals(jsonObject.getString("tls")))
                .wsPpath(jsonObject.getString("path"))
                .wsHeaders(wsHeads)
                .build();
    }
}
