package utils.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.common.Helpers;
import utils.element.factory.ElementFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class DriverFactory {

  public static HashMap<String, DesiredCapabilities> capabilitiesHashmap = new HashMap<String, DesiredCapabilities>() {
    {
      put("SELENIUM_GRID", PredefineCap.SELENIUM_GRID);
      put("LOCAL", PredefineCap.LOCAL);
    }
  };

  private static WebDriver webDriver = null;
  private static int DEFAULT_TIMEOUT = 60;
  public static boolean isWebDriverRun = false;

  public static WebDriver getWebDriver() {
    return webDriver;
  }

  public static void prepareChromeDriver() {
    WebDriverManager.getInstance(CHROME).setup();
  }

  public static void createWebInstance() throws MalformedURLException {
    isWebDriverRun = true;
    if (webDriver != null) {
      return;
    }
    ChromeOptions options = new ChromeOptions();
    String location = System.getenv("env");
    switch (location.toUpperCase()) {
      case "LOCAL":
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        break;
      case "REMOTE":
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "firefox");
        webDriver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"),
            capabilities);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
        break;
    }

  }

  public static void dismissInstances() {
    if (webDriver != null) {
      webDriver.quit();
      webDriver = null;
    }
  }

  public static void initPages(String packageName, WebDriver driver) {
    try {
      Class[] pageClasses = Helpers.getClasses(packageName);
      for (Class pageClass : pageClasses) {
        ElementFactory.initElements(driver, pageClass);
      }
    } catch (Exception e) {
    }
  }

  public static void setImplicitWait(long seconds) {
    if (isWebDriverRun) {
      webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
  }

  public static void resetImplicitTimeout() {
    setImplicitWait(DEFAULT_TIMEOUT);
  }
}
