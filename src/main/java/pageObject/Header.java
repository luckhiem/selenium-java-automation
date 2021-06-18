package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import utils.element.Element;

public class Header extends BasePage {

  public final static Logger LOGGER = LogManager.getLogger(Header.class);

  @FindBy(name = "q")
  public static Element Search_City_Input;

  public static void inputSearchKeyword(String keyword) {
    LOGGER.info(String.format("Input search keyword: %s", keyword));
    Search_City_Input.sendKeys(keyword);
  }

  public static void submitSearchForm() {
    LOGGER.info("Submit the search form");
    Search_City_Input.sendKeys(Keys.ENTER);
  }
}
