package com.axxol;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestLive extends BaseCase{
    private static String name = "11";//显示在直播间的名字
    private static String id = "11";
    @Test(description = "开启直播界面")
    public void testOpenLive(){
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\User Data\\Default");//默认google浏览器的配置文件路径
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://baidu.com");
        for (int i = 0; i < 2; i++) {
            driver.manage().window().maximize();
            //要打开的链接
            String js = "http://demo.talk-cloud.net/WebAPI/entry/domain/gs2in1/serial/241395302/username/" + name + i + "/usertype/2/pid/" + id + i + "/ts/1565613606/auth/ab742de61934f73deea9706b60e80d23/userpassword/027411310a7788ea4a9e9782c526fd7e/servername/cnb/jumpurl/";
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.open('" + js + "')");//通过js打开新窗口在新窗口中打开上面的链接
            List<String> it1 = new ArrayList<String>(driver.getWindowHandles());
            System.out.println(it1.size());
            driver.switchTo().window(it1.get(it1.size() - 1));//切换到最新的窗口句柄
        }
    }
}
