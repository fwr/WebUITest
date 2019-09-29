package com.axxol;

import org.testng.Assert;
import org.testng.annotations.Listeners;

public class Test extends BaseCase {
    @org.testng.annotations.Test
    public void test01(){
        Assert.assertEquals(1,2);
    }
    @org.testng.annotations.Test
    public void test02(){
        Assert.assertEquals("5","8");
    }
}
