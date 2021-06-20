package utils.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PredefineCap {

  public static DesiredCapabilities getDesiredCapabilities() {
    String browser = System.getenv("browser");
    DesiredCapabilities result;
    switch (browser.toUpperCase()) {
      case "FIREFOX":
        result = FIREFOX;
        break;
      case "EDGE":
        result = EDGE;
        break;
      default:
        result = CHROME;
        break;
    }
    return result;
  }

  public static DesiredCapabilities CHROME = new DesiredCapabilities() {
    {
      setBrowserName(new ChromeOptions().getBrowserName());
    }
  };

  public static DesiredCapabilities FIREFOX = new DesiredCapabilities() {
    {
      setBrowserName(new FirefoxOptions().getBrowserName());
    }
  };

  public static DesiredCapabilities EDGE = new DesiredCapabilities() {
    {
      setBrowserName(new EdgeOptions().getBrowserName());
    }
  };
}
