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
                System.out.print(array[i][j].key + "    " + array[i][j].value + "   && ");
            }
            System.out.println();
        }
    }
//

    public class Compare implements Comparator<Dijkestra_Data_Type> {
        // Overriding compare()method of Comparator  
        // for descending order of cgpa 

        public int compare(Dijkestra_Data_Type s1, Dijkestra_Data_Type s2) {
            if (s1.key() > s2.key()) {
                return 1;
            } else if (s1.key() < s2.key()) {
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

    public class Dijkestra_Data_Type {

        private double key;//weight
        private int value;//me
        private int parent;//my parent

        public Dijkestra_Data_Type(Double aKey, int aValue, int aparent) {
            key = aKey;
            value = aValue;
            parent = aparent;
        }

        public Dijkestra_Data_Type() {
            key = 0;
            value = 0;
            parent = 0;
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

        public int parent() {
            return parent;
        }
    }

    //
    public MyPair[] dijkestra_H(Node input) {

        // data section//
        boolean[] Marked_List = new boolean[getNoVertices()];
        // int not_connected = getNoVertices() - 1;
        // int[] paths = new int[getNoVertices()];
        MyPair[] out_put = new MyPair[getNoVertices()];//--------------
        Dijkestra_Data_Type[] new_out_put = new Dijkestra_Data_Type[getNoVertices()];//=============
        PriorityQueue<Dijkestra_Data_Type> P_Queue = new PriorityQueue<Dijkestra_Data_Type>(new Compare());//priority queue
        double[] distance = new double[getNoVertices()];//distance will be removed later
        //========================================================================================//
        // first time fill need optmization later//
        Marked_List[input.getID()] = true;
        out_put[input.getID()] = new MyPair(0.0, 1);// first time 

        System.out.println(P_Queue.comparator());
        for (int i = 0; i < input.getNoChildren(); i++) {
            //Pair<Double, Integer> Dummy_H = new Pair<Double, Integer>(input.getChildren(i).getWeight(), input.getChildren(i).getChild().getID());
            P_Queue.add(new Dijkestra_Data_Type(input.getChildren(i).getWeight(), input.getChildren(i).getChild().getID(), input.getID()));
        }

        // operation section //
        while (!P_Queue.isEmpty()) {
            // data section//
            
            Dijkestra_Data_Type Out = P_Queue.poll();// got the peek
            int Next_Mark = Out.value();// get the next to mark
            int My_parent = Out.parent();//get my parent
            double Next_Weight = Out.key();//get the weight
            //================================================================//
            if (Marked_List[Next_Mark] == false) {
                // first time see this node
               // not_connected--; // 1 is connected 
                Marked_List[Next_Mark] = true;// mark it visited
                distance[Next_Mark] = Next_Weight;// give it the new distance
                
                // new try
                out_put[Next_Mark] = new MyPair(Next_Weight,out_put[My_parent].value() );// get the number of pathes like my parent
                //==========================//

                // add the inserted childrens
                for (int i = 0; i < getNode(Next_Mark).getNoChildren(); i++) {
                    // modified//

                    // if (Marked_List[getNode(Next_Mark).getChildren(i).getChild().getID()] == false) {
               //     Pair<Double, Integer> Dummy_H
                 //           = new Pair<Double, Integer>(getNode(Next_Mark).getChildren(i).getWeight()
                   //                 + Next_Weight, getNode(Next_Mark).getChildren(i).getChild().getID());

                    P_Queue.add(new Dijkestra_Data_Type ( 
                            getNode(Next_Mark).getChildren(i).getWeight()+Next_Weight,
                            getNode(Next_Mark).getChildren(i).getChild().getID()
                            ,getNode(Next_Mark).getID()));
                    
                    //}
                }

            } else {
                int Already_Marked = Out.value();
                  int My_parent_new = Out.parent();//get my parent
                double New_Weight = Out.key();
                
                if (distance[Already_Marked] == New_Weight) {
                    out_put[Next_Mark].set(New_Weight,  
                            out_put[Next_Mark].value()+out_put[My_parent_new].value());
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
