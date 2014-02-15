package main.org.nkelkar.boggle.helper;

import main.org.nkelkar.util.graph.api.abs.AbstractGenericGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * User: dell
 * Date: 2/11/14
 * Time: 2:14 AM
 */
public class BoggleGraph extends AbstractGenericGraph<Character[]> {

    private int boardSize;  // #chars per board edge
    private final int EIGHT = 8;    // max number of nodes that can connect
    private final int THREE = 3;
    private Map<BoggleVertex, ArrayList<BoggleVertex>> adjList;
    private Set<String> vocabulary;
    private Set<String> resultSet;

    public BoggleGraph(Character[][] data, String vocabFilePath) throws IOException {
        // check for squareness first
        if(data.length != data[ 0 ].length) {
            throw new IllegalArgumentException("This Boggle solver requires a square sized board. " +
                                               "Board of size " + data.length + "x" + data[ 0 ].length +
                                               " was found.");
        }

        // check for nulls second
        checkForNulls( data );

        boardSize = data.length;    // set edge length of square
        // create an adjacency list map
        adjList = new HashMap<BoggleVertex, ArrayList<BoggleVertex>>();
        resultSet = new HashSet<String>();
        // now, create BoggleVertices with each vertex containing one character
        /**
         * We may have the following cases to handle (marked by an O):
         * O x x O x O
         * x x x x x x
         * x x x x x O
         * O x x x O x
         * x x x x x x
         * O x O x x O
         *
         * So, we have total 9 cases to handle
         */
        vocabulary = new HashSet<String>();
        buildVocab( vocabFilePath );
        buildGraph( data );
    }

    public void buildVocab(String vocabFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(vocabFilePath));
        String word;
        try {
            while((word = br.readLine()) != null) {

                word = word.toLowerCase();
                if(word.contains("/"))
                    word = word.split("/")[0];

                word = word.replace("'", "");
                word = word.replace("-", "");

                if(word.length() < THREE)
                    continue;   // usual boggle rule: length of word >= 3

                vocabulary.add( word );
            }
        }
        finally {
            br.close();
        }
    }

    @Override
    public void buildGraph(Character[][] data) {

        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data[i].length; j++) {
                // create data structure to
                // hold this new node
                BoggleVertex newVertex = new BoggleVertex(data[i][j], (boardSize*i) + (j%boardSize));
                ArrayList<BoggleVertex> adjNodes = new ArrayList<BoggleVertex>( EIGHT );

                if(i == 0 || i == boardSize - 1) {
                    if(j == 0 || j == boardSize - 1) {
                        int k = (i == 0)?i+1:i-1;
                        int l = (j == 0)?j+1:j-1;
                        adjNodes.add(new BoggleVertex(data[k][j], (boardSize*k + (j%boardSize))));
                        adjNodes.add(new BoggleVertex(data[i][l], (boardSize*i + (l%boardSize))));
                        adjNodes.add(new BoggleVertex(data[k][l], (boardSize*k + (l%boardSize))));
                    }
                    else {
                        int k = (i == 0)?i+1:i-1;
                        adjNodes.add(new BoggleVertex(data[k][j], (boardSize*k + (j%boardSize))));  // top/bottom
                        adjNodes.add(new BoggleVertex(data[i][j-1], (boardSize*i + ((j-1)%boardSize)))); // left
                        adjNodes.add(new BoggleVertex(data[i][j+1], (boardSize*i + ((j+1)%boardSize)))); // right
                        // handle diagonal elements
                        adjNodes.add(new BoggleVertex(data[k][j-1], (boardSize*k + ((j-1)%boardSize)))); // diag left
                        adjNodes.add(new BoggleVertex(data[k][j+1], (boardSize*k + ((j+1)%boardSize)))); // diag right
                    }
                }
                else if (j == 0 || j == boardSize - 1){
                    // don't need to handle 4 corner cases
                    // here. already done above
                    int l = (j == 0)?j+1:j-1;
                    adjNodes.add(new BoggleVertex(data[i][l], (boardSize*i + (l%boardSize)))); // front/back
                    adjNodes.add(new BoggleVertex(data[i-1][j], (boardSize*(i-1) + (j%boardSize)))); // top
                    adjNodes.add(new BoggleVertex(data[i+1][j], (boardSize*(i+1) + (j%boardSize)))); // bottom
                    // handle diagonal elements
                    adjNodes.add(new BoggleVertex(data[i-1][l], (boardSize*(i-1) + (l%boardSize)))); // diag top
                    adjNodes.add(new BoggleVertex(data[i+1][l], (boardSize*(i+1) + (l%boardSize)))); // diag bottom
                }
                else {
                    // case where each node has four neighbors
                    // handle all eight cases
                    adjNodes.add(new BoggleVertex(data[i][j-1], (boardSize*i + ((j-1)%boardSize)))); // back
                    adjNodes.add(new BoggleVertex(data[i][j+1], (boardSize*i + ((j+1)%boardSize)))); // front
                    adjNodes.add(new BoggleVertex(data[i-1][j], (boardSize*(i-1) + (j%boardSize)))); // top
                    adjNodes.add(new BoggleVertex(data[i+1][j], (boardSize*(i+1) + (j%boardSize)))); // bottom
                    // handle diagonal elements
                    adjNodes.add(new BoggleVertex(data[i-1][j-1], (boardSize*(i-1) + ((j-1)%boardSize)))); // diag top left
                    adjNodes.add(new BoggleVertex(data[i-1][j+1], (boardSize*(i-1) + ((j+1)%boardSize)))); // diag top right
                    adjNodes.add(new BoggleVertex(data[i+1][j-1], (boardSize*(i+1) + ((j-1)%boardSize)))); // diag bottom left
                    adjNodes.add(new BoggleVertex(data[i+1][j+1], (boardSize*(i+1) + ((j+1)%boardSize)))); // diag bottom right
                }
                adjList.put(newVertex, adjNodes);   // add to adjacency list
            }
        }
    }

    @Override
    public boolean checkForNulls(Character[][] data) {
        for(int i = 0; i< data.length; i++) {
            for(int j = 0; j < data[ i ].length; j++) {
                if(data[i][j] == null) {
                    throw new IllegalArgumentException("This Boggle solver cannot accept null input values. " +
                                                       "Null value found at position (" + i + ", " + j + ").");
                }
            }
        }
        return false;
    }

    // utility function to view graph
    // as adjacency list
    public void printGraph() {
        for(BoggleVertex v : adjList.keySet()) {
            System.out.print(v.getData() + ": ");
            ArrayList<BoggleVertex> adjNodes = adjList.get( v );
            for(BoggleVertex c : adjNodes) {
                System.out.print("{" + c.getData() + ", " + c.getVertexId() + "} | ");
            }
            System.out.println();   // next vertex
        }
    }

    public boolean containsEdge(BoggleVertex fromVertex, BoggleVertex toVertex) {
        if(!adjList.containsKey(fromVertex)) return false;

        for(BoggleVertex c : adjList.get(fromVertex)) {
            if(c.equals( toVertex )) return true;
        }

        return false;
    }

    public void getWords() {

        // for each vertex v, traverse it's adjacency
        // list, and keep building set of seen words
        Set<BoggleVertex> exploredVertices = new HashSet<BoggleVertex>();
        for(BoggleVertex v : adjList.keySet()) {
            exploredVertices.add( v );  // this vertex has already been seen
            String seen = "";   // create empty string
            seen = seen + v.getData();
            recursiveWordSearch(v, exploredVertices, seen);
            exploredVertices.clear();
        }
    }

    private void recursiveWordSearch(BoggleVertex v, Set<BoggleVertex> explored, String seenSoFar) {

        if(vocabulary.contains(seenSoFar)) {
            resultSet.add(seenSoFar);
        }

        for(BoggleVertex b : adjList.get( v )) {
            if (!explored.contains( b )) {  // if this node hasn't been seen
                explored.add( b );  // mark as seen
                seenSoFar = seenSoFar + b.getData();
                recursiveWordSearch(b, explored, seenSoFar); // proceed to next
                explored.remove( b );
                seenSoFar = seenSoFar.substring(0, seenSoFar.length() - 1); // remove appended character
            }
        }
    }

    public void printResultSet() {
        for (String aResultSet : resultSet) {
            System.out.println(aResultSet);
        }
    }
}
