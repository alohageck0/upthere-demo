package upthere;

import io.appium.java_client.AppiumDriver;
import upthere.screen.WelcomeAppScreen;
import upthere.screen.WelcomeScreen;

public class Upthere {
   private final AppiumDriver driver;

   public Upthere(AppiumDriver driver) {
      this.driver = driver;
   }

   public WelcomeScreen welcomeScreen() {
      return new WelcomeScreen(driver);
   }

   public WelcomeAppScreen welcomeAppScreen() {
      return new WelcomeAppScreen(driver);
   }

}
