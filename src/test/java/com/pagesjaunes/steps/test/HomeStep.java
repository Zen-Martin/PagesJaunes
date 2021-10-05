package com.pagesjaunes.steps.test;

import com.pagesjaunes.context.ScenarioContext;
import com.pagesjaunes.pageObjects.HomePage;
import io.cucumber.java8.En;

public class HomeStep implements En {

    public HomeStep(
            HomePage homePage,
            ScenarioContext scenario
    ){

        Given("I am on the homePage", () -> {
            homePage.navigateToHomePage();
        });

        When("I click on the link *Protection de la vie privée*", () -> {
            homePage.clickOnProtectionOfPrivacy();
        });

        When("Scroll down to the footer Click on the tab *CGU des services du compte*", () -> {
            homePage.clickOnCGU();
        });

    }

}
