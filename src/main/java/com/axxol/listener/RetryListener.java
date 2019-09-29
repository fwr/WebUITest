package com.axxol.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod)    {
        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
        System.out.println("_____2___________");
        if (retry == null)    {
            testannotation.setRetryAnalyzer(Retry.class);
        }

    }
}
