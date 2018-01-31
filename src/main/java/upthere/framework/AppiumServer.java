package upthere.framework;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class AppiumServer {

   private AppiumDriverLocalService service;
   private AppiumServiceBuilder builder;
   private DesiredCapabilities cap = new DesiredCapabilities();

   //   public void startServer() {
//      //Set Capabilities
//      cap.setCapability("noReset", "false");
//      cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
//
//      //Build the Appium service
//      builder = new AppiumServiceBuilder()
//              .withIPAddress("0.0.0.0")
//              .usingPort(4723)
//              .withCapabilities(cap)
//              .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//              .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
//              .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
////      builder.withLogFile(new java.io.File("/Users/evgenii/IdeaProjects/GlookoTest/src/test/java/tests/app.log"));
//
//      //Start the server with the builder
//      service = AppiumDriverLocalService.buildService(builder);
//      service.start();
//   }

   public void startServer() {
      //Set Capabilities
//      cap.setCapability("noReset", "false");
//      cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
////      cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
//      cap.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyS8");

      //Build the Appium service
      //todo change appium path
      builder = new AppiumServiceBuilder()
              .withIPAddress("0.0.0.0")
              .usingPort(4723)
              .withCapabilities(cap)
              .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
              .withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
              //todo remove hardcoded part
//              .withAppiumJS(new File("/Users/royalfiish/.nvm/versions/node/v9.3.0/bin/node"));
//      builder.withLogFile(new java.io.File("/Users/evgenii/IdeaProjects/GlookoTest/src/test/java/tests/app.log"));

      //Start the server with the builder
      service = AppiumDriverLocalService.buildService(builder);
      service.start();
   }

   public DesiredCapabilities getCap() {
      return cap;
   }

   public void setAppPath(File app) {
      cap.setCapability("app", app.getAbsolutePath());
   }

   public void stopServer() {
      service.stop();
   }

   public boolean isServerRunning() {
      return service.isRunning();
   }
}
