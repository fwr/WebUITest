package com.axxol;

import com.axxol.base.BaseDriver;
import com.axxol.listener.TestNGListener;
import com.axxol.listener.ZTestReport;
import com.axxol.utils.ExcelDataHeleper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


@Listeners({ZTestReport.class, TestNGListener.class})
public class BaseCase extends ExcelDataHeleper {
    protected Logger logger;
    protected WebDriver driver;
    @BeforeSuite
    public void setUp(){
        logger= Logger.getLogger(BaseCase.class);
        driver= BaseDriver.getDriver();
    }
}
