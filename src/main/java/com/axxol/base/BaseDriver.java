package com.axxol.base;

import com.axxol.utils.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BaseDriver {
    public static WebDriver driver;
    public static WebDriver getDriver() {
        if(driver==null){
            init();
        }
        return driver;
    }

    private static void init(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator + "res"+File.separator+"chromedriver.exe");
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--headless");//无头浏览器
        String type= ReadProperties.getConfig("webType");
        boolean isUserHeadless=Boolean.parseBoolean(ReadProperties.getConfig("isUserHeadless"));
        switch (type){
            case "1":
                if(isUserHeadless){
                    driver=new ChromeDriver(option);
                }else{
                    driver=new ChromeDriver();
                }
                break;
            case "2":
                break;
        }
        driver.manage().window().maximize();
    }
}
