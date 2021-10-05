package com.pagesjaunes.steps.test;

import com.pagesjaunes.context.ScenarioContext;
import com.pagesjaunes.pageObjects.CGUPage;;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CGUStep implements En {

    public CGUStep(
            CGUPage cguPage,
            ScenarioContext scenario
    ){

        And("In the section *{int}- Definitions* at the end of level *Charte Editorial* Click on The link *Ici*", (Integer int1) -> {
            cguPage.firstClickOnIci();
        });

        And("^Scroll up to the section \\*(\\d+)– Conditions d’utilisation : mentions particulières pour le service Avis consommateurs\\*$", (Integer arg0) -> {

            cguPage.ScrollToSecondClickOnIci();
        });

        And("At the level *Modération des Avis* I click on the link *Ici*", () -> {
            cguPage.secondClickOnIci();
        });

        Then("Availability of the requested page", () -> {
            cguPage.getPageContent();
        });

        Then("Unavailability of the requested page", () -> {
            cguPage.saveScreenShotPNG();
            cguPage.verifyThisPageContent();
            Assert.assertEquals(cguPage.verifyThisPageContent(),true);
        });



    }


}
