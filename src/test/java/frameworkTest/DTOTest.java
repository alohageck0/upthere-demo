package frameworkTest;

import com.eero.qa.apis.JSONSerializer;
import com.eero.qa.apis.testEnv.testEnvironmentDTO.EnvironmentDTO;
import com.eero.tdeero.apis.adblock.AdBlockBenchmarkDTO.AdBlockBenchmarkDTO;
import org.testng.annotations.Test;

public class DTOTest {
   private static final String projectPath = System.getProperty("user.dir");

   @Test
   public void testAdblockDTO() {
      AdBlockBenchmarkDTO dto = new AdBlockBenchmarkDTO();

      String jsonPath = projectPath + "/src/main/java/com/eero/tdeero/apis/adblock/adblockbenchmark.json";
      dto = new JSONSerializer().deserialize(jsonPath, AdBlockBenchmarkDTO.class);
      System.out.println(dto.getData());
      System.out.println(dto.getData().getAddressList());
      System.out.println(dto.getData().getAddressList().get(0));
      System.out.println(dto.getData().getAddressList().get(0).getRequests().get(0).getMessage());
   }

   @Test
   public void testToken() {
      String practitestApiToken = System.getenv("PRACTITEST_TOKEN");
      String devEmail = System.getenv("DEVELOPER_EMAIL");
//      String novariable = "internal";
      String novariable = System.getenv("build_version");
      boolean testBol = Boolean.parseBoolean(System.getenv("build_version"));
      if (devEmail.equals("evgenii@eero.com")) {
         System.out.println("success variable");
         System.out.println(testBol);
      }

      System.out.printf("%s %s", practitestApiToken, devEmail);
   }

   @Test
   public void testEnvDTO() {
      EnvironmentDTO dto = new EnvironmentDTO();
      String jsonPath = projectPath + "/src/testEnv.json";
      dto = new JSONSerializer().deserialize(jsonPath, EnvironmentDTO.class);
      System.out.println(dto.getIfSync());
   }
}
