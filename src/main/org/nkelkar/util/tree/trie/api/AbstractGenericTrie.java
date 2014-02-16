package main.org.nkelkar.util.tree.trie.api;

import main.org.nkelkar.util.tree.trie.TrieNode;

/**
 * User: nkelkar
 * Date: 2/15/14
 * Time: 4:19 PM
 */
public abstract class AbstractGenericTrie<T extends Comparable<? super T>> {

    protected TrieNode<T> root;   // all tries have a root

    public abstract void printAllKeys();   // print dictionary : 'key' is defined as
                                            // a single logical unit of interest
    public abstract boolean isKey(T[] candidate);
    protected abstract void addKey(T[] key);
}
