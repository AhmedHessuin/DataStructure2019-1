package datastructure;

import java.util.ArrayList;

public class Graph {

    protected ArrayList<Node> vertices;

    public Graph() {
        vertices = new ArrayList<Node>();
    }

    public Graph(int noVertices) {
        vertices = new ArrayList<Node>(noVertices);
        for (int i = 0; i < noVertices; i++) {
            Node newNode = new Node(true);
            vertices.add(newNode);
        }
    }

    /**
     * @return the vertices
     */
    public ArrayList<Node> getVertices() {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<Node> vertices) {
        this.vertices = vertices;
    }

    /**
     *
     * @return no of vertices
     */
    public int getNoVertices() {
        return vertices.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public Node getNode(int index) {
        return vertices.get(index);
    }

    /**
     *
     * @param _node
     */
    public void addNode(Node _node) {
        vertices.add(_node);
    }

    /**
     *
     * @param _node
     */
    public void removeNode(Node _node) {
        vertices.remove(_node);
    }

    /**
     *
     */
    public void removeAllNodes() {
        vertices.clear();
    }

    /**
     *
     */
    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).printNodeChildren();
        }
    }



    ////////////update /////////////////
boolean addUndirectedEdge ( Node n1 , Node n2  , int w) {

    vertices.get(n1.getID()).addChild(new Edge(vertices.get(n2.getID()) , w));
    vertices.get(n2.getID()).addChild(new Edge(vertices.get(n1.getID()) , w));

    return true ;
}

public boolean addUndirectedEdge ( int n1 , int n2  , int w) {

    vertices.get(n1).addChild(new Edge(vertices.get(n2) , w));
    vertices.get(n2).addChild(new Edge(vertices.get(n1) , w));

    return true ;
}

    ////////////////////////////////////

}
