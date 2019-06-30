package socialmediaanalysis;

import datastructure.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import static socialmediaanalysis.FXMLDocumentController.last_id;
import static socialmediaanalysis.FXMLDocumentController.mode;

public class Clicks extends Thread implements ViewerListener {

    protected boolean loop = true;
    private Viewer vierer2;
    private Graph graph2;
    private String mark_id_new;
    private String mark_id_old;
    private String edge_node_first = null;
    private String edge_node_second = null;
    private int connect_times = 0;
    private boolean edge_connect_first_time = true;
    private boolean first_time = true;
    private FXMLDocumentController fXMLDocumentController;
    private ViewerPipe fromViewer;
    private Graph graph;
    private Viewer viewer;

    public Clicks(Viewer _viewer, Graph _graph, ViewerPipe _fromViewer, FXMLDocumentController controller) {
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        viewer = _viewer;
        vierer2 = viewer;
        graph = _graph;
        graph2 = graph;

        fXMLDocumentController = controller;
        // The default action when closing the view is to quit
        // the program.
        //            viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        // We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        fromViewer = _fromViewer;
        fromViewer = viewer.newViewerPipe();

        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        // Then we need a loop to do our work and to wait for events.
        // In this loop we will need to call the
        // pump() method before each use of the graph to copy back events
        // that have already occurred in the viewer thread inside
        // our thread.
        /*    while (loop) {
            fromViewer.pump();
        }*/
//  System.out.println("Button pushed on node ");
        // here your simulation code.
        // You do not necessarily need to use a loop, this is only an example.
        // as long as you call pump() before using the graph. pump() is non
        // blocking.  If you only use the loop to look at event, use blockingPump()
        // to avoid 100% CPU usage. The blockingPump() method is only available from
        // the nightly builds.
    }

    @Override
    public void run() {
        while (loop) {
            fromViewer.pump();
        }
    }

    public void viewClosed(String id) {
        loop = false;

    }

    public void buttonPushed(String id) {

        if (mode == "Node Edges") {
            mark_id_new = id;

            if (mark_id_new == mark_id_old) {

                if (graph.getNode(id).getAttribute("ui.class") == "marked") {

                    graph.getNode(id).removeAttribute("ui.class");
                    for (Edge edge : graph.getNode(id).getEachEdge()) {
                        edge.removeAttribute("ui.class");
                        sleep(0);
                    }
                } else {

                    System.out.println("Button set  node " + id);
                    graph.getNode(id).setAttribute("ui.class", "marked");
                    for (Edge edge : graph.getNode(id).getEachEdge()) {
                        edge.setAttribute("ui.class", "marked");
                        sleep(200);
                    }
                    //fXMLDocumentController.clicked_on_node(graph, id);

                }

            } else {
                if (!first_time) {
                    graph.getNode(mark_id_old).removeAttribute("ui.class");
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
            String x = Integer.toString(last_id + 1);
            last_id++;
            graph.addNode(x);
            graph.getNode(x).addAttribute("ui.label", graph.getNode(x).getId());
        }//esle if node add
        else if (mode == "Add Edge") {

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

        }//else if edge add
        else if (mode == "Remove Node") {

            for (Edge edge : graph.getEachEdge()) {
                if (edge.getNode0() == graph.getNode(id) || edge.getNode1() == graph.getNode(id)) {

                    graph.removeEdge(edge);
                }
            }
            graph.removeNode(id);

        }//else if remove node
        else if (mode == "Remove Edge") {

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

        }//else if remove edge
    }

    protected void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buttonReleased(String id) {

    }

}
