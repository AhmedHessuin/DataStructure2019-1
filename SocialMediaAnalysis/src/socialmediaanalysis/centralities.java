package socialmediaanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


import datastructure.*;




public class centralities {


     /**
     *
     * @param Graph G : the needed graph
     * @return Arraylist<int> result : the degree centrality of each node sorted by the node index
     */

    public static ArrayList<Integer> degree ( Graph G) {

        ArrayList<Integer> result = new ArrayList<Integer>() ;


        for ( int i = 0 ; i< G.getNoVertices() ; i++) {


            result.add(G.getNode(i).getNoChildren()) ;
        }


        return result ;
    } 




     /**
     *
     * @param  G : the graph
     * @param  src : source node
     * @return Arraylist<dijkstra_unit> 
     * the shortest path from node src to every node in the graph formated as 
     * : parent ; node ; distance for each unit in the Arraylist 
     */


     public static dijkstra_unit[] dijkstra ( Graph G , Node src ){
       
        
        PriorityQueue <dijkstra_unit>table = new PriorityQueue<dijkstra_unit>(new Comparator<dijkstra_unit>() {

            @Override
            public int compare(dijkstra_unit u1, dijkstra_unit u2) {
                if ( u1.distance > u2.distance) return 1 ;
                else return -1 ;
            }
        });


dijkstra_unit  result [] = new dijkstra_unit [G.getNoVertices() ];

//initialization //
for( int i =0 ; i<G.getNoVertices() ; i++){
     result[i]=new dijkstra_unit(0, 0, -1) ;
     }


table.add(new dijkstra_unit(src.getID() , src.getID() , 0 ) ) ;
////////////////////////

while (! table.isEmpty() ) {
dijkstra_unit temp = table.poll() ; 

if(result[temp.nodeId].distance ==-1 )
{
    result[temp.nodeId]= temp;
}

for (Edge  E : G.getNode(temp.nodeId).getChildren() ) {
   
   if(result[E.getChild().getID()].distance ==-1  ){
   
    dijkstra_unit child = new dijkstra_unit(temp.nodeId, E.getChild().getID() , (float) ( temp.distance + E.getWeight())  );
    table.add(child ) ;
   }
}




}





return result ;

     }



     public static float[]  closeness(Graph G) {

        int numNodes = G.getNoVertices() ;
       float res[] = new float [G.getNoVertices() ] ; 
        for ( int i =0 ; i < numNodes ; i++) {
            float sumOfPathes = sumShortestPathesFrom (G , G.getNode(i) ) ;

            res[i]= (float)((numNodes-1) / sumOfPathes) ;
        }

        return res ;

     }

     private static int sumShortestPathesFrom(Graph G ,Node node) {

        dijkstra_unit all [] = dijkstra(G, node) ;
        int res = 0 ;
        for ( dijkstra_unit D : all )
        {
            if (D.distance!= -1)
            {
                res+=D.distance ;
            }

        }
        return res;
    }





    private static float[] betweenness (Graph G ){


        
    }
    public static void main(String[] args) {
         
        Graph g = new Graph(4) ;

        g.addUndirectedEdge(0, 2, 10) ;
        g.addUndirectedEdge(1, 0, 1) ;
        g.addUndirectedEdge(0, 3, 5) ;
        g.addUndirectedEdge(3, 1, 1) ;
        g.addUndirectedEdge(2, 3, 4) ;

        float res [] = closeness(g) ;

        System.out.print(Arrays.toString(res));
      

     }


}

