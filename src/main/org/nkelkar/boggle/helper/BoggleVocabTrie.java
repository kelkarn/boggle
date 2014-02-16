package main.org.nkelkar.boggle.helper;

import main.org.nkelkar.util.tree.trie.TrieNode;
import main.org.nkelkar.util.tree.trie.api.AbstractGenericTrie;

import java.util.ArrayList;
import java.util.List;

/**
 * User: nkelkar
 * Date: 2/15/14
 * Time: 7:33 PM
 */
public class BoggleVocabTrie extends AbstractGenericTrie<Character> {

    private final Character A = 'a';
    private final int ALPHABET_SIZE = 26;

    public BoggleVocabTrie() {
        List<TrieNode<Character>> alphabet = new ArrayList<TrieNode<Character>>();
        // create alphabet, and add to children of root
        for(int i=(int) A; i< A + ALPHABET_SIZE; i++) {
            //Character input = A + (Character)i;
            alphabet.add(new TrieNode<Character>((char)i)); // down cast to char
        }
        root = new TrieNode<Character>('$', alphabet); // first node contains "S"
    }

    @Override
    public void printAllKeys() {
        recursivePrintKeys(root, "");
    }

    private void recursivePrintKeys(TrieNode<Character> root, String keySoFar) {
        if(root.isFullKey()) {
            System.out.println(keySoFar);
            return;
        }

        if(root.getData()!=null && root.hasChildren()) {
            for(int i=0; i<ALPHABET_SIZE; i++) {
                if(root.getChild( i ) != null) {
                    recursivePrintKeys(root.getChild(i), keySoFar + root.getChild(i).getData());
                }
            }
        }
    }

    public boolean isKey(String candidate) {
        Character[] input = new Character[ candidate.length() ];
        for(int i=0; i<candidate.length(); i++) {
            input[i] = candidate.charAt( i );
        }
        return isKey( input );
    }

    @Override
    public boolean isKey(Character[] candidate) {

        TrieNode<Character> currNode = root;
        int len = candidate.length;
        int idx = 0;
        for(int i=0; i<len; i++) {
            idx = candidate[i] - A;
            if(!currNode.hasChildren())
                return false;

            if(currNode.getChild( idx ) == null)
                return false;
            currNode = currNode.getChild( idx );
        }
        return currNode.isFullKey();
    }

    public void addKey(String key) {
        Character[] input = new Character[ key.length() ];
        for(int i=0; i<key.length(); i++) {
            input[i] = key.charAt( i );
        }
        addKey( input );
    }

    @Override
    protected void addKey(Character[] key) {
        int len = key.length;
        int it = 0;
        TrieNode<Character> currNode = root;   // start with root

        while(it < len) {

            int currCharIdx = key[it] - A; // get index of curr char
            if(!currNode.hasChildren()) {  // this node is being visited
                                           // for the first time ever
                List<TrieNode<Character>> alphabet = new ArrayList<TrieNode<Character>>();
                for(int i=(int) A; i< A + ALPHABET_SIZE; i++) {
                    if(i == (int)key[it])
                        alphabet.add(new TrieNode<Character>((char)i)); // down cast to char
                    else
                        alphabet.add(new TrieNode<Character>(null)); // down cast to char
                }
                currNode.setChildren( alphabet );
                //currNode = currNode.getChild( currCharIdx );// now move forward
            }
            else if(currNode.getChild(currCharIdx) == null || currNode.getChild(currCharIdx).getData() == null) {
                currNode.setChild(currCharIdx, new TrieNode<Character>(key[it])); // this node was visited before
                                                                                  // but not with the current char
            }
            //else {  // this node was visited before
                currNode = currNode.getChild( currCharIdx );
            //}
            it++;
        }
        // we now need to mark end of string
        currNode.markAsKey();
    }
}
