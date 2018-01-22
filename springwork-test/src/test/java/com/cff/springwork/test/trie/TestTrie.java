package com.cff.springwork.test.trie;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Test;

public class TestTrie {

    @Test
    public void testContains(){
        PatriciaTrie<Double> t = new PatriciaTrie<>();

        t.put("我是我是我是我是我是", 44.0);
        t.put("我是", 440.0);

        System.out.println(t.prefixMap("我"));
        System.out.println(t.previousKey("b"));
    }


}
