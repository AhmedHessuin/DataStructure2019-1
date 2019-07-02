package socialmediaanalysisalgorithms;

import datastructure.Graph_Imp;

public class DegreeCentrality extends Graph_Imp implements CentralityAnalysis {

    public DegreeCentrality() {
        super();
    }

    public DegreeCentrality(int noVertices) {
        super(noVertices);
    }

    public DegreeCentrality(Graph_Imp _graph) {
        vertices = _graph.getVertices();
    }

    @Override
    public void calculation() {
        for (int i = 0; i < getNoVertices(); i++) {
            getNode(i).setCentrality(getNode(i).getNoChildren());
        }
    }

}
