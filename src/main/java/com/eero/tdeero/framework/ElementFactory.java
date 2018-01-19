package com.eero.tdeero.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 
 */
public class ElementFactory {

    protected AppiumDriver<MobileElement> driver;
    protected Properties elements;

    public ElementFactory(AppiumDriver<MobileElement> driver, File properties) {
        this.driver = driver;
        this.elements = new Properties();
        try {
            elements.load(new FileInputStream(properties));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Loading element properties failed");
        }
    }

    public UIElement createElement(String propertyName) {
        return createElement(propertyName, null);
    }

    public UIElement createElement(String propertyName, String[] parameters) {
        String val = elements.getProperty(propertyName);
        if (val == null || val.length() == 0) {
            //System.err.println("No accessor identifier found for property " + propertyName);
            return null;
        }
        if (parameters != null && parameters.length > 0) {
            val = MessageFormat.format(val, parameters);
        }
        String[] parts = val.split("#");
        String type = "name";
        String id = parts[0];
        String chained_parameter = parts[0];
        if (parts.length == 2) {
            type = parts[0];
            id = parts[1];
        }
        if (parts.length > 2) {
            type = parts[0];
            id = parts[1];
            chained_parameter = parts[2];
        }
        if ("id".equalsIgnoreCase(type)) {
            return createElement(By.id(id));
        } else if ("name".equalsIgnoreCase(type)) {
            return createElement(By.name(id));
        } else if ("xpath".equalsIgnoreCase(type)) {
            return createElement(By.xpath(id));
        } else if ("class".equalsIgnoreCase(type)) {
            return createElement(By.className(id));
        } else if ("list".equalsIgnoreCase(type)) {
            int index = Integer.parseInt(id);
            String xpath = parts[2];
            return new ListUIElement(driver, By.xpath(xpath), index);
        }
        else if ("list_id".equalsIgnoreCase(type)) {
            int index = Integer.parseInt(id);
            String xpath = parts[2];
            return new ListUIElement(driver, By.id(xpath), index);
        }
        else if ("access_id_thing".equalsIgnoreCase(type)) {
            return new AccessibilityElement(driver, (id));
        }
        else if ("chained_id_and_name".equalsIgnoreCase(type)) {
            return new ChainedUIElement(driver, By.id(id), By.name(chained_parameter));
        }
        else if ("chained_class_and_name".equalsIgnoreCase(type)) {
            return new ChainedUIElement(driver, By.className(id), By.name(chained_parameter));
        }
        else if ("chained_class_and_id".equalsIgnoreCase(type)) {
            return new ChainedUIElement(driver, By.className(id), By.id(chained_parameter));
        }
        else if ("chained_class_and_name".equalsIgnoreCase(type)) {
            return new ChainedUIElement(driver, By.className(id), By.name(chained_parameter));
        }
        else if ("list_chained_id_and_class".equalsIgnoreCase(type)) {
            int index = Integer.parseInt(id);
            String element1 = parts[2];
            String element2 = parts[3];
            return new ListOfChainedUIElement(driver, By.id(element1), By.className(element2), index);
        }
        else if ("list_chained_class_and_class".equalsIgnoreCase(type)) {
            int index = Integer.parseInt(id);
            String element1 = parts[2];
            String element2 = parts[3];
            return new ListOfChainedUIElement(driver, By.className(element1), By.className(element2), index);
        }
        else {
            System.err.println("Undefined typename '" + id + "' for property " + propertyName);
            return null;
        }
    }

    public UIElement createElement(By by) {
        if (by != null) {
            return new BasicUIElement(driver, by);
        } else return null;
    }
}
