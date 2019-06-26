package socialmediaanalysisalgorithms;

import datastructure.Graph;

public class ClosenessCentrality extends Graph implements CentralityAnalysis {

    public ClosenessCentrality() {
        super();
    }

    public ClosenessCentrality(int noVertices) {
        super(noVertices);
    }

    private double dijkstra(int singleSourceInd) {
        double newDist = 0;
        int v = getNoVertices();
        int closestVertex = -1;
        double distance[] = new double[v];
        boolean visitedNodes[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            distance[i] = (i != singleSourceInd) ? Double.MAX_VALUE : 0;
            visitedNodes[i] = false;
        }

        for (int i = 0; i < v - 1; i++) {
            closestVertex = closestVertex(distance, visitedNodes);
            visitedNodes[closestVertex] = true;

            for (int j = 0; j < getNode(closestVertex).getNoChildren(); j++) {
                int childID = getNode(closestVertex).getChildren(j).getChild().getID();
                for (int k = 0; k < getNoVertices(); k++) {
                    if (childID == getNode(k).getID()) {
                        childID = k;
                        break;
                    }
                }
                if (!visitedNodes[childID] && distance[closestVertex] != Double.MAX_VALUE) {
                    newDist = distance[closestVertex] + getNode(closestVertex).getChildren(j).getWeight();
                    if (newDist < distance[childID]) {
                        distance[childID] = newDist;
                    }
                }
            }
        }

        return closenessEquation(v, distance);
    }

    private int closestVertex(double[] distance, boolean[] visitedNodes) {
        int closenessVertex = -1;

        for (int i = 0; i < distance.length; i++) {
            if ((closenessVertex == -1 || distance[i] < distance[closenessVertex]) && !visitedNodes[i]) {
                closenessVertex = i;
            }
        }
        return closenessVertex;
    }

    private double closenessEquation(int N, double[] distances) {
        int num = N - 1;
        double den = 0;

        for (int i = 0; i < N; i++) {
            den += distances[i];
        }

        return (double) num / den;
    }

    @Override
    public void calculation() {
        for (int i = 0; i < getNoVertices(); i++) {
            getNode(i).setCentrality(dijkstra(getNode(i).getID()));
        }
    }

}
