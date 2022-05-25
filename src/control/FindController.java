package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Main;

public class FindController {

    @FXML
    private Button findButton;

    @FXML
    private TextField findText;

    @FXML
    void find(ActionEvent event) {
    	Main.routes.initDijktra(Main.routes.search(findText.getText()));

		System.out.println("------------DISTANCIAS MINIMAS DESDE EL VERTICE ELEGIDO------------------------");
		Main.routes.printDistancias();
		System.out.println("-------------------------------");
    }

}
