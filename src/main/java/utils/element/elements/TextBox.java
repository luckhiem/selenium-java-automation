package utils.element.elements;

import utils.element.Element;
import utils.element.ImplementedBy;

@ImplementedBy(TextBoxImpl.class)
public interface TextBox extends Element {
    String getInputtedText();

    void copyTextToClipboard();

    void pasteTextFromClipboard();

    void clearAndSendKey(String text);

    void clearAndSendDelayedKey(String text);

    void manualClearAndSendKey(String text);

}
