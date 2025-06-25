package dte.smartselenium.elements;

import dte.smartselenium.delegation.DelegatingWebElement;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ClickableWebElement extends DelegatingWebElement
{
    private final JavascriptExecutor javascriptExecutor;

    public ClickableWebElement(WebElement delegate, JavascriptExecutor javascriptExecutor)
    {
        super(delegate);

        this.javascriptExecutor = javascriptExecutor;
    }

    @Override
    public void click()
    {
        try
        {
            super.click();
        }
        catch(ElementClickInterceptedException exception)
        {
            this.javascriptExecutor.executeScript("arguments[0].click();", super.delegate);
        }
    }
}
