package test;


import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.*;

public class BasePageObject<T extends WebDriver> {

    public static final Duration DEFAULT_TIMEOUT= ofSeconds(10);
    public static final Duration EXTENDED_TIMEOUT= ofSeconds(10);
    protected final T lDriver;

    WebDriverWait wait;

    public BasePageObject(WebDriver dependencies){
        lDriver =(T) dependencies;
        PageFactory.initElements(lDriver, this);
        WebDriverWait wait = new WebDriverWait(lDriver, Duration.ofSeconds(10));

        getWait(EXTENDED_TIMEOUT).until(webDriver ->{
            JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
            return jsDriver.executeScript("return document.readyState").equals("complete");
        });
    }

    private FluentWait<WebDriver> getWait(Duration duration){
        return new WebDriverWait(lDriver, duration.getSeconds()).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected void  click(WebElement element){
        click(element, DEFAULT_TIMEOUT);
    }
    protected void click(WebElement element, Duration timeout){
        getWait(timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected WebElement getElement(By locator){ return getElement(locator, DEFAULT_TIMEOUT); }
    protected WebElement getElement(By locator, Duration timeout){
        try{
            return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException e){
            return null;
        }
    }

    protected List<WebElement> getElements(By locator){return getElements(locator, DEFAULT_TIMEOUT);}
    protected List<WebElement> getElements(By locator, Duration tiemout){
        try{
            return getWait(tiemout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException | java.util.NoSuchElementException e){
            return null;
        }
    }

    protected String getText(WebElement element){return getText(element, DEFAULT_TIMEOUT);}
    protected String getText(WebElement element, Duration timeout){
        try{
            getWait(timeout).until(ExpectedConditions.attributeToBeNotEmpty(element, "innerHTML"));
            return getWait(timeout).until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (TimeoutException | NoSuchElementException e){
            return null;
        }
    }

    protected boolean isElementDisplayed(WebElement element){return isElementDisplayed(element, DEFAULT_TIMEOUT);}
    protected boolean isElementDisplayed(WebElement element, Duration timeout){
        try{
            getWait(timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e){
            return false;
        }
    }


    protected boolean isElementPresent(WebElement element){return  isElementPresent(element, DEFAULT_TIMEOUT);}
    protected boolean isElementPresent(WebElement element, Duration timeout){
        try{
            getWait(timeout).until(webDriver -> null != element.getText() || null != element.getAttribute("id") ||null != element.getTagName());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    protected void  select(WebElement element, String value){select(element, value, DEFAULT_TIMEOUT);}
    protected void  select(WebElement element, String value, Duration timeout){
        getWait(timeout).until(ExpectedConditions.elementToBeClickable(element));
        new Select(element).selectByValue(value);
    }

    protected void type(WebElement element, String text){type(element, text, DEFAULT_TIMEOUT);}
    protected void type(WebElement element, String text, Duration timeout){
        getWait(timeout).until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    protected void wait(Duration duration){
        try{
            getWait(duration).ignoring(NoSuchElementException.class).until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e){}
    }

    public void waitForAppearanceOfElement(By by){
        new FluentWait<>(lDriver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
