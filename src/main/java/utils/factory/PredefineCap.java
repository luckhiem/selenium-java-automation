package utils.factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class PredefineCap {

  public static final DesiredCapabilities LOCAL = new DesiredCapabilities() {
    {
      setBrowserName("Chrome");
    }
  };

  public static final DesiredCapabilities SELENIUM_GRID = new DesiredCapabilities() {
    {
      setCapability("browser", "Firefox");
    }
  };
}
