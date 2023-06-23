package com.works.useProfile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("prod")
@Component
public class Prod implements IConfig {

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("apiKey", "prod_asdaklsjdalksdjq");
        hm.put("rowCount", 50);
        hm.put("color", "black");
        return hm;
    }

}

