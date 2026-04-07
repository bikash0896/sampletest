package com.sample.test;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SwagLabs {

	public static void main(String[] args) throws Exception 
	{
		URL u=URI.create("http://192.168.1.3:4723/").toURL();
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("udid","emulator-5554");
		dc.setCapability("platformName","Android");
		dc.setCapability("platformVersion","15");
		dc.setCapability("automationName","UiAutomator2");
		dc.setCapability("appPackage","com.swaglabsmobileapp");
		dc.setCapability("appActivity",".MainActivity");		
		
		AppiumDriver driver=new AndroidDriver(u,dc);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//swipe to bottom to choose and click on the required login credentials
		
		Thread.sleep(2000);
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence swipe=new Sequence(finger,1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),536,1751));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),527,1117));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));		
		
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"standard_user\")")).click();
		driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
		Thread.sleep(3000);		
		
		//Verify whether it has been navigate to the products page or not
//		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")")));
		
		//or else
		
		if(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")")).isDisplayed())
		{
			System.out.println("We're at the Products Page");
		}
		else
		{
			System.out.println("Login Failed");
		}
		Thread.sleep(5000);
		driver.quit();	
		
	}

}
