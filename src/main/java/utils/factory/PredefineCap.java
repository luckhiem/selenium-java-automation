package utils.factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class PredefineCap {

  private static String switchBrowser() {
    String browser = System.getenv("browser");
    String result;
    switch (browser.toUpperCase()) {
      case "FIREFOX":
        result = "firefox";
        break;
      case "EDGE":
        result = "edge";
        break;
      default:
        result = "chrome";
        break;
    }
    return result;
  }

  public static DesiredCapabilities REMOTE = new DesiredCapabilities() {
    {
      setBrowserName(switchBrowser());
    }
  };
}
