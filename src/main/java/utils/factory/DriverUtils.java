package utils.factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {

  public static void waitForPageLoadComplete() {
    WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 60000);
    wait.until(webDriver -> ((JavascriptExecutor) DriverFactory.getWebDriver())
        .executeScript("return document.readyState").equals("complete"));
  }
}
