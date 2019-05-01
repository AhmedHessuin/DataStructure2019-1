package socialmediaanalysis;

import datastructure.Graph;
import datastructure.Node;
import java.util.ArrayList;

public class BetweennessCentrality extends Graph implements CentralityAnalysis {
    
    public BetweennessCentrality() {
        super();
    }
    
    public BetweennessCentrality(int noVertices) {
        super(noVertices);
    }
    
    @Override
    public void calculation() {
    }
    
}
