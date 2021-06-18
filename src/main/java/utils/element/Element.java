package utils.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement {

  boolean isInitialized();

  void waitThenClick(long l, TimeUnit timeUnit);

  void waitThenClick(long timeOutInSec);

  void waitThenClick();

  void clickThenWait(long l, TimeUnit timeUnit);

  void clickThenWait(long timeOutInMillisecond);

  void clickAfter(long l, TimeUnit timeUnit);

  void clickAfter(long timeOutInMillisecond);

  WebElement waitClickable();

  WebDriver getWebDriver();

  void clickByJs();

  void hoverByJs();

  Element scrollIntoView();

  Element scrollToElement();

  Element moveToElement();

  boolean isFocused();

  Element findMyElement(By by);

  List<Element> findMyElements(By by);

  Element findChildWithText(String text);

  void waitForDisplayed();

  void waitForDisplayed(long timeOutInSeconds);

  void waitForEnabled();

  Element waitFor(long i);

  void waitForNotExist();

  void waitForNotExist(long timeOutInSecond);

  boolean isDisplayed();

  boolean isDisplayed(long timeOutInSecond);

  boolean isNotDisplayed(long timeOutInSecond);

  void retryClick(int times);

}
