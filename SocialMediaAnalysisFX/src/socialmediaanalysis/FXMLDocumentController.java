/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediaanalysis;

import datastructure.Edge_Imp;
import datastructure.Graph_Imp;
import datastructure.Node_Imp;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

/**
 *
 * @author ibrah
 */
public class FXMLDocumentController implements Initializable {

    private Graph_Imp graph;

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private AnchorPane graphics;

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

    public void init_draw(Graph_Imp G) {

    }

}
