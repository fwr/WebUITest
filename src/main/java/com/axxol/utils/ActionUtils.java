package com.axxol.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionUtils {
	/**
	 * js操作点击某个元素
	 */
	public static void elementClick(WebElement element, WebDriver driver) {
		// JavascriptExecutor 定义
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js操作上面的元素
		js.executeScript("arguments[0].click();", element);
	}
	/**
	 * 滑动到指定元素处
	 */
	public static void scrollingToView(WebDriver driver, WebElement element) {
		// 使用JavaScript的scrollIntoView()方法将滚动条滚动到页面的指定元素位置
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	/**
	 * 从某个位置滑动到某个位置
	 */
	public static void scrollingToPosition(WebDriver driver, int start, int end) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo("+start+","+end+")");
	}
	
}
