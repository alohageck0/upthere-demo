package upthere;

import upthere.screen.LoginScreen;
import upthere.screen.VerifyCodeScreen;
import upthere.screen.WelcomeToEeroScreen;
import upthere.screen.WelcomeScreen;
import io.appium.java_client.AppiumDriver;

public class Upthere {
   private final AppiumDriver driver;

   public Upthere(AppiumDriver driver) {
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
