package main.org.nkelkar.util.graph;

/**
 * User: nkelkar
 * Date: 2/11/14
 * Time: 1:07 AM
 */
public class Vertex<T extends Comparable<? super T>> implements Comparable<Vertex<T>> {

    private T _data;

    public Vertex() {
        _data = null;
    }

    public Vertex(T data) {
        this._data = data;
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return this._data.compareTo(o._data);   // compare data elements
    }

    public T getData() { return this._data; }

}
