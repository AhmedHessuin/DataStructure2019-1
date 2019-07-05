package socialmediaanalysis;

import datastructure.Edge_Imp;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import socialmediaanalysisalgorithms.BetweennessCentrality;
import socialmediaanalysisalgorithms.ClosenessCentrality;
import socialmediaanalysisalgorithms.DegreeCentrality;
import test.StressTest;

public class SocialMediaAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // GUI
        MainPlatform.initialize();

        //CL Input
        /*Scanner s = new Scanner(System.in);
        System.out.println("Enter the centrality algorithm [1 -> Degree Centrality, 2 -> Closeness Centrality, 3 -> BetweennessCentrality] : ");
        int algorithmType = s.nextInt();
        int n;
        int e;
        Edge_Imp edg;
        int src;
        int dest;
        double wt;

        switch (algorithmType) {
            case 1:
                n = s.nextInt();
                DegreeCentrality degreeCentralityGraph = new DegreeCentrality(n);
                e = s.nextInt();
                for (int i = 0; i < e; i++) {
                    src = s.nextInt();
                    dest = s.nextInt();

                    edg = new Edge_Imp(degreeCentralityGraph.getNode(src), 1);
                    degreeCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge_Imp(degreeCentralityGraph.getNode(dest), 1);
                    degreeCentralityGraph.getNode(src).addChild(edg);
                }
                degreeCentralityGraph.calculation();
                degreeCentralityGraph.print();
                break;
            case 2:
                n = s.nextInt();
                ClosenessCentrality ClosenessCentralityGraph = new ClosenessCentrality(n);
                e = s.nextInt();
                for (int i = 0; i < e; i++) {
                    src = s.nextInt();
                    dest = s.nextInt();
                    wt = s.nextDouble();

                    edg = new Edge_Imp(ClosenessCentralityGraph.getNode(src), wt);
                    ClosenessCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge_Imp(ClosenessCentralityGraph.getNode(dest), wt);
                    ClosenessCentralityGraph.getNode(src).addChild(edg);
                }
                ClosenessCentralityGraph.calculation();
                ClosenessCentralityGraph.print();
                break;
            case 3:
                n = s.nextInt();
                BetweennessCentrality BetweennessCentralityGraph = new BetweennessCentrality(n);
                e = s.nextInt();
                for (int i = 0; i < e; i++) {
                    src = s.nextInt();
                    dest = s.nextInt();
                    wt = s.nextDouble();

                    edg = new Edge_Imp(BetweennessCentralityGraph.getNode(src), wt);
                    BetweennessCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge_Imp(BetweennessCentralityGraph.getNode(dest), wt);
                    BetweennessCentralityGraph.getNode(src).addChild(edg);
                }
                BetweennessCentralityGraph.calculation();
                BetweennessCentralityGraph.print();
                break;
        }*/
        
        
        //Stress Test
        /*StressTest T1 = new StressTest();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the centrality algorithm [1 -> Degree Centrality, 2 -> Closeness Centrality, 3 -> BetweennessCentrality] : ");
        try {
            int i = s.nextInt();
            T1.initiate(i);
        } catch (ParseException ex) {
            Logger.getLogger(SocialMediaAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
