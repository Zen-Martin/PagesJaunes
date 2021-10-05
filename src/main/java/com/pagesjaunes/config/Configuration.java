package com.pagesjaunes.config;

import com.pagesjaunes.Enum.Device;
import com.pagesjaunes.Enum.Urls;


public class Configuration {

    private static Configuration INSTANCE = new Configuration();

    private PropertiesConfig prop = PropertiesConfig.getInstance();

    private String webDriverPath;
    private Boolean headless;
    private String environment;
    private String browser;
    private String email;
    private String pwd;

    private Device device;
    private Urls env;

    public Configuration(){
        headless        = Boolean.valueOf(System.getProperty("headless", (String)prop.get("headless", "false")));
        environment     = System.getProperty("env", (String) prop.get("environment"));
        browser         = System.getProperty("browser", (String)prop.get("browser", "chrome"));
        webDriverPath   = (String)prop.get(browser.toLowerCase(), "chrome");
        device          = Device.valueOf(((String)prop.get("device", "desktop")).toUpperCase());
        email           = System.getProperty("email", (String) prop.get("email","malikadouma19@gmail.com"));
        pwd             = System.getProperty("pwd", (String) prop.get("pwd","Malika2016+"));
        env             = Urls.valueOf(environment.toUpperCase());

    }

    public Boolean getHeadless() {
        return headless;
    }

    public String getEnvironment() { return env.getUrl(); }

    public String getBrowser() {
        return browser;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getWebDriverPath() { return webDriverPath; }

    public Device getDevice(){ return device;}

    public static Configuration getInstance(){
        return INSTANCE;
    }
}
