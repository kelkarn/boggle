package main.org.nkelkar.util.tree.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dell
 * Date: 2/15/14
 * Time: 3:58 PM
 */
public class TrieNode<T extends Comparable<? super T>> implements Comparable<TrieNode<T>> {

    private T _data;    // data in this node
    private List<TrieNode<T>> children;

    public TrieNode() {
        _data = null;
        children = null;
    }

    public TrieNode(T _data) {
        _data = _data;
        children = new ArrayList<TrieNode<T>>();
    }

    public TrieNode(T _data, List<TrieNode<T>> children) {
        this._data = _data;
        this.children = children;
    }

    @Override
    public int compareTo(TrieNode<T> o) {
        return this._data.compareTo(o._data);  // compare data elements
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TrieNode) && (this.compareTo(((TrieNode) o)) == 0);
    }
}
