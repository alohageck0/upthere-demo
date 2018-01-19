package com.eero.tdeero.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends ScreenTemplate {
   public LoginScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      //todo
   }

   @Override
   public void isLoaded() {
//      checkElementVisible();
   }

   @FindBy(className = "android.widget.EditText")
   private MobileElement networkName;
   @FindBy(id = "button_next_login")
   private MobileElement buttonNextLogin;

   public void enterMobileNumber(String phoneNumber) {
      getTouchAction().tap(networkName).perform();
      networkName.sendKeys(phoneNumber);
   }

   public void tapNext() {
      getTouchAction().tap(buttonNextLogin).perform();
   }
}
