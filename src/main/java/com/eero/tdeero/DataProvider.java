package com.eero.tdeero;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {

   @org.testng.annotations.DataProvider(parallel = true)
   public static Object[][] getSites() {
      Object[][] data = null;
      int lines;
      try (BufferedReader br = new BufferedReader(new FileReader("blacklist"))) {
         lines = Integer.parseInt(br.readLine());
         data = new Object[lines][1];
         for (int line = 0; line < lines; line++) {
            String site = br.readLine();
//            System.out.println(site);
            data[line][0] = site;
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return data;
   }

   @org.testng.annotations.DataProvider(parallel = true)
   public static Iterator<Object[]> getLoginUser() {
      List<Object[]> testCases = new ArrayList<>();
      String[] data = null;
      try (BufferedReader br = new BufferedReader(new FileReader("/Users/evgenii/IdeaProjects/mobile-automation/eero_appium_1.7/src/test/java/testUser/loginUser"))) {
         String line;
         while ((line = br.readLine()) != null) {
            data = new String[]{line};
            testCases.add(data);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return testCases.iterator();
   }

   @Test(groups = {"framework"})
   public void testDataProvider() throws IOException {
      getSites();
   }
}
