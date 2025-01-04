package org.truenvy.dogs.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ProjectConfig extends Config {

    Boolean logging();

}
