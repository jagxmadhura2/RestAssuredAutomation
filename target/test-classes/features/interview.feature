@public
Feature: Rest api automation
 Convert testng framework in to bdd framework

  @public
  Scenario: Register successfull in site and create user profile
	Given automate register user api
	Then automate create user api
	
	@public
  Scenario: Calling create user api 2nd time because in scenaro1 assertion failed
	Then automate create user api
  
  @public
  Scenario: Get single user info
	Given get single user api
  
  @public
  Scenario: Get list of users
  Given get list of user api
  
  @public
  Scenario: update user info
  Given update user info using put method
  Given update user info using patch method
  
  @public @private
  Scenario: delete user info
  Given delete user info api