package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

/**
 * 
 */
public class BasicUIElement implements UIElement {

    protected AppiumDriver<MobileElement> driver;
    protected final By by;

    public BasicUIElement(AppiumDriver<MobileElement> driver, By by) {
        this.driver = driver;
        this.by = by;
    }

    public By getAccessor() {
        return by;
    }

    public MobileElement element() {
        return driver.findElement(getAccessor());
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
