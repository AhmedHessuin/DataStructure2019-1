package socialmediaanalysis;

import datastructure.Graph;
import datastructure.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import javafx.util.Pair;

public class BetweennessCentrality extends Graph implements CentralityAnalysis {

    public BetweennessCentrality() {
        super();
    }

    public BetweennessCentrality(int noVertices) {
        super(noVertices);
    }

    @Override
    public void calculation() {
   

        MyPair[][] array = dijkestra_for_all_H();
        for (int i = 0; i < getNoVertices(); i++) {
            for (int j = 0; j < getNoVertices(); j++) {
                System.out.print(array[i][j].key  + "    "+array[i][j].value  +"   && ");
            }
            System.out.println();
        }
    }
//

    public class Compare implements Comparator<Pair<Double, Integer>> {
        // Overriding compare()method of Comparator  
        // for descending order of cgpa 

        public int compare(Pair<Double, Integer> s1, Pair<Double, Integer> s2) {
            if (s1.getKey() > s2.getKey()) {
                return 1;
            } else if (s1.getKey() < s2.getKey()) {
                return -1;
            }
            return 0;
        }
    }

    public class MyPair {

        private double key;
        private int value;

        public MyPair(Double aKey, int aValue) {
            key = aKey;
            value = aValue;
        }
        public MyPair() {
           key=0;
           value=1;
        }

        public Double key() {
            return key;
        }
         public void set(double key_,int value_) {
         key=key_;
         value=value_;
        }

        public int value() {
            return value;
        }
    }

    //
    public MyPair[] dijkestra_H(Node input) {

        // data section//
        boolean[] Marked_List = new boolean[getNoVertices()];
        int not_connected = getNoVertices() - 1;
        int[] paths = new int[getNoVertices()];
        MyPair[] out_put = new MyPair[getNoVertices()];
        PriorityQueue<Pair<Double, Integer>> P_Queue = new PriorityQueue<Pair<Double, Integer>>(new Compare());
        double[] distance = new double[getNoVertices()];
        //========================================================================================//
        // first time fill need optmization later//
        Marked_List[input.getID()] = true;
        out_put[input.getID()]= new MyPair(0.0, 0);
        System.out.println(P_Queue.comparator());
        for (int i = 0; i < input.getNoChildren(); i++) {
            Pair<Double, Integer> Dummy_H = new Pair<Double, Integer>(input.getChildren(i).getWeight(), input.getChildren(i).getChild().getID());
            P_Queue.add(Dummy_H);
        }

        // operation section //
        while (not_connected != 0) {

            Pair<Double, Integer> Out = P_Queue.poll();
            int Next_Mark = Out.getValue();
            double Next_Weight = Out.getKey();
            if (Marked_List[Next_Mark] == false) {
                // first time see this node
                not_connected--; // 1 is connected 
                Marked_List[Next_Mark] = true;// mark it visited
                distance[Next_Mark] = Next_Weight;// give it the new distance

                // new try
                new MyPair(Next_Weight, Next_Mark);
                out_put[Next_Mark]= new MyPair(Next_Weight, 0);
                //==========================//

                // add the inserted childrens
                for (int i = 0; i < getNode(Next_Mark).getNoChildren(); i++) {
                    if (Marked_List[getNode(Next_Mark).getChildren(i).getChild().getID()] == false) {
                        Pair<Double, Integer> Dummy_H
                                = new Pair<Double, Integer>(getNode(Next_Mark).getChildren(i).getWeight()
                                        + Next_Weight, getNode(Next_Mark).getChildren(i).getChild().getID());

                        P_Queue.add(Dummy_H);
                    }
                }

            } else {
                int Already_Marked = Out.getValue();
                double New_Weight = Out.getKey();
                if (distance[Already_Marked] == New_Weight) {
                    out_put[Next_Mark].value++;
                }

            }
        }// for loop on all vertices
        return out_put;
    }

    public MyPair[][] dijkestra_for_all_H() {
        MyPair[][] out_put = new MyPair[getNoVertices()][getNoVertices()];
        for (int i = 0; i < getNoVertices(); i++) {
            out_put[i] = dijkestra_H(getNode(i));
        }

        return out_put;

    }

    public boolean YOU_PASS_THROW_ME(Node src, Node wanted) {
        double[] src_array;
        double[] wanted_array;
       // src_array = dijkestra_H(src);
       // wanted_array = dijkestra_H(wanted);
        // distance from sorcee to me + me to next = from him to next  i am in the middle

        return false;
    }
}
