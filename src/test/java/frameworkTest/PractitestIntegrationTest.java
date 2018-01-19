package frameworkTest;

import com.eero.qa.apis.JSONSerializer;
import com.eero.qa.apis.practitestIntegration.*;
import com.eero.qa.apis.practitestIntegration.cloneTestSetRequestDTO.CloneTestSetRequestDTO;
import com.eero.qa.apis.practitestIntegration.getTestSetDTO.GetTestSetInfoDTO;
import com.eero.qa.apis.testEnv.testEnvironmentDTO.EnvironmentDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eero.tdeero.AbstractTest.PROJECT_PATH;

/**
 * PractitestIntegrationTest created to test Practitest APIs.
 *
 * @author evgenii evgenii@eero.com
 */
public class PractitestIntegrationTest {
   //Testing test suite https://prod.practitest.com/p/4425/sets/233699/edit
   private String testSetID = "233699";
   Map<Integer, ArrayList<String>> mapOfInstances = new HashMap<>(); //into abstract test
   private EnvironmentDTO testEnv = new EnvironmentDTO();
   private List<String> listOfInstances = new ArrayList<>();

   private ResultData testResults = new ResultData();
   private static ListOfSteps listOfSteps = new ListOfSteps();
   private PractitestSyncService practitestSyncService = new PractitestSyncService();
   private List<Integer> listOfTestSetsID = new ArrayList<>();

   public ResultData getTestResults() {
      return testResults;
   }

   public ListOfSteps getListOfSteps() {
      return listOfSteps;
   }

   public PractitestSyncService getPractitestSyncService() {
      return practitestSyncService;
   }

   public void setTestResults(ResultData testResults) {
      this.testResults = testResults;
   }

   @BeforeClass
   public void setup() {
      String testEnvPath = "/Users/evgenii/IdeaProjects/mobile-automation/eero_appium_1.7/src/testEnv.json";
      testEnv = new JSONSerializer().deserialize(testEnvPath, EnvironmentDTO.class);
      testEnv.setMobilePlatform(MobilePlatform.Android);
//      System.out.println(testEnv.getTestSetID().get(0));
//      addAllInstancesInSet("240982");  //test suite
//      addAllInstancesInSet("256489");  //test suite multiple sync
   }

//   @BeforeMethod
   public void setUpMethod() {
      String testEnvPath = "/Users/evgenii/IdeaProjects/mobile-automation/eero_appium_1.7/clonedTestID.json";
      testEnv = new JSONSerializer().deserialize(testEnvPath, EnvironmentDTO.class);
      testEnv.setMobilePlatform(MobilePlatform.Android);
      mapOfInstances = getPractitestSyncService().mapTestIDtoInstance(testEnv.getTestSetID());
      setupSync();
   }

   @AfterClass
   public void tearDown() {
      getTestResults().setStepsData(getListOfSteps());
      if (testEnv.getIfSync() && getListOfSteps().isNeedToSync()) {
         getPractitestSyncService().updateInstance();
         getPractitestSyncService().createRun();
      }
      getListOfSteps().clearSteps();
   }

   @Test
   public void test1WithFail() {
      getTestResults().setInstanceID(6281025);
      getTestResults().setInstanceID(7233762);

      //Some step
      getListOfSteps().addStep("Step1", "expected to pass");
      //some actions
      getListOfSteps().setPassed();

      //other step
      getListOfSteps().addStep("Step2", "expected to pass");
      //another action
      getListOfSteps().setPassed();

      getListOfSteps().addStep("Step3", "expected to fail");
      Assert.fail();
      getListOfSteps().setPassed();
   }

   @Test
   public void testAllPassed() {
      getTestResults().setInstanceID(6281026);
      getTestResults().setInstanceID(7233763);

      //Some step
      getListOfSteps().addStep("Step1", "Another result");
      getListOfSteps().setPassed();

      //Some step
      getListOfSteps().addStep("Step2", "Another result");
      getListOfSteps().setPassed();

   }

   @Test
   public void test2() {
      setInstance(972122);
      //Some step
      getListOfSteps().addStep("Step1", "result");
      getListOfSteps().setStatus(Status.BLOCKED);

      //Some step
      getListOfSteps().addStep("Step2", "result");
      getListOfSteps().setStatus(Status.PASSED);

   }

   @Test
   public void testSetUpdateNoRun() {
      getTestResults().setSyncInstanceList(getListOfInstances());
      getListOfSteps().addStep("No run", "No run");
      getListOfSteps().setNoRun();
   }

   @Test
   public void testCloneTestSet() {
      listOfTestSetsID.add(261820);
      listOfTestSetsID.add(261822);
      List<String> newTestSetIDList = new ArrayList<>();
      if (listOfTestSetsID.size() != 0) {
         for (Integer testSetID : listOfTestSetsID) {
            String testIDtoClone = String.valueOf(testSetID);
            GetTestSetInfoDTO getTestSetInfoDTO = getPractitestSyncService().getTestSetInfo(testIDtoClone);
            CloneTestSetRequestDTO cloneTestSetRequestDTO = new CloneTestSetRequestDTO();
            cloneTestSetRequestDTO.setAppVersion(getTestEnv().getAppVersion());
            cloneTestSetRequestDTO.setBuild(getTestEnv().getBuild());
            cloneTestSetRequestDTO.setRelease(getTestEnv().getRelease().toString());
            cloneTestSetRequestDTO.setTestSetName(getTestSetInfoDTO.getTestSetName() + " build " + getTestEnv().getBuild());
            String newTestSetID = getPractitestSyncService().cloneTestSet(testIDtoClone, cloneTestSetRequestDTO);
            newTestSetIDList.add(newTestSetID);
//            log.info(testSetID + " successfully cloned to " + newTestSetID);
         }
         getTestEnv().setClonedTestID(newTestSetIDList);
         new JSONSerializer().serialize(PROJECT_PATH + "/clonedTestID.json", getTestEnv());
      } else {
         System.out.println("No test sets to clone");
//         log.info("No test sets to clone");
      }
   }

//   @Test
//   public void testMapping() {
//      Map<Integer, ArrayList<String>> map = getPractitestSyncService().mapTestIDtoInstance(listOfTestSetsID);
//      System.out.println(map.toString());
//   }

   @Test
   public void testAllInstances() {
      addAllInstancesInSet(testSetID);
   }

   public void setupSync() {
      List<String> testSetIDList = getTestEnv().getTestSetID();
      mapOfInstances = getPractitestSyncService().mapTestIDtoInstance(testSetIDList);
      testResults = new ResultData();
      testResults.setBuild(testEnv.getBuild());
      testResults.setDevice(testEnv.getDevice());
      testResults.setMobilePlatform(testEnv.getMobilePlatform());
      testResults.setRelease(testEnv.getRelease());
      testResults.setAppVersion(testEnv.getAppVersion());
      practitestSyncService = new PractitestSyncService();
      getListOfSteps().setNeedToSync();
      getTestResults().setTestSetIDList(testSetIDList);
      getPractitestSyncService().setData(getTestResults());
      getPractitestSyncService().updateSet();
   }

   public void addAllInstancesInSet(String testSetID) {
      listOfInstances.addAll(getPractitestSyncService().getAllInstancesInSet(testSetID, testEnv.getBuild()));
   }

   public List<String> getListOfInstances() {
      return listOfInstances;
   }

   public EnvironmentDTO getTestEnv() {
      return testEnv;
   }

   public void setInstance(int testID) {
      System.out.println(mapOfInstances.get(testID));
      testResults.setInstanceID(mapOfInstances.get(testID));
   }
}
