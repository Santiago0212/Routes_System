package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;

public class MenuController {

    @FXML
    private Button addButton;

    @FXML
    private Button showButton;

    @FXML
    private Button findButton;

    @FXML
    void add(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Start.fxml"));
		loader.setController(new StartController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		close();
    }

    @FXML
    void find(ActionEvent event) {

    }

    @FXML
    void show(ActionEvent event) {

    }
    
    public void close() {
    	Stage stage = (Stage) addButton.getScene().getWindow();
    	stage.close();
    }
}
