package com.axxol.listener;

import com.axxol.utils.ReadProperties;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < Integer.parseInt(ReadProperties.getConfig("retryCount"))) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            retryCount++;

            return true;
        }
        resetRetrycount(); // 每次跑完一条用例后，重置retryCount为0，这样dataProvider 数据驱动测试叶支持
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public void resetRetrycount() {
        retryCount = 0;
    }

}
