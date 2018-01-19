package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.pagefactory.ByChained;

/**
 * Created on 03/04/17.
 */
public class ChainedUIElement implements UIElement {

    protected AppiumDriver<MobileElement> driver;
    protected final By by1;
    protected final By by2;

    public ChainedUIElement(AppiumDriver<MobileElement> driver, By by1, By by2){

        this.driver = driver;
        this.by1 = by1;
        this.by2 = by2;
    }

    //@Override
    public By getAccessor() {
        return by1;
    }

    public By getAnotherAccessor() {
        return by2;
    }

    public MobileElement element() {
        return driver.findElement(new ByChained(getAccessor(),getAnotherAccessor()));
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
//List<String>