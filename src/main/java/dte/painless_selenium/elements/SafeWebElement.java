package dte.painless_selenium.elements;

import dte.painless_selenium.delegation.DelegatingWebElement;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SafeWebElement extends DelegatingWebElement
{
    private final WebDriver webDriver;
    private final String pageURL;
    private final By locator;

    public SafeWebElement(WebElement delegate, WebDriver webDriver, String pageURL, By locator)
    {
        super(delegate);

        this.webDriver = webDriver;
        this.pageURL = pageURL;
        this.locator = locator;
    }

    @Override
    public void click()
    {
        safelyRun(WebElement::click);
    }

    @Override
    public void submit()
    {
        safelyRun(WebElement::submit);
    }

    @Override
    public void sendKeys(CharSequence... keysToSend)
    {
        safelyRun(delegate -> sendKeys(keysToSend));
    }

    @Override
    public void clear()
    {
        safelyRun(WebElement::clear);
    }

    @Override
    public String getTagName()
    {
        return safelyExecute(WebElement::getText);
    }

    @Override
    public @Nullable String getDomProperty(String name)
    {
        return safelyExecute(delegate -> delegate.getDomProperty(name));
    }

    @Override
    public @Nullable String getDomAttribute(String name)
    {
        return safelyExecute(delegate -> delegate.getDomAttribute(name));
    }

    @Override
    public @Nullable String getAttribute(String name)
    {
        return safelyExecute(delegate -> delegate.getAttribute(name));
    }

    @Override
    public @Nullable String getAriaRole()
    {
        return safelyExecute(WebElement::getAriaRole);
    }

    @Override
    public @Nullable String getAccessibleName()
    {
        return safelyExecute(WebElement::getAccessibleName);
    }

    @Override
    public boolean isSelected()
    {
        return safelyExecute(WebElement::isSelected);
    }

    @Override
    public boolean isEnabled()
    {
        return safelyExecute(WebElement::isEnabled);
    }

    @Override
    public String getText()
    {
        return safelyExecute(WebElement::getText);
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return safelyExecute(delegate -> delegate.findElements(by));
    }

    @Override
    public WebElement findElement(By by)
    {
        return safelyExecute(delegate -> delegate.findElement(by));
    }

    @Override
    public SearchContext getShadowRoot()
    {
        return safelyExecute(WebElement::getShadowRoot);
    }

    @Override
    public boolean isDisplayed()
    {
        return safelyExecute(WebElement::isDisplayed);
    }

    @Override
    public Point getLocation()
    {
        return safelyExecute(WebElement::getLocation);
    }

    @Override
    public Dimension getSize()
    {
        return safelyExecute(WebElement::getSize);
    }

    @Override
    public Rectangle getRect()
    {
        return safelyExecute(WebElement::getRect);
    }

    @Override
    public String getCssValue(String propertyName)
    {
        return safelyExecute(delegate -> delegate.getCssValue(propertyName));
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException
    {
        return safelyExecute(delegate -> delegate.getScreenshotAs(target));
    }

    private <T> T safelyExecute(Function<WebElement, T> delegateGetter)
    {
        try
        {
            return delegateGetter.apply(super.delegate);
        }
        catch(StaleElementReferenceException exception)
        {
            restore();

            //if the element remains stale, an exception is better than an infinite loop
            return delegateGetter.apply(super.delegate);
        }
    }

    private void safelyRun(Consumer<WebElement> delegateRunner)
    {
        safelyExecute(delegate ->
        {
            delegateRunner.accept(delegate);
            return null;
        });
    }

    private void restore()
    {
        //navigate to the page where the element appeared
        if(!this.webDriver.getCurrentUrl().equals(this.pageURL))
            this.webDriver.get(this.pageURL);

        //relocate the element
        super.delegate = this.webDriver.findElement(this.locator);
    }
}
