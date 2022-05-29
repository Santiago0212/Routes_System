package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import structures.Tree;

public class FindController implements Initializable{

    @FXML
    private TextField destinationTF;

    @FXML
    private Button findButton;

    @FXML
    private TextArea minimumTA;

    @FXML
    private TextField originTF;

    @FXML
    private Button returnButton;

    @FXML
    void find(ActionEvent event) {
    	Main.routes.initDijkstra(Main.routes.search(originTF.getText().toUpperCase()));
    	Tree<String> routesTree = Main.routes.getArbolGeneradorMinimo();
    	ArrayList<String> route = routesTree.getRoute(destinationTF.getText().toUpperCase());
    	
    	String minimum = "";
    	for(String s : route) {
			minimum += s+"\n";
		}
    	
    	minimumTA.setText(minimum);
    	
    }
    
    @FXML
    void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Menu.fxml"));
		loader.setController(new MenuController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    	close();
    }
    
    public void close() {
    	Stage stage = (Stage) findButton.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		minimumTA.setText("");
		
	}

}
