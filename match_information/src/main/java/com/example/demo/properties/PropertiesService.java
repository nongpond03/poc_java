
package com.example.demo.properties;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    @Autowired
    private Environment env;

    private static final Logger logger = LogManager.getLogger(PropertiesService.class);

    public PropertiesService() {
        // LoggerContext context = (org.apache.logging.log4j.core.LoggerContext)
        // LogManager.getContext(false);
        // File file = new File("path/to/a/different/log4j2.xml");
        // context.setConfigLocation(file.toURI());

    }

    public String getProperty(String key) {
        logger.info("id: ", env.getProperty("log4j2.configurationFile"));
        logger.info("key: " + key);
        logger.info("value: " + env.getProperty(key));
        logger.debug("value: " + env.getProperty(key));
        logger.warn("value: " + env.getProperty(key));
        logger.error("value: " + env.getProperty(key));
        System.out.println("key: " + env.getProperty(key));
        return env.getProperty(key);
    }
}
