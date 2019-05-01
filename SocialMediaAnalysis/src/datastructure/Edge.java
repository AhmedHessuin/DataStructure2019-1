package datastructure;

public class Edge {

    private Node child;
    private double weight;

    /**
     *
     */
    public Edge() {
        child = null;
        weight = -1;
    }

    /**
     *
     * @param _child
     * @param _weight
     */
    public Edge(Node _child, double _weight) {
        child = _child;
        weight = _weight;
    }

    /**
     * @return the child
     */
    public Node getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(Node child) {
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
    public boolean equals(Edge _edge) {
        if (child.equals(_edge.getChild()) && weight == _edge.getWeight()) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param _edge
     */
    public void copy(Edge _edge) {
        child = _edge.getChild();
        weight = _edge.getWeight();
    }
}
