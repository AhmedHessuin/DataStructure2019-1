/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediaanalysis;

import java.io.FileNotFoundException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;
import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author ibrah
 */
public class FXMLDocumentController implements Initializable {

    static Graph graph = new MultiGraph("I can see dead pixels");
    static Viewer viewer;
    ViewerPipe fromViewer;
    View view;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        viewer.close();
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        start_to_draw();
        set_stylesheet();
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Node E = graph.addNode("E");
        Node C = graph.addNode("C");
        Node D = graph.addNode("D");

        graph.addEdge("AB", "A", "B");
        graph.addEdge("BE", "B", "E");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("ED", "E", "D");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("AE", "A", "E");

        BetweennessCentrality bcb = new BetweennessCentrality();
        bcb.setWeightAttributeName("weight");
        bcb.setWeight(A, B, 1);
        bcb.setWeight(B, E, 6);
        bcb.setWeight(B, C, 5);
        bcb.setWeight(E, D, 2);
        bcb.setWeight(C, D, 3);
        bcb.setWeight(A, E, 4);
        bcb.init(graph);
        bcb.compute();

        System.out.println("A=" + A.getAttribute("Cb"));
        System.out.println("B=" + B.getAttribute("Cb"));
        System.out.println("C=" + C.getAttribute("Cb"));
        System.out.println("D=" + D.getAttribute("Cb"));
        System.out.println("E=" + E.getAttribute("Cb"));
        darw_node_edge_id_weight();

        // TODO
    }

    public void set_stylesheet() {
        //===================style sheet graph================================//

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "graph {  fill-color: black;}");

        //====================================================================//
        //=================style sheet node ==================================//
        graph.addAttribute("ui.stylesheet", "node { "
                + "text-size:17px;"
                + "size-mode: dyn-size;"
                + "fill-mode: dyn-plain;"
                + "size:26px;"
                + "fill-color: #CB00F3;"
                + "text-mode:normal;"
                + "text-alignment:center; "
                + "text-color:#f2f2f2;"
                + "shape:circle;"
                + " }");
        //====================================================================//
        //=====================style sheet edge ==============================//
        graph.addAttribute("ui.stylesheet", "edge { "
                + "shape:cubic-curve;"
                + "size:3px; "
                + "fill-color: #0867A0;"
                + "text-mode:normal;"
                + "text-size:17px;"
                + "text-color:gold;"
                + "text-alignment:along;"
                + "}");
        //====================================================================//
        //=============================style sheet marked=====================//
        graph.addAttribute("ui.stylesheet", " node.marked {fill-color: green;}");
        graph.addAttribute("ui.stylesheet", " edge.marked {fill-color:green;}");
        //====================================================================//

    }

    public void clicked_on_node(String id) {

        graph.getNode(id).setAttribute("ui.class", "marked");
        for (Edge edge : graph.getNode(id).getEachEdge()) {

            edge.setAttribute("ui.class", "marked");
            sleep(200);
        }

    }

    public void remove_clicked(String id) {

        graph.getNode(id).removeAttribute("ui.class");
        for (Edge edge : graph.getNode(id).getEachEdge()) {

            edge.removeAttribute("ui.class");
            sleep(0);
        }

    }

    public void start_to_draw() {
        //======================viewer========================================//
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        viewer = graph.display();
        view = viewer.getDefaultView();
        view.getCamera().setAutoFitView(true);
        viewer.enableAutoLayout();
    }

    public int get_degree(Node node) {

        return node.getDegree();
    }

    public void zoom_in() {
        view.getCamera().setViewPercent(view.getCamera().getViewPercent() - 0.1);

    }

    public void zoom_out() {
        view.getCamera().setViewPercent(view.getCamera().getViewPercent() + 0.1);
    }

    public void darw_node_edge_id_weight() {
        for (org.graphstream.graph.Node node : graph) {
            node.addAttribute("ui.label", node.getId());

        }
        for (Edge edge : graph.getEachEdge()) {
            edge.addAttribute("ui.label", 1);

        }
    }

    public void change_color(Node node) {
        node.setAttribute("ui.color", Color.RED);
    }

    public void change_size(Node node) {
        node.addAttribute("ui.size", 25 + node.getDegree() % 5);
    }

    protected void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (Exception e) {
        }
    }

}
