package upthere.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class WelcomeScreen extends ScreenTemplate {
   public WelcomeScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      //todo
   }

   @Override
   public void isLoaded() {

   }

   @iOSFindBy(accessibility = "Next")
   @AndroidFindBy(id = "button_next")
   private MobileElement buttonNext;

   @iOSFindBy(accessibility = "Let’s Get Started")
   @AndroidFindBy(id = "button_next")
   private MobileElement getStarted;


   public void tapNext() {
      getTouchAction().tap(buttonNext).perform();
   }

   public void tapGetStarted(){
      getTouchAction().tap(getStarted).perform();
   }
}
