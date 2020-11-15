package com.mine.v2rayAPI.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mine.v2rayAPI.cache.V2rayCache;
import com.mine.v2rayAPI.common.Const;
import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.enums.CacheEnum;
import com.mine.v2rayAPI.service.HttpService;
import com.mine.v2rayAPI.service.ProtocolHandler;
import com.mine.v2rayAPI.utils.V2rayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api")
public class ClashAPI {

    @Value("${rss}")
    private String rss;

    @Autowired
    private HttpService httpService;
    @Autowired
    private ProtocolHandler protocolHandler;

    @GetMapping(value = "clash",produces = MediaType.TEXT_PLAIN_VALUE)
    public String clash() throws IOException {
        if (V2rayCache.cacheValid(CacheEnum.CLASH)){
            return V2rayCache.getCacheData(CacheEnum.CLASH);
        }
        ResponseEntity<String> forObject = httpService.exechange(rss,HttpMethod.GET, null, null,String.class);
        Assert.state(HttpStatus.OK.equals(forObject.getStatusCode()),"根据订阅地址获取服务器信息错误");
        Assert.state(forObject.hasBody(),"订阅接口返回结果为空");
        String s = new String(Base64.getDecoder().decode(Objects.requireNonNull(forObject.getBody())), StandardCharsets.UTF_8);
        String[] split = Const.V2RAY_LF_SEP.split(s);
        List<ClashEntity> entities = protocolHandler.v2rayToClash(Arrays.asList(split));
        List<String> names = entities.stream().map(ClashEntity::getName).collect(Collectors.toList());
        Yaml yaml = new Yaml();
        Map<String, Object> map = V2rayUtil.getYamlTemplate(yaml);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(entities));
        map.put("proxies",jsonArray);
        List<Map<String,Object>> groups = (List<Map<String,Object>>) map.get("proxy-groups");
        groups.forEach(t -> {
            t.putIfAbsent("proxies",new ArrayList<>());
            ((List<String>) t.get("proxies")).addAll(names);
        });
        String data = yaml.dump(map);
        V2rayCache.putCache(CacheEnum.CLASH,data);
        return data;
    }

}
