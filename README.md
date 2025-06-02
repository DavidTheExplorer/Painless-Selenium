# Painless Selenium
When working with [Selenium](https://www.selenium.dev/), it's common to run into these unbearable problems:
* `WebElement#click` throws `ElementClickInterceptedException` for no apparent reason.
* It's impossible to keep elements in memory because they're tied to the current page - causing `StaleElementReferenceException`.

While some can be resolved using a util class, others require an ugly architectural refactor on your end.\
This library ends the misery by changing 1 line of your code.

## Usage
All you need to do is create your `WebDriver` like this:
```java
WebDriver driver = WebDriverFactory.painless(yourWebDriver);
```
and that's it.

## Behind the scenes
The library wraps `WebDriver` methods that produce `WebElement` objects with smart wrappers - so your code doesn't change!\
The [elements package](https://github.com/DavidTheExplorer/Painless-Selenium/tree/master/src/main/java/dte/painless_selenium/elements) contains the juice.


## Import
Maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.DavidTheExplorer</groupId>
    <artifactId>Painless-Selenium</artifactId>
    <version>[latest-version]</version>
</dependency>
```
