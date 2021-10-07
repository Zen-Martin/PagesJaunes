package com.pagesjaunes.steps.test;

import com.pagesjaunes.context.ScenarioContext;
import com.pagesjaunes.pageObjects.LoginPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class LoginStep implements En {

    public LoginStep(
            LoginPage loginPage,
            ScenarioContext scenario
    ){

        Given("I connect to my account", () -> {
            loginPage.connexion();
        });

        When("I click on the tab *Gerer mon compte*", () -> {
            loginPage.getOnModificationPage();
        });

        When("I click on the tab *Modifier mes informations*", () -> {
            loginPage.getOnNickname();
        });

        When("I enter a nickname and validate", () -> {
            loginPage.nickNameValidation();
        });

        Then("Immediate taking into account of the new nickname", () -> {
            loginPage.verifyInstantNicknameModification();
        });

        But("The taking into account of the new nickname is not automatic", () -> {
            loginPage.saveScreenShotPNG();
            loginPage.getDisconnexion();
            Assert.assertEquals(loginPage.verifyInstantNicknameModification(),true);
        });

        When("I modify my profile picture", () -> {
            loginPage.profilValidation();
            loginPage.verifyInstantProfilModification();
        });

        Then("Updating the profile picture is not instantaneous", () -> {
            loginPage.saveScreenShotPNG();
            loginPage.getDisconnexion();
            Assert.assertEquals(loginPage.verifyInstantProfilModification(),true);
        });

    }

}
