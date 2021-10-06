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

  @Bug_1427
  @severity=minor
  Scenario: Continuous presence of question marks on a website page
    When I click on the link *services utiles livraison a domicile*
    And I click on *Table Neuf*
    And I scroll the page
    Then Presence of question marks on this page of the site

  @Bug_1428
  @severity=minor
  Scenario: Problem on the filter from a website page
    When I click on the link *Livraison à domicile*
    And I click on *SACHA FINKELSZTAJN - LA BOUTIQUE JAUNE*
    And I scroll the page to the bottom of the page to sort by
    Then This banner should be effective
    But The banner for scrolling through the choices in the filter field does not work

  @Bug_1444
  @severity=minor
  Scenario: Impossibility after liking a comment, to remove it
    When I click on the link *Livraison à domicile*
    And I select *la boulangerie Poilâne*
    And I go down the page to the comments level
    And Trying to like a comment after trying to remove it
    Then We should be able to dislick a comment once we like it
    But It is impossible for us after having liked a comment to remove it

  @Recommendation_1454
  @severity=minor
  Scenario: Ease of paginating
    When I am doing a research on *conseils en organisation gestion management* à *bordeaux-33000*
    And I valid
    And I browse all of these pages
    Then It is impossible for us to skip certain pages to arrive at a *page 50*





