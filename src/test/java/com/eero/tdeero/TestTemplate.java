package com.eero.tdeero;

import com.eero.qa.apis.testAccount.UserService;
import com.eero.tdeero.framework.AppiumConfigurator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class TestTemplate {
   private File appDir = new File("src");
   public static final String TEST_USER_FOLDER = "/test/java/testUser";
   private AppiumConfigurator config = new AppiumConfigurator();
   private AppiumDriver driver;
   private Eero eero;
   protected UserService testUser = new UserService();
   private String mobilePlatform = System.getProperty("platform");
//   protected Logger logger = Logger.getLogger(this.getClass().getName());

   public TestTemplate(AppiumDriver driver) {
      this.driver = driver;
   }

   public TestTemplate() {
   }

//   public AppiumDriver getDriver() {
//      return driver;
//   }

   public Eero getEero() {
      return eero;
   }

   @BeforeClass(alwaysRun = true)
   public void setUp() throws MalformedURLException {
      System.out.println(mobilePlatform);
      config.startAppiumServer(mobilePlatform);
//      PropertyConfigurator.configure(new File(appDir, "log4j.properties").getAbsolutePath());
      if (mobilePlatform.equalsIgnoreCase("android")) {
         this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), config.getCapabilities());
      } else {
         this.driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), config.getCapabilities());
      }
      this.eero = new Eero(driver);
   }

   @BeforeMethod
   public void setUpTest() {

   }

   @AfterClass(alwaysRun = true)
   public void tearDown() {
      System.out.println("tear down started");
      try {
         driver.quit();
      } catch (NullPointerException e) {
         System.out.println("Driver is null");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      config.cleanUp();
   }

   public void setTestUser(String userPhoneNubmer) {
      testUser.getTestUserFromJSON(userPhoneNubmer);
   }

   private String setMobilePlatform(String platform) {
      if (platform.equalsIgnoreCase("android")) {
         return MobilePlatform.ANDROID;
      }
      return MobilePlatform.IOS;
   }
}
