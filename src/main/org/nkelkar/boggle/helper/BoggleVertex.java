package main.org.nkelkar.boggle.helper;

import main.org.nkelkar.util.graph.Vertex;

import java.util.ArrayList;

/**
 * User: nkelkar
 * Date: 2/11/14
 * Time: 2:18 AM
 */
public class BoggleVertex extends Vertex<Character> {

    //protected ArrayList<BoggleVertex> outVertices = null;
    private boolean isVisited = false;
    private int vertexId = -1;   // unique identifier in the graph

    public boolean getVisiting() { return isVisited; }

    // to toggle isVisited flag
    public void setVisiting(boolean visitStatus) {
        this.isVisited = visitStatus;
    }

    public BoggleVertex(Character data) {
        super(data);
    }

    public BoggleVertex(Character data, int vId) {
        super(data);
        this.vertexId = vId;
    }

    /*
    // to add to the adjacency list of this vertex
    public void addOutVertex(BoggleVertex bv) {
        if(outVertices == null) {
            outVertices = new ArrayList<BoggleVertex>();
        }
        outVertices.add( bv );
    }
    */

    @Override
    public int hashCode() {
        int result = 157;   // arbitrary num > 0

        return 37*result + this.getData().hashCode() + vertexId;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof BoggleVertex) {
            if(this.compareTo((BoggleVertex)o) == 0)
                return this.vertexId == ((BoggleVertex)o).vertexId;
        }

        return false;
    }

    public int getVertexId() { return vertexId; }
}
