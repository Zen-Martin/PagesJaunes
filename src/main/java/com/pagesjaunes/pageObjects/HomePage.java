package com.pagesjaunes.pageObjects;

import com.pagesjaunes.config.Configuration;
import com.pagesjaunes.config.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage extends Page {

    @FindBy(id = "viePrivee")
    private WebElement protectionOfPrivacy;

    @FindBy(id = "cguServiceCompte")
    private WebElement CGU;

    private final static Configuration PROP  = Properties.Config;

    public HomePage() {
    }

    public void navigateToHomePage(){
        get(PROP.getEnvironment());
        waitForLoadingPage();
        handleCookie();
    }

    public void clickOnProtectionOfPrivacy(){
        shortUntil(visibilityOf(protectionOfPrivacy));
        clickOn(protectionOfPrivacy);
        waitForLoadingPage();
    }

    public void clickOnCGU(){
        shortUntil(visibilityOf(CGU));
        goToLinkpage(CGU);
        waitForLoadingPage();
    }

}
