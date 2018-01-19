package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

/**
 * 
 */
public class ListUIElement extends BasicUIElement {

    protected By listBy;
    protected int index;

    public ListUIElement(AppiumDriver<MobileElement> driver, By elementBy, int index) {
        this(driver, null, elementBy, index);
    }

    public ListUIElement(AppiumDriver<MobileElement> driver, By listBy, By elementBy, int index) {
        super(driver, elementBy);
        this.listBy = listBy;
        this.index = index;
    }

    @Override
    public MobileElement element() {
        List elements;
        if (listBy != null) {
            MobileElement list = driver.findElement(listBy);
            elements = list.findElements(by);
        } else {
            elements = driver.findElements(by);
        }
        return (MobileElement) elements.get(index);
    }
}
