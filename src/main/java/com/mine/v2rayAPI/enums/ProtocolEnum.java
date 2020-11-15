package com.mine.v2rayAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProtocolEnum {
    SS("ss://"),TROJAN("trojan://"),VMESS("vmess://");
    private String protocol;

    public static ProtocolEnum matchProtocol(String protocol){
        Assert.notNull(protocol,"传入的协议地址为null");
        ProtocolEnum[] values = values();
        return Arrays.stream(values).filter(p -> protocol.startsWith(p.getProtocol())).findFirst().get();
    }
}
