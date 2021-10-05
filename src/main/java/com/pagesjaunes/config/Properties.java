package com.pagesjaunes.config;

import com.pagesjaunes.driverManager.WebDriverManager;

public abstract class Properties {

    public final static Configuration       Config          = Configuration.getInstance();
    public final static WebDriverManager    DriverManager   = WebDriverManager.getInstance();

}
