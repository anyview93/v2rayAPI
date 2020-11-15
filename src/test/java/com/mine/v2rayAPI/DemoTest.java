package com.mine.v2rayAPI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mine.v2rayAPI.entity.ClashEntity;
import com.mine.v2rayAPI.service.impl.SSConverter;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class DemoTest {

    @Test
    public void regex(){
        String str = "ss://YWVzLTI1Ni1nY206OG42cHdBY3JydjJwajZ0RlkycDNUYlE2@37.120.194.99:33992#github.com/freefq%20-%20%E7%BD%97%E9%A9%AC%E5%B0%BC%E4%BA%9A%2099";
        String regex = "://|@|:|#";
        String[] strings = Pattern.compile(regex).split(str);
        String[] split = str.split(regex);
        System.out.println(Arrays.toString(split));
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void ssConvert(){
        String str = "ss://YWVzLTI1Ni1nY206OG42cHdBY3JydjJwajZ0RlkycDNUYlE2@37.120.194.99:33992#github.com/freefq%20-%20%E7%BD%97%E9%A9%AC%E5%B0%BC%E4%BA%9A%2099";
        SSConverter converter = new SSConverter();
        ClashEntity clashEntity = converter.v2rayToClash(str);
        System.out.println(clashEntity);
    }

    @Test
    public void yaml(){
        Yaml yaml = new Yaml();
        String str = "ss://YWVzLTI1Ni1nY206OG42cHdBY3JydjJwajZ0RlkycDNUYlE2@37.120.194.99:33992#github.com/freefq%20-%20%E7%BD%97%E9%A9%AC%E5%B0%BC%E4%BA%9A%2099";
        SSConverter converter = new SSConverter();
        ClashEntity clashEntity = converter.v2rayToClash(str);
        String s = JSON.toJSONString(clashEntity);
        System.out.println(s);
        Map<String,Object> jsonObject = JSON.parseObject(s, Map.class);
        System.out.println(jsonObject);
        String dump = yaml.dump(jsonObject);
        System.out.println(dump);
    }
}
