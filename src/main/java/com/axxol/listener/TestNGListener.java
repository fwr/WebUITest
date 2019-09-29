package com.axxol.listener;

import com.axxol.base.BaseDriver;
import com.axxol.utils.FileUtil;
import com.axxol.utils.ReadProperties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestNGListener extends TestListenerAdapter{
    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        String filepath = System.getProperty("user.dir")+File.separator+"test-output/errorImage";
        FileUtil.delAllFile(filepath);
        FileUtil.createFile(filepath);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
       boolean flag=Boolean.parseBoolean(ReadProperties.getConfig("isOpenRetry"));
       if(flag){//重试机制打开才执行
           ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
           // collect all id's from passed test
           Set<Integer> passedTestIds = new HashSet<Integer>();
           for (ITestResult passedTest : iTestContext.getPassedTests().getAllResults()) {
               System.out.println("PassedTests = " + passedTest.getName());
               passedTestIds.add(getId(passedTest));
           }

           Set<Integer> failedTestIds = new HashSet<Integer>();
           for (ITestResult failedTest : iTestContext.getFailedTests().getAllResults()) {
               System.out.println("failedTest = " + failedTest.getName());
               // id = class + method + dataprovider
               int failedTestId = getId(failedTest);

               // if we saw this test as a failed test before we mark as to be
               // deleted
               // or delete this failed test if there is at least one passed
               // version
               if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                   testsToBeRemoved.add(failedTest);
               } else {
                   failedTestIds.add(failedTestId);
               }
           }
           Set<Integer> skiTestIds = new HashSet<Integer>();
           for (ITestResult skipTest : iTestContext.getSkippedTests().getAllResults()) {
               System.out.println("skipTest = " + skipTest.getName());
               // id = class + method + dataprovider
               int failedTestId = getId(skipTest);

               // if we saw this test as a failed test before we mark as to be
               // deleted
               // or delete this failed test if there is at least one passed
               // version
               if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                   testsToBeRemoved.add(skipTest);
               } else {
                   failedTestIds.add(failedTestId);
               }
               if (skiTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                   testsToBeRemoved.add(skipTest);
               } else {
                   failedTestIds.add(failedTestId);
               }
           }
           // finally delete all tests that are marked
           for (Iterator<ITestResult> iterator = iTestContext.getFailedTests().getAllResults().iterator(); iterator
                   .hasNext();) {
               ITestResult testResult = iterator.next();
               if (testsToBeRemoved.contains(testResult)) {
                   System.out.println("Remove repeat Fail Test: " + testResult.getName());
                   iterator.remove();
               }
           }
           // finally delete all tests that are marked
           for (Iterator<ITestResult> iterator1 = iTestContext.getSkippedTests().getAllResults().iterator(); iterator1
                   .hasNext();) {
               ITestResult testResult = iterator1.next();
               if (testsToBeRemoved.contains(testResult)) {
                   System.out.println("Remove repeat Fail Test: " + testResult.getName());
                   iterator1.remove();
               }
           }
       }
    }
    private int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = id + result.getMethod().getMethodName().hashCode();
        id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
    }

    @Override
    public void onTestFailure(ITestResult tr){
        System.out.println("--TestNgListener--------------------------------");
        try {
            super.onTestFailure(tr);
            //调用屏幕截图方法
//            captureScreenShot(tr.getMethod().getMethodName());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void captureScreenShot(String name) {

        WebDriver driver=BaseDriver.getDriver();
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filepath = System.getProperty("user.dir")+File.separator+"test-output/errorImage";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        try {
            FileUtils.copyFile(srcFile, new File(filepath + File.separator +name+"-"+ dateFormat.format(new Date()) + ".png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
