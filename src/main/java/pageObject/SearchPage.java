package pageObject;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import utils.common.TextUtils;
import utils.element.Element;
import utils.element.elements.TextBox;

public class SearchPage {

  public final static Logger LOGGER = LogManager.getLogger(SearchPage.class);

  @FindBy(id = "search_str")
  public static TextBox Search_Result_Input;

  @FindBy(css = "#forecast_list_ul .alert")
  public static Element Not_Found_Alert;

  @FindBy(css = "#forecast_list_ul tr")
  public static List<Element> Search_Result_Items;

  public static String getSearchResult() {
    String result = Search_Result_Input.getInputtedText();
    LOGGER.info(String.format("Get Search Result: %s", result));
    return result;
  }

  public static String getAlertMessage() {
    String result = TextUtils.removeSpecialCharacters(Not_Found_Alert.getText());
    LOGGER.info(String.format("Get Alert Message: %s", result));
    return result;
  }

  public static Integer getResultItems() {
    Integer result = Search_Result_Items.size();
    LOGGER.info(String.format("Get Number of Search Items Returned: %s", result));
    return result;
  }
}
