package com.eero.tdeero;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Class with examples of tests for login functionality
 */
public class Login extends TestTemplate {

   public Login(AppiumDriver driver) {
      super(driver);
   }

   public Login() {
   }

   @Test(groups = {"android", "ios"})
   public void openLoginScreen() {
      getEero().welcomeScreen().tapNext();
      getEero().welcomeScreen().tapNext();
      getEero().welcomeScreen().tapNext();
      getEero().welcomeScreen().tapGetStarted();
      getEero().welcomeToEeroScreen().assertScreen();
   }

   @Test(dataProvider = "getLoginUser", dataProviderClass = DataProvider.class)
   public void loginWithPhone(String loginPhone) throws IOException, InterruptedException {
      System.out.printf(loginPhone);
      setTestUser("14155550901");
      openLoginScreen();
      getEero().welcomeToEeroScreen().tapSignin();
      getEero().loginScreen().enterMobileNumber(testUser.getPhoneNumber());
      getEero().loginScreen().tapNext();

      getEero().verifyCodeScreen().enterVerificationCode(testUser.getVerificationCode());
      getEero().verifyCodeScreen().tapNext();
      Thread.sleep(4000);

      //todo assertion
   }
}
