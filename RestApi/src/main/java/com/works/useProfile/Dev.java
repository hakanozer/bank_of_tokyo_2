package com.works.useProfile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("dev")
@Component
public class Dev implements IConfig {

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("apiKey", "dev_asdaklsjdalksdjq");
        hm.put("rowCount", 25);
        hm.put("color", "red");
        return hm;
    }

}
