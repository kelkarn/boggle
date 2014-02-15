package test.org.nkelkar.boggle.helper;

import junit.framework.Assert;
import main.org.nkelkar.boggle.helper.BoggleVertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: dell
 * Date: 2/11/14
 * Time: 3:49 AM
 */
public class BoggleVertexMapTest {

    @Test
    public void testBoggleVertexMap() {
        Map<BoggleVertex, ArrayList<BoggleVertex>> testMap = new HashMap<BoggleVertex, ArrayList<BoggleVertex>>();

        testMap.put(new BoggleVertex('a', 1), new ArrayList<BoggleVertex>());
        testMap.put(new BoggleVertex('b', 2), new ArrayList<BoggleVertex>());
        testMap.put(new BoggleVertex('c', 3), new ArrayList<BoggleVertex>());
        testMap.put(new BoggleVertex('a', 4), new ArrayList<BoggleVertex>());

        Assert.assertTrue(testMap.containsKey(new BoggleVertex('a', 1)));
        Assert.assertTrue(testMap.containsKey(new BoggleVertex('a', 4)));
        Assert.assertFalse(testMap.containsKey(new BoggleVertex('c', 2)));
    }
}
