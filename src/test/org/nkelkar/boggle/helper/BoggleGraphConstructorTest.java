package test.org.nkelkar.boggle.helper;

import main.org.nkelkar.boggle.helper.BoggleGraph;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

/**
 * User: dell
 * Date: 2/14/14
 * Time: 9:25 AM
 */
public class BoggleGraphConstructorTest {

    @Test
    public void testBoggleCtor() throws IOException {
        /**
         * a b c
         * d e f
         * g h i
         */
        String vocabFilePath = "/c/Users/dell/IdeaProjects/boggle/src/test/resources/dictionary.txt";
        BoggleGraph bg = new BoggleGraph(new Character[][] {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}},
                                         vocabFilePath);
        bg.printGraph();
    }
}
