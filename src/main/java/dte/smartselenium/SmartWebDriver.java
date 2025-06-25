package dte.smartselenium;

import dte.smartselenium.delegation.DelegatingWebDriver;
import dte.smartselenium.elements.ClickableWebElement;
import dte.smartselenium.elements.SafeWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SmartWebDriver extends DelegatingWebDriver
{
    private final JavascriptExecutor javascriptExecutor;

    public SmartWebDriver(WebDriver delegate, JavascriptExecutor javascriptExecutor)
    {
        super(delegate);

        this.javascriptExecutor = javascriptExecutor;
    }

    @Override
    public WebElement findElement(By by)
    {
        WebElement element = super.findElement(by);

        return smartify(element, by);
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return super.findElements(by).stream()
                .map(element -> smartify(element, by))
                .toList();
    }

    private WebElement smartify(WebElement element, By locator)
    {
        return new SafeWebElement(new ClickableWebElement(element, this.javascriptExecutor), this, getCurrentUrl(), locator);
    }
}
