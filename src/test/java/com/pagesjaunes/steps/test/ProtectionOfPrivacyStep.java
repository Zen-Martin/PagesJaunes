package com.pagesjaunes.steps.test;

import com.pagesjaunes.context.ScenarioContext;
import com.pagesjaunes.pageObjects.ProtectionOfPrivacyPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class ProtectionOfPrivacyStep implements En {

    public ProtectionOfPrivacyStep(
            ProtectionOfPrivacyPage protectionOfPrivacyPage,
            ScenarioContext scenario
    ){

        When("Scroll down to the section *Pendant quelle durée ?*", () -> {
            protectionOfPrivacyPage.scrollToDelay();
        });

        When("I Click on the link *ne pouvant pas excéder {int} mois*", (Integer int1) -> {
            protectionOfPrivacyPage.getDelayLink();
        });

        Then("Non-clickable link", () -> {
            protectionOfPrivacyPage.saveScreenShotPNG();
            protectionOfPrivacyPage.verifyDelayContent();
            Assert.assertEquals(protectionOfPrivacyPage.verifyDelayContent(),true);
        });

        When("Scroll down to the section *Historique de recherches et de résultats*", () -> {
            protectionOfPrivacyPage.scrollToThisPage();
        });
        When("I click on the link *Cette page*", () -> {
            protectionOfPrivacyPage.goToThisPageLink();
        });
        Then("Availability of the requested page on the site", () -> {
            protectionOfPrivacyPage.getPageContent();
        });
        Then("Unavailability of the requested page on the site", () -> {
            protectionOfPrivacyPage.saveScreenShotPNG();
            protectionOfPrivacyPage.verifyThisPageContent();
            Assert.assertEquals(protectionOfPrivacyPage.verifyThisPageContent(),true);
        });

        When("I click on the first link *Ce formulaire*", () -> {
            protectionOfPrivacyPage.goToFormulaire();
            protectionOfPrivacyPage.emptyValidationForm();
            protectionOfPrivacyPage.emptyValidationResult();
        });

        When("I fill out the form by entering numeric data in the first and last names fields and validate", () -> {
            protectionOfPrivacyPage.numericValidationForm();
            protectionOfPrivacyPage.numericValidationResult();
        });

        Then("An error message should be returned by the site", () -> {
            protectionOfPrivacyPage.verifyNumericValidation();
        });

        Then("The form takes into account numeric values", () -> {
            protectionOfPrivacyPage.saveScreenShotPNG();
            Assert.assertEquals(protectionOfPrivacyPage.verifyNumericValidation(),true);
        });

        And("^I fill out the form by entering emoji at the level of these fields namely \\*N° / Voie / Lieu dit\\* and \\*ville\\*$", () -> {
            protectionOfPrivacyPage.emojiValidationForm();
            protectionOfPrivacyPage.emojiValidationResult();
        });

        Then("^An error message should be returned$", () -> {
            protectionOfPrivacyPage.verifyEmojiValidation();
        });

        But("^The form accepts emoji$", () -> {
            protectionOfPrivacyPage.saveScreenShotPNG();
            Assert.assertEquals(protectionOfPrivacyPage.verifyEmojiValidation(),true);
        });



    }
}
