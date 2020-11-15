package com.mine.v2rayAPI.service.impl;

import com.mine.v2rayAPI.common.Const;
import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.service.ProtocolConverter;
import com.mine.v2rayAPI.utils.V2rayUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TrojanConverter implements ProtocolConverter {
    @Override
    public ClashEntity v2rayToClash(String protocal) {
        final String[] split = Const.TROJAN_V2RAY_SEP.split(protocal);
        String port = split[3];
        String name = split[4];
        try {
            name = URLDecoder.decode(name, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException e) {
            log.error("name URL解码失败",e);
        }
        name = V2rayUtil.concatName(name,port);
        return ClashEntity.builder()
                .type(split[0])
                .password(split[1])
                .server(split[2])
                .port(port)
                .name(name)
                .build();
    }
}
