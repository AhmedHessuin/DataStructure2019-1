package datastructure;

public class Edge_Imp {

    private Node_Imp child;
    private double weight;

    /**
     *
     */
    public Edge_Imp() {
        child = null;
        weight = -1;
    }

    /**
     *
     * @param _child
     * @param _weight
     */
    public Edge_Imp(Node_Imp _child, double _weight) {
        child = _child;
        weight = _weight;
    }

    /**
     * @return the child
     */
    public Node_Imp getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(Node_Imp child) {
        this.child = child;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     *
     * @param _edge
     * @return
     */
    public boolean equals(Edge_Imp _edge) {
        if (child.equals(_edge.getChild()) && weight == _edge.getWeight()) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param _edge
     */
    public void copy(Edge_Imp _edge) {
        child = _edge.getChild();
        weight = _edge.getWeight();
    }
}
