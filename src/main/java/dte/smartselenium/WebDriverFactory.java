package dte.smartselenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory
{
    public static WebDriver smartify(WebDriver webDriver)
    {
        if(!(webDriver instanceof JavascriptExecutor executorDriver))
            throw new IllegalArgumentException("A WebDriver that implements JavascriptExecutor must be provided.");

        return new SmartWebDriver(webDriver, executorDriver);
    }
}