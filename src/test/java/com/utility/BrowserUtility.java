package com.utility;

import com.constants.Browser;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
    }

    public BrowserUtility(Browser browserName,boolean isHeadless) {
        if (browserName == Browser.CHROME) {
            if(isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");   // use headless for CI
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");

                // optional: unique profile to avoid lock issues
                String tmpProfile = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + tmpProfile);
                driver.set(new ChromeDriver(options));
            }else{
                driver.set(new ChromeDriver());
            }
        }
        else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            } else {
                driver.set(new EdgeDriver());
            }
        }

        else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
            }else {
                driver.set(new FirefoxDriver());
            }
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator By locator of the element
     */
    public void clickOn(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException for locator: " + locator + " - Retrying...");
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            logger.error("Click failed for locator: " + locator + " - Trying JavaScript click", e);
            try {
                WebElement element = driver.get().findElement(locator);
                ((JavascriptExecutor) driver.get()).executeScript("arguments[0].click();", element);
            } catch (Exception jsEx) {
                logger.error("JavaScript click also failed for locator: " + locator,jsEx);
            }
        }
    }


    public void enterText(By locator, String textToBeEnter) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToBeEnter);
    }

    /**
     * Gets the visible text of the element located by the given locator.
     *
     * @param locator By locator of the element
     * @return Visible text of the element, or empty string if not found
     */
    public String getVisibleText(By locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText().trim();
            logger.info("Fetched text from element " + locator + " : " + text);
            return text;
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException for locator: " + locator + " - Retrying...");
            return getVisibleText(locator);
        } catch (Exception e) {
            logger.error("Error fetching text from locator: " + locator, e);
            return "";
        }
    }


    public String takeScreenshot(String name)  {
        TakesScreenshot screenshot= (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = simpleDateFormat.format(date);
        String path = "./Screenshot/" + name +" - " + timeStamp +".png";
        try {
            FileHandler.copy(screenshotData , new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void quit() {
        driver.get().quit();
    }

    public void enterSpecialKey(By locator, Keys keyToEnter) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(keyToEnter);
    }

    public List<String> getAllVisibleText(By locator){
        List<WebElement> elementList= driver.get().findElements(locator);
        List<String> visibleTextList = new ArrayList<String>();
        for (WebElement element : elementList) {
            waitUntilElementDisplay(element);
            System.out.println(getVisibleText(element));
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public String getVisibleText(WebElement element) {
        return element.getText();
    }

    public WebElement waitUntilElementDisplay(By locator) {
        return waitUntilElementDisplay(locator, 30); // default 30s timeout
    }

    public WebElement waitUntilElementDisplay(WebElement webElement){
        return waitUntilElementDisplay(webElement,30);
    }

    public WebElement waitUntilElementDisplay(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element located and visible: " + locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Element not visible after " + timeoutInSeconds + " seconds: " + locator, e);
            throw e;
        }
    }

    public WebElement waitUntilElementDisplay(WebElement webElement , int timeoutInSeconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutInSeconds));
            WebElement visibleElement =  wait.until(ExpectedConditions.visibilityOf(webElement));
            logger.info("Element located and visible: " + visibleElement);
            return visibleElement;
        } catch (Exception e) {
            logger.error("Element not visible after " + timeoutInSeconds + " seconds: " + webElement, e);
            throw new RuntimeException(e);
        }
    }

    public void setImplicitWait(int timeoutInSeconds) {
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
        logger.info("Implicit wait set to " + timeoutInSeconds + " seconds");
    }

    public void selectFormDropdown(By dropdownLocator, String optionToselect){
        logger.info("Finding element with the locator", dropdownLocator);
        WebElement element = driver.get().findElement(dropdownLocator);
        Select select = new Select(element);
        logger.info("selecting"+optionToselect+ "value form dropdown");
        select.selectByVisibleText(optionToselect);
    }

    /**
     * Checks whether an element is displayed on the page.
     *
     * @param locator By locator of the element
     * @return true if element is present and displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            List<WebElement> elements = getDriver().findElements(locator);
            if (!elements.isEmpty()) {
                return elements.get(0).isDisplayed();
            }
            return false;
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException for locator: " + locator, e);
            return false;
        } catch (Exception e) {
            logger.error("Error checking display status for locator: " + locator, e);
            return false;
        }
    }


    /**
     *  mouse hover actions
     */
    public void mouseHover(By locator) {
        WebElement element = getDriver().findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver.get());
        actions.moveToElement(element).perform();
    }

    public void mouseHoverAndClick(By locator) {
        WebElement element = getDriver().findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver.get());
        actions.moveToElement(element).click().perform();
    }


}
