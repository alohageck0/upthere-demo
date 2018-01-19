package com.eero.tdeero;

import com.eero.tdeero.screen.LoginScreen;
import com.eero.tdeero.screen.VerifyCodeScreen;
import com.eero.tdeero.screen.WelcomeToEeroScreen;
import com.eero.tdeero.screen.WelcomeScreen;
import io.appium.java_client.AppiumDriver;

public class Eero {
   private final AppiumDriver driver;

   public Eero(AppiumDriver driver) {
      this.driver = driver;
   }

   public WelcomeScreen welcomeScreen() {
      return new WelcomeScreen(driver);
   }

   public WelcomeToEeroScreen welcomeToEeroScreen() {
      return new WelcomeToEeroScreen(driver);
   }

   public LoginScreen loginScreen() {
      return new LoginScreen(driver);
   }


   public VerifyCodeScreen verifyCodeScreen() {
      return new VerifyCodeScreen(driver);
   }
}
