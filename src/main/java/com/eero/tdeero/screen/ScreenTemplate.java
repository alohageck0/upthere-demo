package com.eero.tdeero.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ScreenTemplate {
   private AppiumDriver driver;
   private TouchAction touchAction;

   public ScreenTemplate(AppiumDriver driver) {
      this.driver = driver;
      touchAction = new TouchAction(driver);
      PageFactory.initElements(new AppiumFieldDecorator(driver), this);
      isLoaded();
   }

   public TouchAction getTouchAction() {
      return touchAction;
   }

   public AppiumDriver getDriver() {
      return driver;
   }

   /**
    * Method to assert if screen opened
    */
   public abstract void assertScreen();

   /**
    * Add this method with checkElementVisibleDefault(MobileElement mobileElement)
    * to each screen constructor to check if screen properly loaded
    */
   public abstract void isLoaded();

   /**
    * Checks if element visible on screen with default timeout 5 sec
    * @param mobileElement
    */
   public void checkElementVisibleDefault(MobileElement mobileElement) {
      checkElementVisible(mobileElement, 5);
   }

   /**
    * Checks if element visible on screen with custom timeout
    * @param mobileElement
    * @param timeout
    */
   public void checkElementVisible(MobileElement mobileElement, int timeout) {
      System.out.println("Waiting for element to be visible");
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
         wait.until(ExpectedConditions.visibilityOf(mobileElement));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
