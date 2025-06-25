# Smart Selenium
When working with [Selenium](https://www.selenium.dev/), it's common to run into these unbearable problems:
* `WebElement#click` throws `ElementClickInterceptedException` for no apparent reason.
* It's impossible to keep elements in memory because they're tied to the current page - causing `StaleElementReferenceException`.

While some can be resolved using a util class, others require an ugly architectural refactor on your end.\
This library ends the misery by changing 1 line of your code.

## Usage
All you need to do is create your `WebDriver` like this:
```java
WebDriver driver = WebDriverFactory.smartify(yourWebDriver);
```
and suddenly this is possible:
```java
driver.get("www.website.com");
WebElement element = driver.findElement(someLocator);
driver.get("www.anotherWebsite.com");
        
//without the library -> throws StaleElementReferenceException
System.out.println(element.getText());
```

## Behind the scenes
The library wraps `WebDriver` methods that produce `WebElement` objects with smart wrappers - so your code doesn't change!\
The [elements package](https://github.com/DavidTheExplorer/Smart-Selenium/tree/development/src/main/java/dte/smartselenium/elements) contains the juice.


## Import
Maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.DavidTheExplorer</groupId>
    <artifactId>Smart-Selenium</artifactId>
    <version>[latest-version]</version>
</dependency>
```
