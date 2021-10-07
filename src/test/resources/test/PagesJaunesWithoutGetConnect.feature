@Web
Feature: Re-Test Cases for PAGES-JAUNES Website without account Access
  Background:
    Given I am on the homePage

  @Bug_1379
  @severity=minor
  Scenario: Consideration of alphanumeric characters by certain fields
    When I click on the link *Protection de la vie privée*
    And I click on the first link *Ce formulaire*
    And I fill out the form by entering numeric data in the first and last names fields and validate
    Then An error message should be returned by the site
    But The form takes into account numeric values

  @Bug_1380
  @severity=minor
  Scenario: the site accepts emojis
    When I click on the link *Protection de la vie privée*
    And I click on the first link *Ce formulaire*
    And I fill out the form by entering emoji at the level of these fields namely *N° / Voie / Lieu dit* and *ville*
    Then An error message should be returned
    But The form accepts emoji

  @Bug_1381
  @severity=critical
  Scenario: Page without content
    When Scroll down to the footer Click on the tab *CGU des services du compte*
    And In the section *1- Definitions* at the end of level *Charte Editorial* Click on The link *Ici*
    And Scroll up to the section *5– Conditions d’utilisation : mentions particulières pour le service Avis consommateurs*
    And At the level *Modération des Avis* I click on the link *Ici*
    Then Availability of the requested page
    But Unavailability of the requested page

  @Bug_1382
  @severity=critical
  Scenario: Unavailability of a page on the website
    When I click on the link *Protection de la vie privée*
    And Scroll down to the section *Historique de recherches et de résultats*
    And I click on the link *Cette page*
    Then Availability of the requested page on the site
    But Unavailability of the requested page on the site

  @Bug_1417
  @severity=minor
  Scenario: Non-clickable link
    When I click on the link *Protection de la vie privée*
    And Scroll down to the section *Pendant quelle durée ?*
    And I Click on the link *ne pouvant pas excéder 14 mois*
    Then Non-clickable link





