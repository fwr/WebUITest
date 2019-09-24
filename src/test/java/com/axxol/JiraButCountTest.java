package com.axxol;

import com.axxol.utils.Utils;
import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JiraButCountTest extends BaseCase{
    @BeforeClass
    public void beforeClass(){
//        log4j=Logger.getLogger(JiraButCountTest.class);
    }
    //某个指定项目的url
    private String url="http://jira.dev.aixuexi.com/issues/?jql=project%20%3D%20AXXZX%20AND%20issuetype%20%3D%20Bug%20AND%20status%20in%20(打开%2C%20处理中%2C%20重新打开)%20AND%20text%20~%20\"服务迁移\"%20ORDER%20BY%20status%20ASC";
    private String weixin_jiqi="https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=565a7254-2d30-47c1-a8fd-d6688ee7bb8a";

    @Test(description = "统计jira bug")
    public void testCount() throws IOException {
        Assert.assertEquals(1,2);
        System.setProperty("webdriver.chrome.driver","E:\\AndroidHaoke91\\res\\chromedriver.exe");
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--headless");//无头浏览器
        WebDriver driver=new ChromeDriver(option);
        driver.get(url);
        driver.manage().window().maximize();
        Utils.sleep(3);
        WebElement element=driver.findElement(By.linkText("登录"));
        StringBuffer message=new StringBuffer();
        if(element!=null){
            element.click();
            Utils.sleep(10);
            driver.findElement(By.name("os_username")).sendKeys("fuwanrong");
            driver.findElement(By.name("os_password")).sendKeys("meng123");
            driver.findElement(By.xpath("//input[@value='登录']")).click();
            Utils.sleep(10);
            driver.get(url);
            WebElement e=driver.findElement(By.xpath("//span[@class='results-count-total results-count-link']"));
            if(e!=null){
                List<WebElement> es= driver.findElements(By.xpath("//a[@class='issue-link']"));
                if(es!=null&&es.size()>0){
                    message.append("目前还有如下问题未解决：\n");
                    int count=1;
                    for (int i=0;i<es.size();i++){
                        if(i%3==2){
                            message.append((count)+"、问题："+es.get(i).getText()+"\n"+es.get(i).getAttribute("href")+"\n");
                            count++;
                        }
                    }
//                    log4j.info("执行结果："+message.toString());
//                    sendMessage(message.toString(),weixin_jiqi);
                }else{
//                    log4j.info("没有定位到元素");
                }
            }
        }
    }

    /**
     *
     * @param message  消息体内容
     * @param url 机器人的url
     */
    private void sendMessage(String message,String url){
        String reqBody ="{" +
                "    \"touser\":\"12345\"," +
                "    \"msgtype\":\"text\"," +
                "    \"agentid\":1," +
                "    \"text\":{" +
                "        \"content\":\"" +message+
                " \"safe\":1" +
                "}";

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)// 设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)// 设置读取超时时间
                .build();
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(contentType, reqBody);
        Request request = new Request.Builder().url(weixin_jiqi).post(body).addHeader("cache-control", "no-cache").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}