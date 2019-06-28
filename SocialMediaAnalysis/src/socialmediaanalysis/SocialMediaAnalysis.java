package socialmediaanalysis;

import datastructure.*;
import java.util.Scanner;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Group; 

import java.*;
import java.awt.Button;

import javafx.stage.* ;

import socialmediaanalysisalgorithms.BetweennessCentrality;
import socialmediaanalysisalgorithms.ClosenessCentrality;
import socialmediaanalysisalgorithms.DegreeCentrality;


public class SocialMediaAnalysis extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        
      Scene scene = new Scene(root);
        
        
        ////////testing code ///////

Graph g = new Graph(10) ;

g.addUndirectedEdge(0, 1, 1) ;
g.addUndirectedEdge(0, 2, 1) ;
g.addUndirectedEdge(1, 3, 1) ;
g.addUndirectedEdge(2, 3, 1) ;

init ( g, 500,500 , root) ;

  //////////////////////////////////// 
   


        stage.setScene(scene);
        
        stage.show();
    }

    
    
    
    public   boolean init (Graph G , int windowXcenter , int windowYcenter , Parent root) {

        int spacing = 60 ;
        float thetaSector = (float)(2.0*Math.PI) / G.getNoVertices() ;

        
         float radius = (float) ( (spacing + 100.0) / (2.0 * Math.sin(thetaSector / 2)));


         float X = windowXcenter + (float) (radius)  ;
         float Y = windowYcenter ;

float theta = 0 ;
for ( Node N  : G.getVertices()) {

N.x = X ; N.y = Y ;


 theta += thetaSector ;
 //newX = radius * Math.cos(theta) ;
 //newY = radius * Math.sin(theta) ;

X =  (float) (windowXcenter   +  radius * Math.cos(theta)) ;
Y=  (float) (windowYcenter     +  radius * Math.sin(theta))  ;
 //X += radius * Math.cos(theta) ;
 //Y += radius * Math.sin(theta) ;
 
}

float maxCentrality = -1 ;
for ( Node N : G.getVertices()) {
	
	if ( N.getCentrality() > maxCentrality) maxCentrality = (float) N.getCentrality() ;
}

maxCentrality = Math.abs(maxCentrality) ;
for ( Node N : G.getVertices()) {
	
	
	Rectangle rectangle = new Rectangle(N.x, N.y, 100 /maxCentrality, 100 / maxCentrality );


	(   (AnchorPane) root).getChildren().add(rectangle);

	
}

for ( Node N : G.getVertices()) {
	

	for ( Edge E : N.getChildren()) {
		
		Line l = new Line(N.x, N.y, E.getChild().x , E.getChild().y) ;
		
		(   (AnchorPane) root).getChildren().add(l);
	}
	
}
return true ;
    }

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // GUI
        launch(args);
        
        
        //CL Input
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the centrality algorithm [1 -> Degree Centrality, 2 -> Closeness Centrality, 3 -> BetweennessCentrality] : ");
        int algorithmType = s.nextInt();
        int n;
        int e;
        Edge edg;
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

                    edg = new Edge(degreeCentralityGraph.getNode(src), 1);
                    degreeCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge(degreeCentralityGraph.getNode(dest), 1);
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

                    edg = new Edge(ClosenessCentralityGraph.getNode(src), wt);
                    ClosenessCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge(ClosenessCentralityGraph.getNode(dest), wt);
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

                    edg = new Edge(BetweennessCentralityGraph.getNode(src), wt);
                    BetweennessCentralityGraph.getNode(dest).addChild(edg);
                    edg = new Edge(BetweennessCentralityGraph.getNode(dest), wt);
                    BetweennessCentralityGraph.getNode(src).addChild(edg);
                }
                BetweennessCentralityGraph.calculation();
                BetweennessCentralityGraph.print();
                break;
        }
        
        
        
        //Stress Test
        /*StressTest T1 = new StressTest();
        try {
            T1.initiate(2);
        } catch (ParseException ex) {
            Logger.getLogger(SocialMediaAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
    
}
