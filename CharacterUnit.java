package labotary;

import java.util.*;

public class CharacterUnit {
    private int nextId = 0;
    private final Map<Character, Integer> stoi = new HashMap<>();
    private final Map<Integer, Character> itos = new HashMap<>();
    public int[] encodeChunk(String text) {
        Set<Character> characterSet = new TreeSet<>();
        for(char c : text.toCharArray()) {
            characterSet.add(c);
        }
        for(char c : characterSet) {
            if(!stoi.containsKey(c)) {
                stoi.put(c, nextId);
                itos.put(nextId, c);
                nextId++;
            }
        }
        int[] out = new int[text.length()];
        for(int i = 0; i < text.length(); i++) {
            out[i] = stoi.get(text.charAt(i));
        }
        return out;
    }

    public int vocaSize() {
        return stoi.size();
    }

    public int[] getIds() {
        int[] k = new int[itos.size()];
        for(int i = 0; i < itos.size(); i++) {
            k[i] = i;
        }
        return k;
    }
}
