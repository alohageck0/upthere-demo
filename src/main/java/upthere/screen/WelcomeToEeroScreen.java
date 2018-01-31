package upthere.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.testng.Assert;

public class WelcomeToEeroScreen extends ScreenTemplate {
   public WelcomeToEeroScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      Assert.assertTrue(ifSigninPresent());
   }

   @Override
   public void isLoaded() {
      checkElementVisibleDefault(buttonSignin);
   }

   @iOSFindBy(accessibility = "Log In")
   @AndroidFindBy(id = "button_signin")
   private MobileElement buttonSignin;

   public void tapSignin() {
      getTouchAction().tap(buttonSignin).perform();
   }

   public boolean ifSigninPresent() {
      return buttonSignin.isDisplayed();
   }

}
