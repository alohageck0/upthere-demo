package upthere.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class VerifyCodeScreen extends ScreenTemplate {
   public VerifyCodeScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      //todo
   }

   @Override
   public void isLoaded() {

   }

   @FindBy(className = "android.widget.EditText")
   private MobileElement verficationCodeField;

   @FindBy(id = "button_login_verification")
   private MobileElement buttonLoginVerification;

   public void enterVerificationCode(String code) {
      getTouchAction().tap(verficationCodeField).perform();
      verficationCodeField.sendKeys(code);
   }

   public void tapNext() {
      getTouchAction().tap(buttonLoginVerification).perform();
   }
}
