package com.helpers;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * Created by Valentyn on 10/6/2015.
 */

@Resource.Classpath("testing.properties")
public class Properties {
    private static final Properties instance = new Properties();

    private Properties() {
        PropertyLoader.populate(this);
    }

    @Property("jdbc_driver_class")
    private String jdbc_driver_class;

    @Property("jdbc_url_server")
    private String jdbc_url_server;

    @Property("jdbc_url_embedded")
    private String jdbc_url_embedded;

    @Property("jdbc_pass")
    private String jdbc_pass;

    @Property("jdbc_login")
    private String jdbc_login;

    @Property("jdbc_pool_size")
    private String pool_size;

    public static Properties getInstance() {
        return instance;
    }

    public Integer getPoolSize() {
        return Integer.parseInt(pool_size);
    }

    public String getJdbcDriverClass() {
        return jdbc_driver_class;
    }

    public String getJdbcUrl() {
        return jdbc_url_server;
    }

    public String getJdbcPass() {
        return jdbc_pass;
    }

    public String getJdbcLogin() {
        return jdbc_login;
    }

    public String getJdbcUrlEmbedded() {
        return jdbc_url_embedded;
    }
}