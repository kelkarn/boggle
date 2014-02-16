package main.org.nkelkar.util.graph.api.ifc;

/**
 * User: nkelkar
 * Date: 2/11/14
 * Time: 1:21 AM
 */
public interface GenericGraph {

    public int getOrder();  // #vertices
    public int getSize();   // #edges

    public void buildGraph(Object data);
    // TODO: implement the following methods
    // public double getAvgClusterCoeff();
    // public int getDiameter();
}
