@Web
Feature: Re-Test Cases for PAGES-JAUNES Website without account Access
  Background:
    Given I am on the homePage
    And I connect to my account

  @Bug_1400
  @severity=critical
  Scenario: The change of nickname is not done instantly
    When I click on the tab *Gerer mon compte*
    And I click on the tab *Modifier mes informations*
    And I enter a nickname and validate
    Then Immediate taking into account of the new nickname
    But The taking into account of the new nickname is not automatic

  @Bug_1406
  @severity=minor
  Scenario: The instantaneous disregard of the update of the profile picture
    When I click on the tab *Gerer mon compte*
    And I modify my profile picture
    Then Updating the profile picture is not instantaneous
