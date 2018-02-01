package upthere.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.testng.Assert;

public class WelcomeScreen extends ScreenTemplate {
   public WelcomeScreen(AppiumDriver driver) {
      super(driver);
   }

   @Override
   public void assertScreen() {
      Assert.assertTrue(ifLoginTitleCorrect());
   }

   @Override
   public void isLoaded() {
      checkElementVisibleDefault(login_title);
   }

   @iOSFindBy(accessibility = "Facebook")
   @AndroidFindBy(id = "facebookButton")
   private MobileElement facebookButton;

   @iOSFindBy(accessibility = "Google Sign in")
   @AndroidFindBy(id = "googleSignInButton")
   private MobileElement googleSignInButton;

   @AndroidFindBy(id = "emailSignInLink")
   private MobileElement emailSignInLink;

   @AndroidFindBy(id = "login_title")
   private MobileElement login_title;


   @AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Evgenii Iavorovich']")
   private MobileElement googleAccount;

   public void tapFacebookButton() {
      getTouchAction().tap(facebookButton).perform();
   }

   public void tapGoogleSignIn() {
      getTouchAction().tap(googleSignInButton).perform();
   }

   public void tapEmailSignIn() {
      getTouchAction().tap(emailSignInLink).perform();
   }

   public void tapGoogleAccount() {
      getTouchAction().tap(googleAccount).perform();
   }

   public boolean ifLoginTitleCorrect() {
      return login_title.getText().equals("Welcome to\n" +
              "Upthere Home");
   }


}
