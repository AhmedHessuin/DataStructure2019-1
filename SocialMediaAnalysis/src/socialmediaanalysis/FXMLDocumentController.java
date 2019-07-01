package socialmediaanalysis;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

public class FXMLDocumentController implements Initializable {

    //===============static variable area ====================================//
    public static int last_id;
    public static String mode = "add_node";
    public static boolean algroerth_on = false;
    public static boolean request_change=false;
    boolean view_weight = false;
    public static String selected_edge;
    public Viewer viewer2;
    //========================================================================//

    //==============================graph variable============================//
    public static Graph graph = new MultiGraph("I can see dead pixels");//graph
    //============================//
    public static Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
    //============================//

    JFrame frame;
    //============================//
    public static ViewerPipe fromViewer;
    public static View view;
    //============================//
    //========================================================================//

    //=====================gui variable section===============================//
    private FXMLDocumentController fXMLDocumentController;
    private Label label;
    @FXML
    private javafx.scene.control.Button button;
    @FXML
    private Label noxus_rise;
    @FXML
    public ComboBox<?> LISTBOX;

    @FXML
    public javafx.scene.control.TextField old_weight_text;
    @FXML
    private javafx.scene.control.TextField new_weight_text;
    @FXML
    private javafx.scene.control.Button set_button;
    @FXML
    private javafx.scene.control.Button btnOpenNewWindow;

    //========================================================================//
    //====================useless functions section===========================//
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //viewer.close();
        //zoom_in();
        frame.dispose();
        label.setText("Hello World!");
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    protected void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void star_gent() {
        Graph graph3 = new SingleGraph("Random");
        Generator gen = new BarabasiAlbertGenerator(3);
        gen.addSink(graph3);
        gen.begin();
        for (int i = 0; i < 10; i++) {
            gen.nextEvents();
        }
        gen.end();
        graph3.display();
    }
    //========================================================================//

    //========================int=============================================//
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        add_check_list_element();
        start_to_draw();
        set_stylesheet();

        //  star_gent();
        for (int i = 0; i < 1; i++) {
            String x = Integer.toString(i);
            graph.addNode(x);
            last_id = i;
            // graph.getNode(x).addAttribute("ui.stylesheet", "node { size:" +Integer.toString(i)+"px;}");
            // graph.getNode(x).setAttribute(x, values);

        }
        for (int i = 0; i < 0; i++) {
            for (int j = i; j < 0; j++) {
                if (j == i) {
                    continue;
                } else {
                    if (i == 0 && j == 1) {
                        continue;
                    }
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

    //=======================style sheet======================================//
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

    public static void color_generator(int input, Node node) {
        int reminder;
        int size;
        if (input > 0 && input < 10) {
            reminder = input % 10;// 1 2 3 4 5 6 7 8 9
            size = reminder * 2 + 20;

            node.changeAttribute("ui.color", Color.decode("#ffff00"));
            node.changeAttribute("ui.size", size);
            //#ffff00
        } else if ((input >= 10 && input < 20)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;

            node.changeAttribute("ui.color", Color.decode("#ffae42"));
            node.changeAttribute("ui.size", size);
            //#ffae42 

        } else if ((input >= 20 && input < 30)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#FF7200"));
            node.changeAttribute("ui.size", size);
            //#FFA500

        } else if ((input >= 30 && input < 40)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#ff4500"));
            node.changeAttribute("ui.size", size);
            //#ff4500 

        } else if ((input >= 40 && input < 50)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#ff0000"));
            node.changeAttribute("ui.size", size);
            //#ff0000  

        } else if ((input >= 50 && input < 60)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#c71585"));
            node.changeAttribute("ui.size", size);
            //#c71585   

        } else if ((input >= 60 && input < 70)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#800080"));
            node.changeAttribute("ui.size", size);
            //#800080   

        } else if ((input >= 70 && input < 80)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#8a2be2"));
            node.changeAttribute("ui.size", size);
            //#8a2be2    

        } else if ((input >= 80 && input < 90)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#0000ff"));
            node.changeAttribute("ui.size", size);
            //#0000ff     

        } else if ((input >= 90 && input < 100)) {
            reminder = input % 10;// 0 1 2 3 4 5 6 7 8 9 
            size = reminder * 2 + 20;
            node.changeAttribute("ui.color", Color.decode("#0d98ba"));
            node.changeAttribute("ui.size", size);
            //#0d98ba      

        } else {

            //error
        }

    }

    //===================clicked file functions===============================//
    // note not used but the important 
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
    //========================================================================//

    //======================start the gui=====================================//
    public void start_to_draw() {
        //======================viewer========================================//
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");//gs.ui.renderer

        frame = new JFrame();
        frame.setResizable(true);
        //=====================modify===================================//
        //tester

        //===============================================================//
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };

        //==========modify=================================//
        panel.addMouseListener(new MouseAdapter() {
            public void buttonclicked(MouseEvent e) {
                double x = e.getX();
                double y = e.getY();
                System.out.println("monster");

            }
        });

        //modify=======================//
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

    public void darw_node_edge_id_weight() {
        for (org.graphstream.graph.Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        for (Edge edge : graph.getEachEdge()) {
            edge.addAttribute("ui.label", 1);
        }
    }

    public void add_check_list_element() {
        ObservableList list = FXCollections.observableArrayList();
        String a = "Add Edge";
        String b = "Add Node";
        String c = "Node Edges";
        String v = "Remove Node";
        String d = "Remove Edge";
        String e = "Free Move";
        String f = "Change Weight";
        list.removeAll(list);
        list.addAll(b, a, v, d, c, e, f);
        LISTBOX.getItems().addAll(list);

    }

    //====================camera section======================================//
    public void zoom_in() {
        view.getCamera().setViewPercent(view.getCamera().getViewPercent() - 0.1);
    }

    public void zoom_out() {
        view.getCamera().setViewPercent(view.getCamera().getViewPercent() + 0.1);
    }

    //======================idk===============================================//
    /**
     * @param fXMLDocumentController the fXMLDocumentController to set
     */
    public void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController) {
        this.fXMLDocumentController = fXMLDocumentController;

    }

    //====================gui function section================================//
    // camera section 
    @FXML
    private void close_view(MouseEvent event) {
        frame.dispose();
        viewer.close();
    }

    @FXML
    private void reset_Zoom(MouseEvent event) {
        view.getCamera().setViewPercent(1);

        set_stylesheet();
        view.getCamera().resetView();

        for (org.graphstream.graph.Node node : graph) {
            node.addAttribute("ui.size", 24);

            node.removeAttribute("ui.color");
        }

        algroerth_on = false;
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
        request_change=true;
        viewer.enableAutoLayout();

        request_change=false;
    }

    @FXML
    private void twoD(MouseEvent event) {
        //viewer2 = viewer;
        viewer.disableAutoLayout();
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

    //===============================//
    //change the size depending on the algorethm seciton 
    @FXML
    private void Draw_on_Betweenness(MouseEvent event) {

    }

    @FXML
    private void Draw_on_Degree(MouseEvent event) {
        for (org.graphstream.graph.Node node : graph) {

            color_generator(node.getDegree(), node);

        }

        for (Edge edge : graph.getEachEdge()) {
            edge.removeAttribute("ui.class");
            sleep(0);
        }
        algroerth_on = true;
    }

    @FXML
    private void Draw_on_Closeness(MouseEvent event) {

    }

    //===============================//
    @FXML
    private void noxus(MouseEvent event) {

        noxus_rise.setVisible(true);
    }

    //check list
    private void check_list_relase(MouseEvent event) {
        mode = (String) LISTBOX.getValue();
        System.out.println(mode);
    }

    @FXML
    private void Change_weight(MouseEvent event) {
        //graph.getEdge(selected_edge).setAttribute("ui.label", 10);;
        new_weight_text.setText("222");
        old_weight_text.setText("jbjb");
        System.out.println("ssd");
        //old_weight_text.setText("123");
    }

    private void Change_weight(ContextMenuEvent event) {
        //graph.getEdge(selected_edge).setAttribute("ui.label", 10);;

        new_weight_text.setText("s");
        old_weight_text.setText("jbjb");

        //old_weight_text.setText("123");
    }

    private void Change_weight2(InputMethodEvent event) {
        new_weight_text.setText("222");
        old_weight_text.setText("jbjb");
        System.out.println("ssd");
        //old_weight_text.setText("123");
    }

    @FXML
    public void LISTBOXChanged(ActionEvent event) {

        mode = (String) LISTBOX.getValue();
        System.out.println(mode);
    }

}
