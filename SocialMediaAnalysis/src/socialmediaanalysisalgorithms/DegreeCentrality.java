package socialmediaanalysisalgorithms;

import datastructure.Graph_Imp;

public class DegreeCentrality extends Graph_Imp implements CentralityAnalysis {

    public DegreeCentrality() {
        super();
    }

    public DegreeCentrality(int noVertices) {
        super(noVertices);
    }

    @Override
    public void calculation() {
        for (int i = 0 ; i < getNoVertices() ; i++){
            getNode(i).setCentrality(getNode(i).getNoChildren());
        }
    }

}
