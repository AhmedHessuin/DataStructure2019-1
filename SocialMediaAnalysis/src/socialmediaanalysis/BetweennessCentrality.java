package socialmediaanalysis;

import datastructure.Graph;
import datastructure.Node;
import java.util.ArrayList;
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
    }

    void dijkestra_H(Node input) {

        // data section//
        boolean[] Marked_List = new boolean[getNoVertices()];
        int not_connected = getNoVertices();
        PriorityQueue<Pair<Double, Integer>> P_Queue = new PriorityQueue<Pair<Double, Integer>>();
        double[] distance = new double[getNoVertices()];
        //========================================================================================//
        // first time fill need optmization later//
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
                // add the inserted childrens
                for (int i = 0; i < getNode(Next_Mark).getNoChildren(); i++) {
                    Pair<Double, Integer> Dummy_H
                            = new Pair<Double, Integer>(getNode(Next_Mark).getChildren(i).getWeight()
                                    + Next_Weight, getNode(Next_Mark).getChildren(i).getChild().getID());

                    P_Queue.add(Dummy_H);
                }

            }

        }// for loop on all vertices

    }

}
