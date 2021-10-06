package com.pagesjaunes.pageObjects;

import com.pagesjaunes.config.Configuration;
import com.pagesjaunes.config.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.charset.Charset;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends Page{

    @FindBy(xpath = "//span[contains(text(),'Mon compte')]")
    private WebElement account;

    @FindBy(id = "metanav-lien-se-connecter")
    private WebElement accountAccess;

    @FindBy(id = "loginID")
    private WebElement login;

    @FindBy(id = "passwordAuthent")
    private WebElement passwd;

    @FindBy(id = "menu_connexion")
    private WebElement menuConnexion;

    @FindBy(id = "metanav-lien-mon-compte")
    private WebElement manageAccount;

    @FindBy(xpath = "//body/main[@id='main']/section[1]/div[2]/div[1]/div[1]/section[1]/a[1]")
    private WebElement modifyAccount;

    @FindBy(xpath = "//body/main[@id='main']/section[1]/div[2]/div[1]/div[1]/section[1]/div[1]/span[1]")
    private WebElement modifyProfil;

    @FindBy(xpath = "//body/main[@id='main']/section[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[1]")
    private WebElement profilField;

    @FindBy(xpath = "//body/main[@id='main']/section[1]/div[2]/div[1]/div[1]/section[1]/div[1]/button[1]")
    private WebElement updateProfil;

    @FindBy(id = "pj_user_avatar_file")
    private WebElement addFile;


    @FindBy(id = "pj_user_profile_modify_nickname")
    private WebElement nickNameField;

    @FindBy(id = "mes_informations_pseudo")
    private WebElement nickNameValue;

    @FindBy(id = "metanav-utilisateur-connecte")
    private WebElement userConnected;

    @FindBy(id = "popin")
    private WebElement connexionFrame;

    private final static Configuration PROP  = Properties.Config;

    private boolean instantModificationStatut = true;

    private int modifySequence = 0;

    public void connexion(){
        shortUntil(visibilityOf(account));
        clickOn(account);
        shortUntil(visibilityOf(accountAccess));
        clickOn(accountAccess);
        shortUntil(visibilityOf(login));
        login.sendKeys(PROP.getEmail());
        passwd.sendKeys(PROP.getPwd());
        passwd.sendKeys(Keys.ENTER);
        shortUntil(invisibilityOf(connexionFrame));
    }

    public void getOnModificationPage() {
        clickOn(userConnected);
        shortUntil(visibilityOf(manageAccount));
        clickOn(manageAccount);
        waitForLoadingPage();
    }

    public void nickNameValidation(){
        instantModificationStatut = getNickNameField();
        if (modifySequence!=0){
            setNickName();
            getOnNickname();
            instantModificationStatut = getNickNameField();
        }
    }

    public void profilValidation(){
        shortUntil(visibilityOf(profilField));
        instantModificationStatut = getProfilField();
        if (modifySequence!=0){
            setProfilePicture();
            instantModificationStatut = getProfilField();
        }
    }

    public void getOnNickname(){
        shortUntil(visibilityOf(modifyAccount));
        clickOn(modifyAccount);
        waitForLoadingPage();
        shortUntil(visibilityOf(nickNameField));
    }

    public void setProfilePicture(){
            clickOn(updateProfil);
            addFile.sendKeys(System.getProperty("user.dir")+"/src/test/resources/image/Profil.png");
            refresh_page();
    }

    public boolean getNickNameField(){
        if (!nickNameField.isEnabled()){
            modifySequence += 0;
            return false;
        }
        else {
            modifySequence += 1;
            return true;
        }
    }

    public boolean getProfilField(){
        try{
            if (modifyProfil.isDisplayed()){
                modifySequence += 0;
                return false;
            }

        }catch(Exception e){
            modifySequence += 1;
        }
        return true;
    }

    public void setNickName(){
        nickNameField.sendKeys(Keys.DELETE);
        nickNameField.sendKeys(generateNickName(5));
        nickNameField.sendKeys(Keys.ENTER);
        waitForLoadingPage();
    }

    public String generateNickName(int n){

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }
        return r.toString();
    }

    public boolean verifyInstantNicknameModification(){
        System.out.println("\nModification iteration : "+modifySequence
                            +"\nModification instantly statut : "+instantModificationStatut);
        if (modifySequence==2 && instantModificationStatut==true){
            System.out.println("\nModificationin instantanee effective : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\nModificationin instantanee non-effective : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }

    public boolean verifyInstantProfilModification(){
        System.out.println("\nModification iteration : "+modifySequence
                +"\nModification instantly statut : "+instantModificationStatut);
        if (modifySequence==2 && instantModificationStatut==true){
            System.out.println("\nModificationin instantanee effective : "
                    + "\n\n\tBug Corrigé !!!");
            return true;

        } else {
            System.out.println("\nModificationin instantanee non-effective : "
                    + "\n\n\tBug Non Corrigé !!!");
            return false;
        }

    }

}
