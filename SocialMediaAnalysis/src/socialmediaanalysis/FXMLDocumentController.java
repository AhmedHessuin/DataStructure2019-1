package socialmediaanalysis;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

public class FXMLDocumentController implements Initializable {

    public static Graph graph = new MultiGraph("I can see dead pixels");
    public static Viewer viewer;
    boolean view_weight = false;
    JFrame frame;
    public static ViewerPipe fromViewer;
    View view;
    private FXMLDocumentController fXMLDocumentController;
    private Label label;
    @FXML
    private javafx.scene.control.Button button;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //viewer.close();
        //zoom_in();
        frame.dispose();
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        start_to_draw();
        set_stylesheet();

        for (int i = 0; i < 20; i++) {
            String x = Integer.toString(i);
            graph.addNode(x);
            // graph.getNode(x).addAttribute("ui.stylesheet", "node { size:" +Integer.toString(i)+"px;}");
            // graph.getNode(x).setAttribute(x, values);

        }
        for (int i = 0; i < 1; i++) {
            for (int j = i; j < 20; j++) {
                if (j == i) {
                    continue;
                } else {
                    // if (i == 0 && j == 1) {
                    //   continue;
                    //}
                    graph.addEdge(Integer.toString(i) + Integer.toString(j), Integer.toString(i), Integer.toString(j));
                }
            }
            //System.out.println(graph.getNode(i).getDegree());
        }

        darw_node_edge_id_weight();

        Clicks ct = new Clicks(viewer, graph, fromViewer, fXMLDocumentController);
        ct.start();
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
                + "size:24px;"
                + "fill-color: #CB00F3;"
                + "text-mode:normal;"
                + "text-alignment:center; "
                + "text-color:#4C3C57;"
                + "shape:circle;"
                + " }");
        //====================================================================//
        //=====================style sheet edge ==============================//
        graph.addAttribute("ui.stylesheet", "edge { "
                + "shape:cubic-curve;"
                + "size:5px; "
                + "fill-color: #0867A0;"
                + "text-mode:normal;"
                + "text-visibility-mode:hidden;"
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

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public void color_generator(int input, Node node) {
        int reminder;
        int size;
        if (input > 0 && input < 10) {
            reminder = input % 10;// 1 2 3 4 5 6 7 8 9
            size = reminder * 2 + 20;

            node.setAttribute("ui.color", Color.decode("#ffff00"));
            node.addAttribute("ui.size", size);
            //#ffff00
        } else if ((input >= 10 && input < 20)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;

            node.setAttribute("ui.color", Color.decode("#ffae42"));
            node.addAttribute("ui.size", size);
            //#ffae42 

        } else if ((input >= 20 && input < 30)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#FF7200"));
            node.addAttribute("ui.size", size);
            //#FFA500

        } else if ((input >= 30 && input < 40)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#ff4500"));
            node.addAttribute("ui.size", size);
            //#ff4500 

        } else if ((input >= 40 && input < 50)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#ff0000"));
            node.addAttribute("ui.size", size);
            //#ff0000  

        } else if ((input >= 50 && input < 60)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#c71585"));
            node.addAttribute("ui.size", size);
            //#c71585   

        } else if ((input >= 60 && input < 70)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#800080"));
            node.addAttribute("ui.size", size);
            //#800080   

        } else if ((input >= 70 && input < 80)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#8a2be2"));
            node.addAttribute("ui.size", size);
            //#8a2be2    

        } else if ((input >= 80 && input < 90)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#0000ff"));
            node.addAttribute("ui.size", size);
            //#0000ff     

        } else if ((input >= 90 && input < 100)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.setAttribute("ui.color", Color.decode("#0d98ba"));
            node.addAttribute("ui.size", size);
            //#0d98ba      

        } else {

            //error
        }

    }

    public void clicked_on_node(Graph _graph, String id) {
        _graph.getNode(id).setAttribute("ui.class", "marked");
        for (Edge edge : _graph.getNode(id).getEachEdge()) {
            edge.setAttribute("ui.class", "marked");
            sleep(200);
        }

    }

    public void remove_clicked(Graph _graph, String id) {
        _graph.getNode(id).removeAttribute("ui.class");
        for (Edge edge : _graph.getNode(id).getEachEdge()) {
            edge.removeAttribute("ui.class");
            sleep(0);
        }
    }

    public void start_to_draw() {
        //======================viewer========================================//
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        frame = new JFrame();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };

        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        ViewPanel viewPanel = viewer.addDefaultView(false);
        panel.add(viewPanel);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        view = viewer.getDefaultView();
        view.getCamera().setAutoFitView(true);
        viewer.enableAutoLayout();
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
        node.addAttribute("ui.size", 26);
    }

    protected void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close_view(MouseEvent event) {
        frame.dispose();
        viewer.close();
    }

    @FXML
    private void reset_Zoom(MouseEvent event) {
        view.getCamera().setViewPercent(1);
        for (org.graphstream.graph.Node node : graph) {
            view.getCamera().resetView();

            node.addAttribute("ui.size", 24);
            node.setAttribute("ui.color", Color.decode("#CB00F3"));

        }
    }

    @FXML
    private void zoom_IN(MouseEvent event) {
        zoom_in();

    }

    @FXML
    private void Zoom_OUT(MouseEvent event) {
        zoom_out();
    }

    @FXML
    private void threeD(MouseEvent event) {
        viewer.enableAutoLayout();
    }

    @FXML
    private void twoD(MouseEvent event) {
        viewer.disableAutoLayout();
    }

    /**
     * @param fXMLDocumentController the fXMLDocumentController to set
     */
    public void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController) {
        this.fXMLDocumentController = fXMLDocumentController;

    }

    @FXML
    private void Draw_on_Betweenness(MouseEvent event) {

    }

    @FXML
    private void Draw_on_Degree(MouseEvent event) {
        for (org.graphstream.graph.Node node : graph) {

            color_generator(node.getDegree(), node);

        }
    }

    @FXML
    private void Draw_on_Closeness(MouseEvent event) {

    }

    @FXML
    private void VIEW_EDGE_WEIGHT(MouseEvent event) {
        graph.addAttribute("ui.screenshot", ".\\..\\GraphStream.png");
       
        if (!view_weight) {
            graph.setAttribute("ui.stylesheet", "edge { "
                    + "text-visibility-mode:normal;"
                    + "}");
            view_weight = true;
        } else {
            graph.setAttribute("ui.stylesheet", "edge { "
                    + "text-visibility-mode:hidden;"
                    + "}");
            view_weight = false;
        }

    }

    

}
