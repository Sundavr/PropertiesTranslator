package myjavafx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SearchField extends StackPane {
    private final TextField textField;
    private final Button searchButton;
    private final Label searchResults;

    public SearchField() {
        this.textField = new TextField();
        this.textField.setPromptText("Search Text");
        this.searchButton = new Button("Search");
        this.searchResults = new Label();
        VBox layout = new VBox(20, new HBox(10, this.textField, this.searchButton), this.searchResults);
        layout.setPadding(new Insets(10));
        this.searchButton.setOnAction(event -> this.searchResults.setText("Search result for " + this.textField.getText()));
        getChildren().setAll(layout);
    }
}