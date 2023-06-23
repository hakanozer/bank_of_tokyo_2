package com.works.useProfile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("test")
@Component
public class Test implements IConfig {

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("apiKey", "test_asdaklsjdalksdjq");
        hm.put("rowCount", 10);
        hm.put("color", "yellow");
        return hm;
    }

}

