package upthere;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

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
      getUpthere().welcomeScreen().tapNext();
      getUpthere().welcomeScreen().tapNext();
      getUpthere().welcomeScreen().tapNext();
      getUpthere().welcomeScreen().tapGetStarted();
      getUpthere().welcomeToEeroScreen().assertScreen();
   }
}
