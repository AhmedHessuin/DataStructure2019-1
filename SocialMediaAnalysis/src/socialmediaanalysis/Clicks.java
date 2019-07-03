package socialmediaanalysis;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.graph.Edge;

import static socialmediaanalysis.MainPlatform.graph;
import static socialmediaanalysis.MainPlatform.mode;
import static socialmediaanalysis.MainPlatform.viewer;
import static socialmediaanalysis.MainPlatform.request_change;
import static socialmediaanalysis.MainPlatform.algorithm_on;
import static socialmediaanalysis.MainPlatform.color_generator;
import static socialmediaanalysis.MainPlatform.jTextField1;
import static socialmediaanalysis.MainPlatform.last_id;
import static socialmediaanalysis.MainPlatform.selected_edge;

public class Clicks extends Thread implements ViewerListener {

    private ViewerPipe fromViewer;

    private boolean loop = true;

    private String mark_id_new;
    private Edge old_edge;
    private String mark_id_old;
    private String edge_node_first = null;
    private String edge_node_second = null;
    private boolean edge_connect_first_time = true;
    private boolean first_time = true;

    public Clicks() {
        fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);
    }

    @Override
    public void run() {
        while (loop) {
            if (request_change) {
                fromViewer.clearSinks();
            }
            if (mode == "close") {
                loop = false;
            }
            fromViewer.pump();
        }
    }

    @Override
    public void viewClosed(String id) {
    }

    @Override
    public void buttonPushed(String id) {
        
    
        
        
        if (mode == "Node Edges") {
            mark_id_new = id;
            if (mark_id_new == mark_id_old) {
                if (graph.getNode(id).getAttribute("ui.class") == "marked") {
                    graph.getNode(id).removeAttribute("ui.class");
                    if (algorithm_on) {
                        color_generator(graph.getNode(id).getDegree(), graph.getNode(id));
                    }
                    for (Edge edge : graph.getNode(id).getEachEdge()) {
                        edge.removeAttribute("ui.class");
                        sleep(0);
                    }
                } else {
                    System.out.println("Button set  node " + id);
                    graph.getNode(id).setAttribute("ui.class", "marked");
                    if (algorithm_on) {
                        graph.getNode(id).setAttribute("ui.color", Color.decode("#00ff00"));
                    }
                    for (Edge edge : graph.getNode(id).getEachEdge()) {
                        edge.setAttribute("ui.class", "marked");
                        sleep(200);
                    }
                }
            } else {
                if (!first_time) {
                    graph.getNode(mark_id_old).removeAttribute("ui.class");
                    if (algorithm_on) {
                        color_generator(graph.getNode(mark_id_old).getDegree(), graph.getNode(mark_id_old));

                    }
                    for (Edge edge : graph.getNode(mark_id_old).getEachEdge()) {
                        edge.removeAttribute("ui.class");
                        sleep(0);
                    }
                }
                first_time = false;
                mark_id_old = mark_id_new;
            }
        }//first if 
        else if (mode == "Add Node") {
            if (algorithm_on) {

            } else {
                String x = Integer.toString(last_id + 1);
                last_id++;
                graph.addNode(x);
                graph.getNode(x).addAttribute("ui.label", graph.getNode(x).getId());
            }

        }//esle if node add
        else if (mode == "Add Edge") {
            if (algorithm_on) {

            } else {
                if (edge_connect_first_time) {
                    edge_node_first = id;
                    graph.getNode(id).setAttribute("ui.class", "marked");//marke the first node
                    edge_connect_first_time = false;
                } else {
                    edge_node_second = id;
                    boolean i_can = true;
                    //============//

                    for (Edge edge : graph.getEachEdge()) {
                        if (edge.getNode0() == graph.getNode(edge_node_second) && edge.getNode1() == graph.getNode(edge_node_first)
                                || edge.getNode0() == graph.getNode(edge_node_first) && edge.getNode1() == graph.getNode(edge_node_second)) {
                            i_can = false;
                            break;
                        }
                    }

                    //============//
                    if (i_can) {
                        graph.addEdge(edge_node_first + edge_node_second, edge_node_first, edge_node_second);
                        graph.getEdge(edge_node_first + edge_node_second).addAttribute("ui.label", 1);
                        edge_connect_first_time = true;
                    }

                    graph.getNode(edge_node_first).removeAttribute("ui.class");
                    edge_connect_first_time = true;
                }
            }

        }//else if edge add
        else if (mode == "Remove Node") {
            if (algorithm_on) {

            } else {
                if (graph.getNodeCount() == 1) {

                } else {

                    for (Edge edge : graph.getEachEdge()) {
                        if (edge.getNode0() == graph.getNode(id) || edge.getNode1() == graph.getNode(id)) {

                            graph.removeEdge(edge);
                        }
                    }
                    graph.removeNode(id);
                }
            }
        }//else if remove node
        else if (mode == "Remove Edge") {
            if (algorithm_on) {

            } else {
                if (edge_connect_first_time) {
                    edge_node_first = id;
                    graph.getNode(id).setAttribute("ui.class", "marked");//mark before remove
                    edge_connect_first_time = false;
                } else {
                    edge_node_second = id;

                    for (Edge edge : graph.getEachEdge()) {
                        if (edge.getNode0() == graph.getNode(edge_node_second) && edge.getNode1() == graph.getNode(edge_node_first)
                                || edge.getNode0() == graph.getNode(edge_node_first) && edge.getNode1() == graph.getNode(edge_node_second)) {
                            graph.removeEdge(edge);

                            break;
                        }
                    }
                    graph.getNode(edge_node_first).removeAttribute("ui.class");
                    edge_connect_first_time = true;

                }
            }
        }//else if remove edge
        else if (mode == "Change Weight") {
            if (algorithm_on) {

            } else {
             
                if (edge_connect_first_time) {

                    edge_node_first = id;
                    graph.getNode(id).setAttribute("ui.class", "marked");//mark before remove
                    if (old_edge != null) {
                        old_edge.removeAttribute("ui.class");
                    }

                    edge_connect_first_time = false;
                } else {
                    edge_node_second = id;

                    for (Edge edge : graph.getEachEdge()) {
                        if (edge.getNode0() == graph.getNode(edge_node_second) && edge.getNode1() == graph.getNode(edge_node_first)
                                || edge.getNode0() == graph.getNode(edge_node_first) && edge.getNode1() == graph.getNode(edge_node_second)) {

                            selected_edge = edge.getId();
                            // System.out.println(selected_edge);

                            double v = edge.getNumber("ui.label");
                            System.out.println(v);
                            jTextField1.setText(Double.toString(v));

                            //        old_weight_text.setText("jbjb");
                            edge.setAttribute("ui.class", "marked");
                            old_edge = edge;
                            break;
                        }
                    }
                    graph.getNode(edge_node_first).removeAttribute("ui.class");
                    edge_connect_first_time = true;

                }

            }
        }//else if change weight
    }

    protected void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            Logger.getLogger(Clicks.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void buttonReleased(String id) {
    }
}
