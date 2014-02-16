package test.org.nkelkar.boggle.helper;

import junit.framework.Assert;
import main.org.nkelkar.boggle.helper.BoggleVocabTrie;
import org.junit.Test;

/**
 * User: dell
 * Date: 2/15/14
 * Time: 11:02 PM
 */
public class BoggleTrieTest {

    @Test
    public void testBoggleTrieCtor() {
        BoggleVocabTrie bvt = new BoggleVocabTrie();
        bvt.addKey("contain");
        bvt.addKey("contract");
        bvt.addKey("cost");
        bvt.addKey("conquer");

        Assert.assertTrue(bvt.isKey("contain"));
        Assert.assertTrue(bvt.isKey("conquer"));
        Assert.assertFalse(bvt.isKey("costs"));
        // Assert.assertFalse(bvt.isKey("contract"));
    }

    @Test
    public void testBoggleTriePrintAllKeys() {
        BoggleVocabTrie bvt = new BoggleVocabTrie();
        bvt.addKey("contain");
        bvt.addKey("contract");
        bvt.addKey("cost");
        bvt.addKey("conquer");

        bvt.printAllKeys();
    }

    @Test
    public void testBoggleTrieContainsPrefix() {
        BoggleVocabTrie bvt = new BoggleVocabTrie();
        bvt.addKey("contain");
        bvt.addKey("contract");
        bvt.addKey("cost");
        bvt.addKey("conquer");

        Assert.assertFalse(bvt.containsPrefix("col"));
        Assert.assertTrue(bvt.containsPrefix("con"));
        Assert.assertTrue(bvt.containsPrefix("cos"));
    }
}
