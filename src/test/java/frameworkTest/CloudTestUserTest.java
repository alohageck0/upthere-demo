package frameworkTest;

import com.eero.qa.apis.testAccount.UserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CloudTestUserTest {
   private UserService user = new UserService("Evgenii NewFrame", "test+evgeniiframe@e2ro.com", "4155550901");

   @AfterClass
   public void tearDown() {
//      user.deleteUser();
   }

   @Test(groups = {"framework"})
   public void createUserTest() throws IOException {
      user.createTestUser();
      user.returnPhoneVerificationCode();
      user.verifyUserWithCode();
      user.returnEmailVerificationCode();
      user.verifyUserEmailWithCode();

      user.serializeCloudTestUser();
//      user.deleteUser();
//      user.apiLoginTestUser();
//      user.getVerificationCode();


   }

   @Test
   public void deserializeUserTest(){
      UserService testUser = new UserService();
      testUser.getTestUserFromJSON("14155550901");
      System.out.println(testUser);
   }

   @Test
   public void getVerificationCodeForExistingTestUser() {
      UserService user = new UserService();
   }

   @Test
   public void loginUser() {
      user.apiLoginTestUser();
      user.getVerificationCode();
      System.out.println(user.getVerificationCode());

      user.apiLoginTestUser();
      user.getVerificationCode();
      System.out.println(user.getVerificationCode());
   }

}
