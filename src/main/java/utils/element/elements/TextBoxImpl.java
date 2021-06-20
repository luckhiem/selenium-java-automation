package utils.element.elements;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.element.ElementImpl;

public class TextBoxImpl extends ElementImpl implements TextBox {
    private WebElement element = getWrappedElement();
    private final static Logger LOGGER = LogManager.getLogger("TextBox");

    public TextBoxImpl(WebElement element) {
        super(element);
    }

    private WebElement getInputElement(){
        WebElement exactElement = getWrappedElement();
        if(!(exactElement.getTagName().equals("input")|| exactElement.getTagName().equals("textarea"))){
            LOGGER.info("The selector is not point to Input Element, temporary switch to the suggested one");
            exactElement = getWrappedElement().findElement(By.tagName("input"));
        }
        return exactElement;
    }

    @Override
    public String getInputtedText() {
         return getInputElement().getAttribute("value");
    }

    @Override
    public void copyTextToClipboard() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getInputtedText()), null);
    }

    @Override
    public void pasteTextFromClipboard() {
        String clipboardText = "";
        try {
            clipboardText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        clearAndSendKey(clipboardText);
    }

    @Override
    public void clearAndSendKey(String text) {
        this.waitForDisplayed();
        WebElement exactElement = getInputElement();
        LOGGER.info("Clearing text");
        exactElement.clear();
        LOGGER.info(String.format("Typing: %s", text));
        exactElement.sendKeys(text);
    }

    @Override
    public void clearAndSendDelayedKey(String text) {
        this.waitForDisplayed();
        WebElement exactElement = getInputElement();
        LOGGER.info("Clearing text");
        exactElement.clear();
        LOGGER.info(String.format("Typing: %s", text));
    }

    @Override
    public void manualClearAndSendKey(String text) {
        this.waitForDisplayed();
        WebElement exactElement = getInputElement();
        LOGGER.info("Clearing text by sending BackSpace");
        IntStream.rangeClosed(0,getInputtedText().length()).forEach(i -> exactElement.sendKeys(Keys.BACK_SPACE));
        LOGGER.info(String.format("Typing: %s", text));
        exactElement.sendKeys(text);
    }
}
