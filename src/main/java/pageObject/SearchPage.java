package pageObject;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import utils.element.Element;

public class SearchPage {

  @FindBy(id = "search_str")
  public static Element Search_Result_Input;

  @FindBy(css = "#forecast_list_ul .alert")
  public static Element Not_Found_Alert;

  @FindBy(css = "#forecast_list_ul tr")
  public static List<Element> Search_Result_Items;

  public static String getSearchResult() {
    String result = Search_Result_Input.getAttribute("value");
    return result;
  }

  public static String getAlertMessage() {
    String result = Not_Found_Alert.getText();
    return result;
  }

  public static Integer getResultItems() {
    Integer result = Search_Result_Items.size();
    return result;
  }
}
