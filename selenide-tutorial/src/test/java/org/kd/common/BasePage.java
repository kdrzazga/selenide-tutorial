package org.kd.common;

import com.codeborne.selenide.Configuration;

public abstract class BasePage {
    protected String url;
    public BasePage(String url){
        this.url = url;
        Configuration.startMaximized = true;
    }
}
