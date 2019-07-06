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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt")
        );
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
        );
        File file = fileChooser.showOpenDialog(button.getScene().getWindow());
        if (file != null) {
            Scanner scanner = new Scanner(file);
            int no_nodes = 0;
            int no_vertics = 0;

            if (scanner.hasNextLine()) {
                Scanner line = new Scanner(scanner.nextLine());

                no_nodes = line.nextInt();
                no_vertics = line.nextInt();
            }

            graph = new Graph_Imp(no_nodes);

            for (int k = 0; k < no_vertics; k++) {
                Scanner line = new Scanner(scanner.nextLine());

                int src = line.nextInt();
                int dst = line.nextInt();
                double wt = line.nextDouble();
                Edge_Imp edg;

                edg = new Edge_Imp(graph.getNode(src), wt);
                graph.getNode(dst).addChild(edg);
                edg = new Edge_Imp(graph.getNode(dst), wt);
                graph.getNode(src).addChild(edg);

            }
            scanner.close();
        }

        init_draw(graph);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

    public void init_draw(Graph_Imp G) {
                int spacing = 60;
        float thetaSector = (float) (2.0 * Math.PI) / G.getNoVertices();

        float radius = (float) ((spacing + 100.0) / (2.0 * Math.sin(thetaSector / 2)));

        graphics.setPrefWidth((2 * radius) + radius);
        graphics.setPrefHeight((2 * radius) + radius);

        float windowXcenter = radius;
        float windowYcenter = radius;

        float X = windowXcenter + (float) (radius);
        float Y = windowYcenter;

        float theta = 0;
        for (Node_Imp N : G.getVertices()) {

            N.x = X;
            N.y = Y;

            theta += thetaSector;
            //newX = radius * Math.cos(theta) ;
            //newY = radius * Math.sin(theta) ;

            X = (float) (windowXcenter + radius * Math.cos(theta));
            Y = (float) (windowYcenter + radius * Math.sin(theta));
            //X += radius * Math.cos(theta) ;
            //Y += radius * Math.sin(theta) ;

        }

        float maxCentrality = -1;
        for (Node_Imp N : G.getVertices()) {

            if (N.getCentrality() > maxCentrality) {
                maxCentrality = (float) N.getCentrality();
            }
        }

        maxCentrality = Math.abs(maxCentrality);
        for (Node_Imp N : G.getVertices()) {

            Rectangle rectangle = new Rectangle(N.x, N.y, 100 / maxCentrality, 100 / maxCentrality);

            graphics.getChildren().add(rectangle);

        }

        for (Node_Imp N : G.getVertices()) {

            for (Edge_Imp E : N.getChildren()) {

                Line l = new Line(N.x, N.y, E.getChild().x, E.getChild().y);

                graphics.getChildren().add(l);
            }

        }
    }

}
