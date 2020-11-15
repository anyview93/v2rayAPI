package com.mine.v2rayAPI.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CacheEntity<T> {
    private T data;
    private LocalDateTime expireTime;
}
