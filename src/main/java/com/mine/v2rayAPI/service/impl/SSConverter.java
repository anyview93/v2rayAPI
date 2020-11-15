package com.mine.v2rayAPI.service.impl;

import com.mine.v2rayAPI.common.Const;
import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.service.ProtocolConverter;
import com.mine.v2rayAPI.utils.V2rayUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class SSConverter implements ProtocolConverter {

    @Override
    public ClashEntity v2rayToClash(String protocal) {
        String[] split = Const.SS_V2RAY_SEP.split(protocal);
        byte[] decode = Base64.getDecoder().decode(split[1]);
        final String s = new String(decode, StandardCharsets.UTF_8);
        String[] cipherInfo = Const.SS_V2RAY_CIPHER_SEP.split(s);
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
                .cipher(cipherInfo[0])
                .password(cipherInfo[1])
                .server(split[2])
                .port(port)
                .name(name)
                .build();
    }
}
