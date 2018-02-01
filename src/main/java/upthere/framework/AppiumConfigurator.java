package upthere.framework;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public final class AppiumConfigurator {
   private File appAndroid = new File("application.apk");
   private File appiOS = new File("application.ipa");
   private AppiumServer appiumServer;
   private DesiredCapabilities capabilities = new DesiredCapabilities();

   //   public void startAppiumServer() {
//      appiumServer = new AppiumServer();
//      appiumServer.setAppPath(appAndroid);
//      appiumServer.startServer();
//   }
   public void startAppiumServer(String platform) {
      appiumServer = new AppiumServer();
      if (platform.equalsIgnoreCase("android")) {
         setAndroidCapabilities();
      } else {
         setIOSCapabilities();
      }
      appiumServer.startServer();
   }

   private void setIOSCapabilities() {
      appiumServer.setAppPath(appiOS);
      capabilities.setCapability("platformName", "iOS");
      capabilities.setCapability("deviceName", "iPhone 7 Plus");
      capabilities.setCapability("udid", "f44c6add84ac6583f291d0b06fbd46126911af6d"); //iphone7+
//      capabilities.setCapability("udid", "b671f653ca76f0d698e9a7cdca3d9bacff9369a9"); //iphone7
      capabilities.setCapability("platformVersion", "11.2");
   }

   private void setAndroidCapabilities() {
      //android
      appiumServer.setAppPath(appAndroid);
      capabilities.setCapability("platformName", "Android");
//      capabilities.setCapability("automationName", "uiautomator2");
      capabilities.setCapability("deviceName", "Android Device");
      capabilities.setCapability("newCommandTimeout", 180);
      capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.upthere.skydroid.auth.LoginActivity");
   }

   public void cleanUp() {
      System.out.println("In cleanup");
      if (appiumServer.isServerRunning()) {
         System.out.println("Attempt to stop server");
         appiumServer.stopServer();
      }
   }

   public DesiredCapabilities getCapabilities() {
      return capabilities;
   }
}