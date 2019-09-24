package com.axxol.utils;

import com.axxol.base.BaseDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
    private int time=10;
    private WebDriver driver = null;
    private static int WAIT_TIME = 1;
    Logger logger;
    public SeleniumUtils() {
        this.driver = BaseDriver.getDriver();
        logger= Logger.getLogger(SeleniumUtils.class);
    }
    /**
     * 判断控件时候存在
     *
     * @param by
     *            By
     * @return
     */
    public boolean isElementExist(By by, boolean isShow) {
        try {
            WebElement element = waitAuto(by);
            if (element == null) {
                return false;
            } else {
                if (isShow) {
                    return element.isDisplayed();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 显示等待，等待Id对应的控件出现time秒，一出现马上返回，time秒不出现也返回
     */
    public WebElement waitAuto(final By by) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            element = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            logger.error("***" + "查找控件" + by.toString() + "超时！");
            return null;
        }
        return element;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
