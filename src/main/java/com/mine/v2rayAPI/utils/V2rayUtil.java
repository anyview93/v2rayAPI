package com.mine.v2rayAPI.utils;

import com.mine.v2rayAPI.common.Const;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class V2rayUtil {
    private V2rayUtil() {
    }

    public static String concatName(String server,String port){
        return String.format(Const.NAME_FORMATER, server,port);
    }

    public static Map<String,Object> getYamlTemplate(Yaml yaml) throws IOException {
        try (InputStream stream = new ClassPathResource("template/template.yml").getInputStream()){
            return yaml.loadAs(stream,Map.class);
        } catch (IOException e) {
            throw e;
        }
    }
}
