package com.m2i.tp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataSourceConfig.class,JpaConfig.class})
@ComponentScan(basePackages= {"com.m2i.tp"})
//AppConfig.class est la classe de configuration principale/globale
//qui va remplacer mySpringconf.xml dans une application spring moderne .
public class AppConfig {

}
