package socialmediaanalysis;

import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import static socialmediaanalysis.FXMLDocumentController.graph;

public class Clicks extends FXMLDocumentController implements ViewerListener {

    protected boolean loop = true;
    private Viewer vierer2;
    private Graph graph2;
    private String mark_id_new;
    private String mark_id_old;
    private String last_clicked = "0";
    private boolean first_time = true;

    public Clicks(Viewer viewer, Graph graph) {
        // We do as usual to display a graph. This
        // connect the graph outputs to the viewer.
        // The viewer is a sink of the graph.
        vierer2 = viewer;
        graph2 = graph;

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
        fromViewer = viewer.newViewerPipe();

        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        // Then we need a loop to do our work and to wait for events.
        // In this loop we will need to call the
        // pump() method before each use of the graph to copy back events
        // that have already occurred in the viewer thread inside
        // our thread.
        while (loop) {
            fromViewer.pump();
        }

//  System.out.println("Button pushed on node ");
        // here your simulation code.
        // You do not necessarily need to use a loop, this is only an example.
        // as long as you call pump() before using the graph. pump() is non
        // blocking.  If you only use the loop to look at event, use blockingPump()
        // to avoid 100% CPU usage. The blockingPump() method is only available from
        // the nightly builds.
    }

    public void viewClosed(String id) {
        loop = false;
       
    }

    public void buttonPushed(String id) {
       
        mark_id_new = id;

        if (mark_id_new == mark_id_old) {

            if (graph.getNode(id).getAttribute("ui.class") == "marked") {

                remove_clicked(id);

            } else {

                // System.out.println("Button set  node " + id);
                clicked_on_node(id);

            }

        } else {
            if (!first_time) {
                remove_clicked(mark_id_old);
            }
            first_time = false;
            mark_id_old = mark_id_new;
        }
    }

    public void buttonReleased(String id) {

    }

}
