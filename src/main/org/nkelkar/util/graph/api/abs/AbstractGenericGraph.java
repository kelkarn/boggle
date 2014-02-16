package main.org.nkelkar.util.graph.api.abs;

/**
 * User: nkelkar
 * Date: 2/11/14
 * Time: 1:42 AM
 */
public abstract class AbstractGenericGraph<T> {

    private int order;
    private int size;

    // TODO: add more abstract methods

    public abstract void buildGraph(T[] data);  // used to build internal graph data structure
    public abstract boolean checkForNulls(T[] data);    // preliminary data check for null values
}
