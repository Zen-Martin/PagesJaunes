package com.pagesjaunes.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProtectionOfPrivacyPage extends Page{

    @FindBy(linkText = "ne pouvant pas excéder 14 mois ;")
    private WebElement delay;

    @FindBy(linkText = "cette page")
    private WebElement thisPage;

    @FindBy(id = "main-content")
    private WebElement pageContent;

    @FindBy(xpath = "//body/main[@id='content']/section[2]/article[1]/p[4]/a[1]")
    private WebElement formulaire;

    @FindBy(id = "pj_mes_coordonnees_person_lastname")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[contains(text(),'Merci de préciser votre Nom')]")
    private WebElement lastNameFieldError;

    @FindBy(id = "pj_mes_coordonnees_person_firstname")
    private WebElement firstNameField;

    @FindBy(xpath = "//div[contains(text(),'Merci de préciser votre Prénom')]")
    private WebElement firstNameFieldError;

    @FindBy(id = "pj_mes_coordonnees_person_postalAddress_streetAddress")
    private WebElement localisationField;

    @FindBy(xpath = "//div[contains(text(),'Merci de préciser votre N° / Voie / Lieu dit')]")
    private WebElement localisationFieldError;

    @FindBy(id = "pj_mes_coordonnees_person_postalAddress_addressLocality")
    private WebElement townField;

    @FindBy(xpath = "//div[contains(text(),'Merci de préciser votre Ville')]")
    private WebElement townFieldError;

    @FindBy(xpath = "//button[contains(text(),'Valider')]")
    private WebElement submitField;

    private String link = "";

    private String bodytext = "";

    private int fieldError = 0;

    private String numericTestField = "2000";

    private String emojiTestField = "\uD83D\uDE2C\uD83D\uDE05";

    public ProtectionOfPrivacyPage() {
    }

    public void scrollToDelay(){
        shortUntil(visibilityOf(delay));
        scroll((delay.getLocation().getY()-20));
    }

    public void scrollToThisPage(){
        shortUntil(visibilityOf(thisPage));
        scroll((thisPage.getLocation().getY()-20));
    }

    public void goToThisPageLink(){
        clickOn(thisPage);
        waitForLoadingPage();
        handleCookie();
    }

    public void goToFormulaire(){
        shortUntil(visibilityOf(formulaire));
        clickOn(formulaire);
        waitForLoadingPage();
    }

    public void emptyValidationForm(){
        shortUntil(visibilityOf(submitField));
        clickOn(submitField);
        waitForLoadingPage();
    }

    public void emptyValidationResult(){
        shortUntil(visibilityOf(lastNameFieldError));
        scroll((lastNameFieldError.getLocation().getY()-50));
        checkNumericFieldResult();
    }

    public void numericValidationForm(){
        refresh_page();
        lastNameField.sendKeys(numericTestField);
        firstNameField.sendKeys(numericTestField);
        clickOn(submitField);
        waitForLoadingPage();
    }

    public void emojiValidationForm(){
        refresh_page();
        js.executeScript("arguments[0].value='"+emojiTestField+"';", localisationField);
        js.executeScript("arguments[0].value='"+emojiTestField+"';", townField);
        clickOn(submitField);
        waitForLoadingPage();
    }

    public void checkNumericFieldResult(){
        try{
            if(lastNameFieldError.isDisplayed() && firstNameFieldError.isDisplayed()){
                fieldError+=1;
            }
        }catch (Exception e){
            fieldError+=0;
        }
    }

    public void checkEmojiFieldResult(){
        try{
            if(localisationFieldError.isDisplayed() && townFieldError.isDisplayed()){
                fieldError+=1;
            }
        }catch (Exception e){
            fieldError+=0;
        }
    }

    public void emojiValidationResult(){
        shortUntil(visibilityOf(localisationField));
        scroll((localisationField.getLocation().getY()-50));
        checkEmojiFieldResult();
    }

    public void numericValidationResult(){
        shortUntil(visibilityOf(lastNameField));
        scroll((lastNameField.getLocation().getY()-50));
        checkNumericFieldResult();
    }

    public void getPageContent(){
        bodytext = pageContent.getText();
    }

    private boolean isNumeric(String str){
        return str != null && str.matches("[0-9]+");
    }

    public void getDelayLink() {
        clickOn(delay);
        try{
            if(!delay.getAttribute("href").isEmpty()){
                link = delay.getAttribute("href");
            }
        }catch (Exception e){
            link = "null";
        }

    }

    public boolean verifyDelayContent(){
        System.out.println("\nredirection statut : "+link);
        if (!link.equals("null")){
            System.out.println("\n Probleme de redirection resolu : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\n Probleme de redirection non-resolu : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }

    public boolean verifyThisPageContent(){
        System.out.println("\nredirection page content : "+bodytext);
        if (!bodytext.contains("La page que vous cherchez n’existe pas")){
            System.out.println("\n Probleme de redirection resolu : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\n Probleme de redirection non-resolu : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }

    public boolean verifyNumericValidation() {
        System.out.println("\nindicateur d'erreurs lors des validations du formulaire : " + fieldError);
        if ((isNumeric(numericTestField) && fieldError != 1)) {
            System.out.println("\n Probleme de formulaire resole : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\n Probleme de formulaire non-resolu : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }

        public boolean verifyEmojiValidation(){
            System.out.println("\nindicateur d'erreurs lors des validations du formulaire : "+fieldError);
            if (fieldError!=1){
                System.out.println("\n Probleme de formulaire resole : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {
                System.out.println("\n Probleme de formulaire non-resolu : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }

    }

}
