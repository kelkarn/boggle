package test.org.nkelkar.boggle.helper;

import junit.framework.Assert;
import main.org.nkelkar.boggle.helper.BoggleGraph;
import main.org.nkelkar.boggle.helper.BoggleVertex;
import org.junit.Test;

import java.io.IOException;

/**
 * User: dell
 * Date: 2/14/14
 * Time: 8:22 PM
 */
public class BoggleGraphAdjListTest {

    @Test
    public void testBoggleGraphAdjList() throws IOException {
        /**
         * a b c
         * d e f
         * g h i
         */
        /*
        HashSet<String> vocab = new HashSet<String>();
        vocab.add("beg");
        vocab.add("bad");
        vocab.add("bead");
        vocab.add("head");
        */
        String vocabFilePath = "C:\\Users\\dell\\IdeaProjects\\boggle\\src\\test\\resources\\dictionary.txt";
        BoggleGraph bg = new BoggleGraph(new Character[][] {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}}, vocabFilePath);
        bg.printGraph();    // to view graph

        Assert.assertTrue(bg.containsEdge(new BoggleVertex('e', 4), new BoggleVertex('b', 1)));
        Assert.assertTrue(bg.containsEdge(new BoggleVertex('i', 8), new BoggleVertex('e', 4)));
        Assert.assertTrue(bg.containsEdge(new BoggleVertex('f', 5), new BoggleVertex('b', 1)));
        Assert.assertFalse(bg.containsEdge(new BoggleVertex('c', 2), new BoggleVertex('d', 3)));
        Assert.assertFalse(bg.containsEdge(new BoggleVertex('h', 7), new BoggleVertex('c', 2)));

        bg.getWords();  // get all boggle words
        bg.printResultSet();
    }
}
