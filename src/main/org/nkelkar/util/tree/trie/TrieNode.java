package main.org.nkelkar.util.tree.trie;

import java.util.List;

/**
 * User: nkelkar
 * Date: 2/15/14
 * Time: 3:58 PM
 */
public class TrieNode<T extends Comparable<? super T>> implements Comparable<TrieNode<T>> {

    private T _data;    // data in this node
    private TrieNode<T>[] children;
    boolean isFullKey = false;  // TODO: implement better way to mark end-of-key

    public TrieNode() {
        _data = null;
        children = null;
    }

    public TrieNode(T _data) {
        this._data = _data;
        children = null;
    }

    public TrieNode(T _data, List<TrieNode<T>> children) {
        this._data = _data;
        setChildren(children);
    }

    public TrieNode<T> getChild(int idx) {
        return this.children[ idx ];
    }

    public void setChild(int idx, TrieNode<T> toSet) {
        if(idx < this.children.length)
            children[idx] = toSet;
    }

    public void setChildren(List<TrieNode<T>> children) {
        this.children = new TrieNode[ children.size() ];

        for(int i=0; i<children.size(); i++) {
            this.children[i] = children.get(i);
        }
    }

    public boolean hasChildren() {
        return !(children == null);
    }

    public void markAsKey() {
        isFullKey = true;
    }

    public boolean isFullKey() { return isFullKey; }
    public T getData() { return _data; }

    @Override
    public int compareTo(TrieNode<T> o) {
        return this._data.compareTo(o._data);  // compare data elements
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TrieNode) && (this.compareTo(((TrieNode<T>) o)) == 0);
    }
}
