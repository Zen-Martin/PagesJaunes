package com.pagesjaunes.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CGUPage extends Page{

    @FindBy(xpath = "//body/main[@id='content']/section[2]/article[1]/div[1]/p[6]/a[1]")
    private WebElement firstClickIci;

    @FindBy(xpath = "//body/main[@id='content']/section[2]/article[1]/div[1]/ul[9]/li[6]/a[1]")
    private WebElement secondClickIci;

    @FindBy(xpath = "//body/main[@id='content']/div[1]/h1[1]")
    private WebElement pageContent;

    private String bodytext = "";

    public CGUPage() {
    }

    public void firstClickOnIci(){
        shortUntil(visibilityOf(firstClickIci));
        clickOn(firstClickIci);
        waitForLoadingPage();
    }

    public void ScrollToSecondClickOnIci(){
        shortUntil(visibilityOf(secondClickIci));
        scroll((secondClickIci.getLocation().getY()-50));
    }

    public void secondClickOnIci(){
        clickOn(secondClickIci);
        waitForLoadingPage();
    }

    public void getPageContent(){
        bodytext = pageContent.getText();
    }

    public boolean verifyThisPageContent(){
        System.out.println("\nredirection page content : "+bodytext);
        if ((!bodytext.contains("Il semblerait que la page que"))&&(!bodytext.contains("vous cherchez soit introuvable"))){
            System.out.println("\n Probleme de redirection resolu : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\n Probleme de redirection non-resolu : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }



}
