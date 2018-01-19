package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

/**
 * Created on 03/04/17.
 */
public class AccessibilityElement implements UIElement {

    protected AppiumDriver<MobileElement> driver;
    protected String using;

    public AccessibilityElement(AppiumDriver<MobileElement> driver, String using){

        this.driver = driver;
        this.using = using;
    }

    //@Override
    public By getAccessor() {
        return null;
    }

    public String getAnotherAccessor() {
        return using;
    }

    public MobileElement element() {
        System.out.print("findElementByAccessibilityId:"+getAnotherAccessor());
        return driver.findElementByAccessibilityId(getAnotherAccessor());
    }

    public void click() {
        element().click();
    }

    public void sendKeys(String text) {
        element().sendKeys(text);
    }

    public void tap() {
        MobileElement element = element();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        driver.tap(1, location.x + size.width / 2, location.y + size.height / 2, 1000);
    }

    public void clear() {
        element().clear();
    }
}
