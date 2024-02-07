package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTable {

    private WebElement wrappedElement;

    public WebTable(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    public List<WebElement> getTableRows() {
        return wrappedElement.findElements(By.className("mr-2"));
    }

}