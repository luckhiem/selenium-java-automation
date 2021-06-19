package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.env.Config;
import utils.factory.DriverFactory;
import utils.factory.DriverUtils;

public class BasePage {

  public final static Logger LOGGER = LogManager.getLogger(BasePage.class);

  public static void gotoOpenWeatherMapPage() {
    DriverFactory.getWebDriver().get(Config.ENV.TEST_URL());
    DriverUtils.waitForPageLoadComplete();
    LOGGER.info("Go to the Open Weather Map page");
  }
}
