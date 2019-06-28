/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediaanalysis;

import java.net.URL;
import java.util.ResourceBundle;

import datastructure.Edge;
import datastructure.Graph;
import datastructure.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ibrah
 * 
 * 
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event)throws Exception {
       
    	/////testing/////////////
    	Graph g = new Graph(10) ;

    	g.addUndirectedEdge(0, 1, 1) ;
    	g.addUndirectedEdge(0, 2, 1) ;
    	g.addUndirectedEdge(1, 3, 1) ;
    	g.addUndirectedEdge(2, 3, 1) ;

    	Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")) ;
    	init ( g, 500,500 , root ) ;
    	( (AnchorPane) root).requestLayout();
    	
    	//////////////////////////////////////////
    	
    	System.out.println("You clicked me!");
        label.setText("Hello World!");
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

	(   (AnchorPane) root).requestLayout();
}

for ( Node N : G.getVertices()) {
	

	for ( Edge E : N.getChildren()) {
		
		Line l = new Line(N.x, N.y, E.getChild().x , E.getChild().y) ;
		
		(   (AnchorPane) root).getChildren().add(l);
		(   (AnchorPane) root).requestLayout();

	}
	
}

((AnchorPane) root).requestLayout();

return true ;
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
