package datastructure;

import java.util.ArrayList;

public class Graph_Imp {

    protected ArrayList<Node_Imp> vertices;

    public Graph_Imp() {
        Node_Imp.setNODE_ID(0);
        vertices = new ArrayList<Node_Imp>();
    }

    public Graph_Imp(int noVertices) {
        Node_Imp.setNODE_ID(0);
        vertices = new ArrayList<Node_Imp>(noVertices);
        for (int i = 0; i < noVertices; i++) {
            Node_Imp newNode = new Node_Imp(true);
            vertices.add(newNode);
        }
    }

    /**
     * @return the vertices
     */
    public ArrayList<Node_Imp> getVertices() {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<Node_Imp> vertices) {
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
    public Node_Imp getNode(int index) {
        return vertices.get(index);
    }

    /**
     *
     * @param _node
     */
    public void addNode(Node_Imp _node) {
        vertices.add(_node);
    }

    /**
     *
     * @param _node
     */
    public void removeNode(Node_Imp _node) {
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