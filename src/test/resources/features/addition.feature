#/**
#* @author: https://github.com/eaccmk
#* */

Feature: First feature Test feature

  @delete
  Scenario: Testing the test

  Given User delete's the old values

  @add
  Scenario Outline: Adding two values

    Given User adds "<value1>" with "<value2>"

    Examples:
    |value1|value2|
    |    1 | 2    |