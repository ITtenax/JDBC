package core;

/**
 * Created by Valentyn on 10/6/2015.
 */
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * User: Valentyn_Kvasov
 * Date: 28.09.2015
 * Time: 22:12
 */

@Resource.Classpath("testing.properties")
public class Properties {
    private static final Properties instance = new Properties();

    private Properties() {
        PropertyLoader.populate(this);
    }

    @Property("jdbc_driver_class")
    private String jdbc_driver_class;

    @Property("jdbc_url")
    private String jdbc_url;

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
        return jdbc_url;
    }

    public String getJdbcPass() {
        return jdbc_pass;
    }

    public String getJdbcLogin() {
        return jdbc_login;
    }
}