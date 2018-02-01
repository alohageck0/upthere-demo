package upthere.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class WelcomeAppScreen extends ScreenTemplate {
   public WelcomeAppScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      Assert.assertTrue(ifWelcomeTextCorrect());
   }

   @Override
   public void isLoaded() {
      checkElementVisibleDefault(welcomeText);
   }

   @AndroidFindBy(id = "welcomeText")
   private MobileElement welcomeText;

   public boolean ifWelcomeTextCorrect() {
      return welcomeText.getText().equals("Welcome to Upthere!");
   }
}
