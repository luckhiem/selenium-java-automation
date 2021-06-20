package utils.factory;

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
      setBrowserName("chrome");
    }
  };

  public static DesiredCapabilities FIREFOX = new DesiredCapabilities() {
    {
      setBrowserName("firefox");
    }
  };

  public static DesiredCapabilities EDGE = new DesiredCapabilities() {
    {
      setBrowserName("edge");
    }
  };
}
