package dte.painless_selenium.delegation;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public abstract class DelegatingWebDriver implements WebDriver
{
    protected final WebDriver delegate;

    protected DelegatingWebDriver(WebDriver delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public void get(String url)
    {
        this.delegate.get(url);
    }

    @Override
    public @Nullable String getCurrentUrl()
    {
        return this.delegate.getCurrentUrl();
    }

    @Override
    public @Nullable String getTitle()
    {
        return this.delegate.getTitle();
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
    public @Nullable String getPageSource()
    {
        return this.delegate.getPageSource();
    }

    @Override
    public void close()
    {
        this.delegate.close();
    }

    @Override
    public void quit()
    {
        this.delegate.quit();
    }

    @Override
    public Set<String> getWindowHandles()
    {
        return this.delegate.getWindowHandles();
    }

    @Override
    public String getWindowHandle()
    {
        return this.delegate.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo()
    {
        return this.delegate.switchTo();
    }

    @Override
    public Navigation navigate()
    {
        return this.delegate.navigate();
    }

    @Override
    public Options manage()
    {
        return this.delegate.manage();
    }
}
