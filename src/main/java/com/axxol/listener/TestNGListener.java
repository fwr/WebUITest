package com.axxol.listener;

import com.axxol.base.BaseDriver;
import com.axxol.utils.FileUtil;
import org.apache.commons.io.FileUtils;
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
import java.util.Date;

public class TestNGListener extends TestListenerAdapter{

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        String filepath = System.getProperty("user.dir")+File.separator+"test-output/errorImage";
        FileUtil.delAllFile(filepath);
        FileUtil.createFile(filepath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
    }

    @Override
    public void onTestFailure(ITestResult tr){
        System.out.println("----------------------------------");
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
