package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

/**
 * Created on 03/04/17.
 */
public class ListOfChainedUIElement extends ChainedUIElement {

    protected By listBy;
    protected int index;

    public ListOfChainedUIElement(AppiumDriver<MobileElement> driver, By elementBy1, By elementBy2, int index) {
        this(driver, null, elementBy1, elementBy2, index);
    }

    public ListOfChainedUIElement(AppiumDriver<MobileElement> driver, By listBy, By elementBy1, By elementBy2, int index) {
        super(driver, elementBy1, elementBy2);
        this.listBy = listBy;
        this.index = index;
    }

    @Override
    public MobileElement element() {
        List elements;
        if (listBy != null) {
            //MobileElement list = driver.findElement(listBy);
            //elements = list.findElements(by1);

            //MobileElement list = driver.findElement(new ByChained(listBy));
            //elements = list.findElements(by1, by2);

            elements = driver.findElements(new ByChained(by1,by2));

        } else {
            //elements = driver.findElements(by1);
            elements = driver.findElements(new ByChained(by1,by2));
        }
        return (MobileElement) elements.get(index);
    }
}
