package test;

import datastructure.Edge;
import java.util.Random;
import java.util.Vector;
import socialmediaanalysis.BetweennessCentrality;
import socialmediaanalysis.ClosenessCentrality;
import socialmediaanalysis.DegreeCentrality;

public class StressTest {

    static int MAX_NO_NODES = 10;
    double[][] Data;
    double[] nodeCentrality;

    int number_of_nodes;
    int number_of_edges;

    DegreeCentrality degreeCentralityGraph;
    ClosenessCentrality ClosenessCentralityGraph;
    BetweennessCentrality BetweennessCentralityGraph;

    private class MyPair {

        private int key;
        private int value;

        public MyPair(int aKey, int aValue) {
            key = aKey;
            value = aValue;
        }

        public MyPair() {
            key = 0;
            value = 0;
        }

        public int key() {
            return key;
        }

        public void set(int key_, int value_) {
            key = key_;
            value = value_;
        }

        public int value() {
            return value;
        }
    }

    private boolean inserted(int n1, int n2, Vector<MyPair> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).key() == n1 && vector.get(i).value() == n2) {
                return true;
            } else if (vector.get(i).key() == n2 && vector.get(i).value() == n1) {
                return true;
            }
        }
        return false;
    }

    public void initiate(int centralityMethod) {
        while (true) {

            Vector<MyPair> check = new Vector<MyPair>();

            Random rand = new Random();

            number_of_nodes = rand.nextInt(MAX_NO_NODES) + 2;
            number_of_edges = rand.nextInt(number_of_nodes * (number_of_nodes - 1) / 2);

            Data = new double[number_of_nodes][number_of_nodes];
            nodeCentrality = new double[number_of_nodes];

            switch (centralityMethod) {
                case 1:
                    degreeCentralityGraph = new DegreeCentrality(number_of_nodes);
                    break;
                case 2:
                    ClosenessCentralityGraph = new ClosenessCentrality(number_of_nodes);
                    break;
                case 3:
                    BetweennessCentralityGraph = new BetweennessCentrality(number_of_nodes);
                    break;
            }

            for (int i = 0; i < number_of_nodes; i++) {
                for (int j = 0; j < number_of_nodes; j++) {
                    Data[i][j] = -1;
                }
                nodeCentrality[i] = -1;
            }

            for (int i = 0; i < number_of_edges; i++) {
                int x1, x2;
                double wt;

                x1 = rand.nextInt(number_of_nodes);
                x2 = rand.nextInt(number_of_nodes);

                while (x1 == x2 || inserted(x1, x2, check)) {
                    x1 = rand.nextInt(number_of_nodes);
                    x2 = rand.nextInt(number_of_nodes);
                }

                check.add(new MyPair(x1, x2));

                wt = rand.nextDouble() * 100 + 1;

                Data[x1][x2] = wt;
                Data[x2][x1] = wt;

                Edge edg;

                switch (centralityMethod) {
                    case 1:
                        edg = new Edge(degreeCentralityGraph.getNode(x1), wt);
                        degreeCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge(degreeCentralityGraph.getNode(x2), wt);
                        degreeCentralityGraph.getNode(x1).addChild(edg);
                        break;
                    case 2:
                        edg = new Edge(ClosenessCentralityGraph.getNode(x1), wt);
                        ClosenessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge(ClosenessCentralityGraph.getNode(x2), wt);
                        ClosenessCentralityGraph.getNode(x1).addChild(edg);
                        break;
                    case 3:
                        edg = new Edge(BetweennessCentralityGraph.getNode(x1), wt);
                        BetweennessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge(BetweennessCentralityGraph.getNode(x2), wt);
                        BetweennessCentralityGraph.getNode(x1).addChild(edg);
                        break;
                }

            }

            boolean correct = true;

            switch (centralityMethod) {
                case 1:
                    degreeCentralityGraph.calculation();
                    degreeCebtrality();
                    for (int i = 0; i < number_of_nodes; i++) {
                        if (degreeCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            correct = false;
                        }
                    }
                    break;
                case 2:
                    ClosenessCentralityGraph.calculation();
                    closenessCentrality();
                    for (int i = 0; i < number_of_nodes; i++) {
                        if (ClosenessCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            correct = false;
                        }
                    }
                    break;
                case 3:
                    BetweennessCentralityGraph.calculation();
                    betweennessCentrality();
                    for (int i = 0; i < number_of_nodes; i++) {
                        if (BetweennessCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            correct = false;
                        }
                    }
                    break;
            }

            if (correct == false) {
                System.out.println(number_of_nodes + " " + number_of_edges);
                for (int i = 0; i < number_of_nodes; i++) {
                    for (int j = 0; j < number_of_nodes; j++) {
                        if (Data[i][j] != -1) {
                            System.out.println(i + " " + j + " " + Data[i][j]);
                        }
                    }
                }
                break;
            } else {
                System.out.println("Correct answer");
            }

        }

    }

    private void degreeCebtrality() {

    }

    private void closenessCentrality() {

    }

    private void betweennessCentrality() {

    }
}
