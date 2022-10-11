import java.util.*;

public class Trie {
    private class TrieNode {
        @SuppressWarnings("unused")
        private char val;
        private HashMap<Character,TrieNode> children;
        private boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            children = new HashMap<>();
            isWord = false;
        }

        public void addChild(char child){
            children.put(child, new TrieNode(child));
        }

        public TrieNode getChild(char child) {
            if(!children.keySet().contains(child)){
                return null;
            }
            return children.get(child);
        }

        public boolean containsChild(char child) {
            return children.keySet().contains(child);
        }

    }
    private TrieNode root;
    private TrieNode curr;

    public Trie() {
        root  = new TrieNode(' ');
        curr = root;
    }

    public void insert(String s){
        char ch;
        curr = root;
        for (int i = 0; i <s.length(); i++) {
            ch = s.charAt(i);

            if (!curr.containsChild(ch)) {
                curr.addChild(ch);
            }
            curr = curr.getChild(ch);
        }
        curr.isWord = true;

    }

    public List<Integer> getSuffixesStartIndices(String s) {
        List<Integer> indexes = new LinkedList<>();
        char ch;
        curr = root;
        for (int i = 0; i < s.length(); i++) {
             ch = s.charAt(i);

            if(!curr.containsChild(ch))
                return indexes;

            curr = curr.getChild(ch);
            if(curr.isWord)
                indexes.add(i+1);
        }
        return indexes;
    }
}
