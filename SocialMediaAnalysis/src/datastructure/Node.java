package datastructure;

import java.util.ArrayList;

public class Node {

    public static int NODE_ID = 0;

    public float x,y ;
    private int ID;
    private double centrality;
    private ArrayList<Edge> children;

    /**
     *
     * @param isGraphNode if true,it gives the node id; else if false if gives
     * the node id equal to -1.
     */
    public Node(boolean isGraphNode) {
        if (isGraphNode) {
            ID = NODE_ID;
            NODE_ID++;
        } else {
            ID = -1;
        }
        centrality = -1;
        children = new ArrayList<Edge>();
    }

    /**
     *
     * @param isGraphNode if true,it gives the node id; else if false if gives
     * the node id equal to -1.
     * @param noChildren define initial no of children.
     */
    public Node(boolean isGraphNode, int noChildren) {
        if (isGraphNode) {
            ID = NODE_ID;
            NODE_ID++;
        } else {
            ID = -1;
        }
        centrality = -1;
        children = new ArrayList<Edge>(noChildren);
    }

    /**
     * for debugging only. it prints in the console window;
     */
    public void printNode() {
        System.out.print("Vertex : " + ID + ", Centrality : " + centrality);
    }

    /**
     * for debugging only. it prints in the console window;
     */
    public void printNodeChildren() {
        System.out.print("Adjacency list of [");
        printNode();
        System.out.println("]");
        System.out.print("Head");
        for (int i = 0; i < children.size(); i++) {
            System.out.print(" -> [");
            children.get(i).getChild().printNode();
            System.out.print(" , weight :" + children.get(i).getWeight() + "]");
        }
        System.out.println("\n");
    }

    /**
     *
     * @param _child
     */
    public void addChild(Edge _child) {
        children.add(_child);
    }

    /**
     *
     * @param _children
     */
    public void addChildren(ArrayList<Edge> _children) {
        children.addAll(_children);
    }

    /**
     *
     * @param _child
     */
    public void removeChild(Edge _child) {
        children.remove(_child);
    }

    /**
     *
     */
    public void removeAllChildren() {
        children.clear();
    }

    /**
     * @param aNODE_ID the NODE_ID to set
     */
    public static void setNODE_ID(int aNODE_ID) {
        NODE_ID = aNODE_ID;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the centrality
     */
    public double getCentrality() {
        return centrality;
    }

    /**
     * @param centrality the centrality to set
     */
    public void setCentrality(double centrality) {
        this.centrality = centrality;
    }

    /**
     * @return the children
     */
    public ArrayList<Edge> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(ArrayList<Edge> children) {
        this.children = children;
    }

    public int getNoChildren() {
        return children.size();
    }

    /**
     * @param index
     * @return the child by index
     */
    public Edge getChildren(int index) {
        return children.get(index);
    }
    
}
