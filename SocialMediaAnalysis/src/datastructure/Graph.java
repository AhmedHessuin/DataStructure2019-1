package datastructure;

import java.util.ArrayList;

public class Graph {

    protected ArrayList<Node> vertices;

    public Graph() {
        Node.setNODE_ID(0);
        vertices = new ArrayList<Node>();
    }

    public Graph(int noVertices) {
        Node.setNODE_ID(0);
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

}
