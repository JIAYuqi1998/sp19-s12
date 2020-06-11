import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MyTrieSet implements TrieSet61B{
    private Node root = new Node('s', false);

    private class Node {
        char val;
        boolean isEnd;
        TreeMap<Character, Node> map = new TreeMap<>();
        Node (char c, boolean end) {
            isEnd = end;
            val = c;
        }
    }
    @Override
    public void clear() {
        root.map = new TreeMap<>();
    }

    @Override
    public boolean contains(String key) {
        Node ptr = root;
        int l = key.length();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!ptr.map.containsKey(c)) {
                return false;
            } else {
                ptr = ptr.map.get(c);
                if (i == key.length() - 1) break;
            }
        }
        return ptr.isEnd;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isEnd = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List returnList = new ArrayList();
        Node ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!ptr.map.containsKey(c)) {
                return null;
            } else {
                ptr = ptr.map.get(c);
                if (i == prefix.length() - 1) break;
            }
        }
        prefixHelper(ptr, returnList, prefix);
        return returnList;
    }
    private void prefixHelper(Node remaining, List<String> stringPrefix, String s) {
        if (remaining.isEnd) {
            stringPrefix.add(s);
        }
        for (Map.Entry<Character, Node> entry : remaining.map.entrySet()) {
            prefixHelper(entry.getValue(), stringPrefix, s + entry.getKey());
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
