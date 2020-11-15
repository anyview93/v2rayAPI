package com.mine.v2rayAPI.service;

import com.mine.v2rayAPI.entity.ClashEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProtocolConverter {

    default ClashEntity v2rayToClash(String protocal){
        return ClashEntity.builder().build();
    }

    default List<ClashEntity> v2rayToClash(List<String> protocal){
        return Optional.ofNullable(protocal)
                .map(l -> l.stream().map(this::v2rayToClash).collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

}
