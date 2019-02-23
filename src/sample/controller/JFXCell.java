package sample.controller;

import javafx.event.EventTarget;

public interface JFXCell extends EventTarget {
    void updateItem(Boolean item, boolean empty);
}
