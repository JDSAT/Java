package com.jdsat.jdsatfinance.view;

import javafx.scene.Node;

public class AbstractController implements Controller {
    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView(Node view) {
        this.view=view;
    }
}
