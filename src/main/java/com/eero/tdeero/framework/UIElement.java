package com.eero.tdeero.framework;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

/**
 * 
 */
public interface UIElement {

    /** Get locator for element */
    By getAccessor();

    MobileElement element();

    /** MobileElement click element */
    void click();

    /** MobileElement sendKeys */
    void sendKeys(String text);

    /** Precise tap on the center of the element */
    void tap();

    void clear();

}
