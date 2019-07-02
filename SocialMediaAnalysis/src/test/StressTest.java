package test;

import datastructure.Edge_Imp;
import java.text.ParseException;
import java.util.Random;
import java.util.Vector;
import java.util.*;
import socialmediaanalysisalgorithms.BetweennessCentrality;
import socialmediaanalysisalgorithms.ClosenessCentrality;
import socialmediaanalysisalgorithms.DegreeCentrality;

import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public class MyPair2 {

        private double key;
        private int value;

        public MyPair2(Double aKey, int aValue) {
            key = aKey;
            value = aValue;
        }

        public MyPair2() {
            key = 0;
            value = 0;
        }

        public Double key() {
            return key;
        }

        public void set(double key_, int value_) {
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

    public void initiate(int centralityMethod) throws ParseException {
        long counter = 0;
        while (true) {
            System.out.print("test " + counter + " : ");
            counter++;

            Vector<MyPair> check = new Vector<MyPair>();

            Random rand = new Random();

            number_of_nodes = rand.nextInt(MAX_NO_NODES) + 3;
            number_of_edges = rand.nextInt((number_of_nodes * (number_of_nodes - 1) / 3));

            if (number_of_edges < number_of_nodes) {
                number_of_edges = number_of_nodes;
            }

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

            for (int i = 0; i < number_of_nodes; i++) {
                int x2;
                double wt;

                x2 = rand.nextInt(number_of_nodes);

                while (i == x2 || inserted(i, x2, check)) {
                    x2 = rand.nextInt(number_of_nodes);
                }

                check.add(new MyPair(i, x2));

                wt = rand.nextInt(100 + 1);

                Data[i][x2] = wt;
                Data[x2][i] = wt;

                Edge_Imp edg;

                switch (centralityMethod) {
                    case 1:
                        edg = new Edge_Imp(degreeCentralityGraph.getNode(i), wt);
                        degreeCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(degreeCentralityGraph.getNode(x2), wt);
                        degreeCentralityGraph.getNode(i).addChild(edg);
                        break;
                    case 2:
                        edg = new Edge_Imp(ClosenessCentralityGraph.getNode(i), wt);
                        ClosenessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(ClosenessCentralityGraph.getNode(x2), wt);
                        ClosenessCentralityGraph.getNode(i).addChild(edg);
                        break;
                    case 3:
                        edg = new Edge_Imp(BetweennessCentralityGraph.getNode(i), wt);
                        BetweennessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(BetweennessCentralityGraph.getNode(x2), wt);
                        BetweennessCentralityGraph.getNode(i).addChild(edg);
                        break;
                }

            }

            for (int i = 0; i < (number_of_edges - number_of_nodes); i++) {
                int x1, x2;
                double wt;

                x1 = rand.nextInt(number_of_nodes);
                x2 = rand.nextInt(number_of_nodes);

                while (x1 == x2 || inserted(x1, x2, check)) {
                    x1 = rand.nextInt(number_of_nodes);
                    x2 = rand.nextInt(number_of_nodes);
                }

                check.add(new MyPair(x1, x2));

                wt = rand.nextInt(100 + 1);

                Data[x1][x2] = wt;
                Data[x2][x1] = wt;

                Edge_Imp edg;

                switch (centralityMethod) {
                    case 1:
                        edg = new Edge_Imp(degreeCentralityGraph.getNode(x1), wt);
                        degreeCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(degreeCentralityGraph.getNode(x2), wt);
                        degreeCentralityGraph.getNode(x1).addChild(edg);
                        break;
                    case 2:
                        edg = new Edge_Imp(ClosenessCentralityGraph.getNode(x1), wt);
                        ClosenessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(ClosenessCentralityGraph.getNode(x2), wt);
                        ClosenessCentralityGraph.getNode(x1).addChild(edg);
                        break;
                    case 3:
                        edg = new Edge_Imp(BetweennessCentralityGraph.getNode(x1), wt);
                        BetweennessCentralityGraph.getNode(x2).addChild(edg);
                        edg = new Edge_Imp(BetweennessCentralityGraph.getNode(x2), wt);
                        BetweennessCentralityGraph.getNode(x1).addChild(edg);
                        break;
                }

            }

            System.out.print("Running..., ");

            boolean correct = true;
            Date t1 = null;
            long algorithmTime = 0;
            long testTime = 0;
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            switch (centralityMethod) {
                case 1:
                    t1 = format.parse(LocalTime.now().toString());
                    degreeCentralityGraph.calculation();
                    testTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();
                    t1 = format.parse(LocalTime.now().toString());
                    degreeCebtrality();
                    algorithmTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();

                    for (int i = 0; i < number_of_nodes; i++) {
                        if (degreeCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            System.out.println("Wrong Answer in node: " + i);
                            System.out.println("Algorithm Output: " + degreeCentralityGraph.getNode(i).getCentrality() + " Test Output: " + nodeCentrality[i]);
                            correct = false;
                        }
                    }
                    break;
                case 2:
                    t1 = format.parse(LocalTime.now().toString());
                    ClosenessCentralityGraph.calculation();
                    testTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();
                    t1 = format.parse(LocalTime.now().toString());
                    closenessCentrality();
                    algorithmTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();

                    for (int i = 0; i < number_of_nodes; i++) {
                        if (ClosenessCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            System.out.println("Wrong Answer in node: " + i);
                            System.out.println("Algorithm Output: " + ClosenessCentralityGraph.getNode(i).getCentrality() + " Test Output: " + nodeCentrality[i]);
                            correct = false;
                        }
                    }
                    break;
                case 3:
                    t1 = format.parse(LocalTime.now().toString());
                    BetweennessCentralityGraph.calculation();
                    testTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();
                    t1 = format.parse(LocalTime.now().toString());
                    betweennessCentrality();
                    algorithmTime = format.parse(LocalTime.now().toString()).getTime() - t1.getTime();

                    for (int i = 0; i < number_of_nodes; i++) {
                        if (BetweennessCentralityGraph.getNode(i).getCentrality() != nodeCentrality[i]) {
                            System.out.println("Wrong Answer in node: " + i);
                            System.out.println("Algorithm Output: " + BetweennessCentralityGraph.getNode(i).getCentrality() + " Test Output: " + nodeCentrality[i]);
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
                System.out.println("Correct Answer, Test time: " + testTime + " Algorithm time: " + algorithmTime);
            }

        }

    }

    private void degreeCebtrality() {
        for (int i = 0; i < number_of_nodes; i++) {
            double sum = 0;
            for (int j = 0; j < number_of_nodes; j++) {
                if (Data[i][j] != -1) {
                    sum += 1;
                }
            }
            nodeCentrality[i] = sum;
        }
    }

    public class Compare implements Comparator<MyPair2> {
        // Overriding compare()method of Comparator  
        // for descending order of cgpa 

        public int compare(MyPair2 s1, MyPair2 s2) {
            if (s1.key() > s2.key()) {
                return 1;
            } else if (s1.key() < s2.key()) {
                return -1;
            }
            return 0;
        }
    }

    private void closenessCentrality() {

        // data section//
        //========================================================================================//
        // first time fill need optmization later//
        for (int k = 0; k < number_of_nodes; k++) {
            int connected = number_of_nodes;
            boolean[] Marked_List = new boolean[number_of_nodes];
            Double out_put[] = new Double[number_of_nodes];
            PriorityQueue<MyPair2> P_Queue = new PriorityQueue<MyPair2>(new Compare());//priority queue

            Marked_List[k] = true;
            out_put[k] = 0.0;// first time 

            for (int i = 0; i < number_of_nodes; i++) {
                if (Data[k][i] == -1) {
                    continue;
                } else {

                    P_Queue.add(new MyPair2(Data[k][i], i));// i is the node number 
                }
            }

            // operation section //
            while (connected != 0 && !P_Queue.isEmpty()) {
                // data section//

                MyPair2 Out = P_Queue.poll();// got the peek 22.5  1
                int Next_Mark = Out.value();// get the next to mark
                double Next_Weight = Out.key();//get the weight
                //================================================================//
                if (Marked_List[Next_Mark] == false) {

                    connected--;

                    Marked_List[Next_Mark] = true;// mark it visited

                    out_put[Next_Mark] = Next_Weight;// get the number of pathes like my parent

                    // add the inserted childrens
                    for (int i = 0; i < number_of_nodes; i++) {

                        if (Data[Next_Mark][i] == -1) {
                            continue;
                        } else {
                            if (Marked_List[i]) {
                                continue;
                            }

                            P_Queue.add(new MyPair2(Data[Next_Mark][i] + out_put[Next_Mark], i));// i is the node number 
                        }

                        //}
                    }

                }
            }// for loop on all vertices
            double sum = 0;
            for (int z = 0; z < number_of_nodes; z++) {
                if (out_put[z] != null) {
                    sum += out_put[z];
                }
            }

            nodeCentrality[k] = (double) ((double) (number_of_nodes - 1) / sum);

        }// k for

    }

    private void betweennessCentrality() {

    }
}
