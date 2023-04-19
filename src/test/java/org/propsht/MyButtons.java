package org.propsht;

enum MyButtons {
    ALERT("Click for JS Alert"),
    CONFIRM("Click for JS Confirm"),
    PROMPT("Click for JS Prompt");

    private String textOnButton;

    MyButtons(String textOnButton) {
        this.textOnButton = textOnButton;
    }

    public String getTextOnButton() {
        return textOnButton;
    }
}
