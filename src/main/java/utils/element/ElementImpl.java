package utils.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElementImpl implements Element {

  private final WebElement element;
  private WebDriver webDriver;
  private int DEFAULT_TIMEOUT = 60;
  private final static Logger LOGGER = LogManager.getLogger("Element");

  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  public ElementImpl(final WebElement element) {
    this.element = element;
    webDriver = getWebDriver();
  }

  @Override
  public boolean isInitialized() {
    return false;
  }

  @Override
  public void waitThenClick(long timeOut, TimeUnit timeUnit) {
    timeOut = TimeUnit.SECONDS.convert(timeOut, timeUnit);
    waitThenClick(timeOut);
  }

  @Override
  public void waitThenClick(long timeOutInSec) {
    WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
    wait.until(ExpectedConditions.visibilityOf(element));
    WebElement result = wait.until(ExpectedConditions.elementToBeClickable(element));
    result.click();
  }

  @Override
  public void waitThenClick() {
    waitThenClick(DEFAULT_TIMEOUT);
  }

  @Override
  public void waitForDisplayed() {
    waitForDisplayed(DEFAULT_TIMEOUT);
  }

  @Override
  public void waitForDisplayed(long timeOutInSecond) {
    WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSecond);
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  @Override
  public void waitForNotExist() {
    //TODO
  }

  @Override
  public void waitForNotExist(long timeOutInSecond) {
    //TODO
  }

  @Override
  public void clickThenWait(long l, TimeUnit timeUnit) {
    element.click();
    l = TimeUnit.MILLISECONDS.convert(l, timeUnit);
    try {
      Thread.sleep(l);
    } catch (InterruptedException e) {
      //TODO
    }
  }

  @Override
  public void clickThenWait(long timeOutInMillisecond) {
    element.click();
    try {
      Thread.sleep(timeOutInMillisecond);
    } catch (InterruptedException e) {
      //TODO
    }
  }

  @Override
  public void clickAfter(long l, TimeUnit timeUnit) {
    //TODO
  }

  @Override
  public void clickAfter(long timeOutInMillisecond) {
    //TODO
  }

  @Override
  public WebElement waitClickable() {
    WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  @Override
  public WebDriver getWebDriver() {
    Field f = null;
    WebDriver webDriver = null;
    try {
      f = element.getClass().getDeclaredField("parent");
    } catch (NoSuchFieldException e) {
    }
    try {
      f.setAccessible(true);
      Object o = f.get(element);
      if (o instanceof WebDriver) {
        webDriver = (WebDriver) o;
      }
    } catch (IllegalAccessException e) {
    }
    return webDriver;
  }

  @Override
  public void clickByJs() {
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
  }

  @Override
  public void hoverByJs() {
    ((JavascriptExecutor) webDriver).executeScript(
        "var event = document.createEvent('HTMLEvents');event.initEvent('mouseover', true, true);arguments[0].dispatchEvent(event);",
        element);
  }

  @Override
  public Element scrollIntoView() {
    return null;
  }

  @Override
  public Element scrollToElement() {
    return null;
  }

  @Override
  public Element moveToElement() {
    return null;
  }

  @Override
  public boolean isFocused() {
    return false;
  }

  @Override
  public Element findMyElement(By by) {
    return null;
  }

  @Override
  public List<Element> findMyElements(By by) {
    return null;
  }

  @Override
  public Element findChildWithText(String text) {
    return null;
  }

  @Override
  public void waitForEnabled() {
  }

  @Override
  public Element waitFor(long i) {
    return null;
  }

  public void click() {
    element.click();
    String elementInfo = element.getText().replace("\n", " | ");
    LOGGER.info(String.format("Clicking element: %s", elementInfo));
  }

  public void submit() {
    element.submit();
    //TODO
  }

  public void sendKeys(CharSequence... charSequences) {
    element.sendKeys(charSequences);
    //TODO
  }

  public void clear() {
    element.clear();
    //TODO
  }

  public String getTagName() {
    //TODO
    return element.getTagName();
  }

  public String getAttribute(String s) {
    return element.getAttribute(s);
    //TODO
  }

  public boolean isSelected() {
    return element.isSelected();
    //TODO
  }

  public boolean isEnabled() {
    return element.isEnabled();
    //TODO
  }

  public String getText() {
    WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
    wait.until(ExpectedConditions.visibilityOf(element));
    return element.getText();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return null;
  }

  @Override
  public WebElement findElement(By by) {
    return null;
  }

  @Override
  public boolean isDisplayed() {
    return element.isDisplayed();
    //TODO
  }

  @Override
  public Point getLocation() {
    return element.getLocation();
    //TODO
  }

  @Override
  public Dimension getSize() {
    return element.getSize();
    //TODO
  }

  @Override
  public Rectangle getRect() {
    return element.getRect();
    //TODO
  }

  @Override
  public String getCssValue(String s) {
    return element.getCssValue(s);
    //TODO
  }

  @Override
  public boolean isDisplayed(long timeOutInSecond) {
    return element.isDisplayed();
    //TODO
  }

  @Override
  public boolean isNotDisplayed(long timeOutInSecond) {
    return element.isDisplayed();
    //TODO
  }

  @Override
  public void retryClick(int times) {

  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return element.getScreenshotAs(outputType);
    //TODO
  }

  @Override
  public WebElement getWrappedElement() {
    return element;
    //TODO
  }
}

