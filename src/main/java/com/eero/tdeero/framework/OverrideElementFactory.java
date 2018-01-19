package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.File;

/**
 * 
 */
public class OverrideElementFactory extends ElementFactory {

    private ElementFactory defaultFactory;

    public OverrideElementFactory(AppiumDriver<MobileElement> driver, ElementFactory defaultFactory, File overrideProperties) {
        super(driver, overrideProperties);
        this.defaultFactory = defaultFactory;
    }



    @Override
    public UIElement createElement(String propertyName) {

        UIElement uielement = super.createElement(propertyName);
        if(uielement == null) uielement = defaultFactory.createElement(propertyName);
        return uielement;
    }



    @Override
    public UIElement createElement(String propertyName, String[] parameters) {

        UIElement uielement = super.createElement(propertyName, parameters);
        if(uielement == null) uielement = defaultFactory.createElement(propertyName, parameters);
        return uielement;
    }

}
