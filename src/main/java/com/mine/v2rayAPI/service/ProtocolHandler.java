package com.mine.v2rayAPI.service;

import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.enums.ProtocolEnum;
import com.mine.v2rayAPI.service.impl.SSConverter;
import com.mine.v2rayAPI.service.impl.TrojanConverter;
import com.mine.v2rayAPI.service.impl.VmessConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProtocolHandler implements ProtocolConverter {

    private static final Map<ProtocolEnum,ProtocolConverter> PROTOCOL_HANDLER_MAPPING = new HashMap<>();

    static {
        PROTOCOL_HANDLER_MAPPING.put(ProtocolEnum.SS,new SSConverter());
        PROTOCOL_HANDLER_MAPPING.put(ProtocolEnum.TROJAN,new TrojanConverter());
        PROTOCOL_HANDLER_MAPPING.put(ProtocolEnum.VMESS,new VmessConverter());
    }

    @Override
    public ClashEntity v2rayToClash(String protocal) {
        ProtocolConverter converter = PROTOCOL_HANDLER_MAPPING.getOrDefault(ProtocolEnum.matchProtocol(protocal),
                new ProtocolConverter() {});
        return converter.v2rayToClash(protocal);
    }
}
