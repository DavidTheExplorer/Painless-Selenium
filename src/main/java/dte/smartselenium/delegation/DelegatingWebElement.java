package dte.smartselenium.delegation;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class DelegatingWebElement implements WebElement
{
    protected WebElement delegate;

    protected DelegatingWebElement(WebElement delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public void click()
    {
        this.delegate.click();
    }

    @Override
    public void submit()
    {
        this.delegate.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend)
    {
        this.delegate.sendKeys(keysToSend);
    }

    @Override
    public void clear()
    {
        this.delegate.clear();
    }

    @Override
    public String getTagName()
    {
        return this.delegate.getTagName();
    }

    @Override
    public @Nullable String getDomProperty(String name)
    {
        return this.delegate.getDomProperty(name);
    }

    @Override
    public @Nullable String getDomAttribute(String name)
    {
        return this.delegate.getDomAttribute(name);
    }

    @Override
    public @Nullable String getAttribute(String name)
    {
        return this.delegate.getAttribute(name);
    }

    @Override
    public @Nullable String getAriaRole()
    {
        return this.delegate.getAriaRole();
    }

    @Override
    public @Nullable String getAccessibleName()
    {
        return this.delegate.getAccessibleName();
    }

    @Override
    public boolean isSelected()
    {
        return this.delegate.isSelected();
    }

    @Override
    public boolean isEnabled()
    {
        return this.delegate.isEnabled();
    }

    @Override
    public String getText()
    {
        return this.delegate.getText();
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return this.delegate.findElements(by);
    }

    @Override
    public WebElement findElement(By by)
    {
        return this.delegate.findElement(by);
    }

    @Override
    public SearchContext getShadowRoot()
    {
        return this.delegate.getShadowRoot();
    }

    @Override
    public boolean isDisplayed()
    {
        return this.delegate.isDisplayed();
    }

    @Override
    public Point getLocation()
    {
        return this.delegate.getLocation();
    }

    @Override
    public Dimension getSize()
    {
        return this.delegate.getSize();
    }

    @Override
    public Rectangle getRect()
    {
        return this.delegate.getRect();
    }

    @Override
    public String getCssValue(String propertyName)
    {
        return this.delegate.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException
    {
        return this.delegate.getScreenshotAs(target);
    }
}
