package dte.painless_selenium;

import dte.painless_selenium.delegation.DelegatingWebDriver;
import dte.painless_selenium.elements.ClickableWebElement;
import dte.painless_selenium.elements.SafeWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PainlessWebDriver extends DelegatingWebDriver
{
    private final JavascriptExecutor javascriptExecutor;

    public PainlessWebDriver(WebDriver delegate, JavascriptExecutor javascriptExecutor)
    {
        super(delegate);

        this.javascriptExecutor = javascriptExecutor;
    }

    @Override
    public WebElement findElement(By by)
    {
        WebElement element = super.findElement(by);

        return toPainless(element, by);
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return super.findElements(by).stream()
                .map(element -> toPainless(element, by))
                .toList();
    }

    private WebElement toPainless(WebElement element, By locator)
    {
        return new SafeWebElement(new ClickableWebElement(element, this.javascriptExecutor), this, getCurrentUrl(), locator);
    }
}
