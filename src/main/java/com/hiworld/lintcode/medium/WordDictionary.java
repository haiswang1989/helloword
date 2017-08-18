package com.hiworld.lintcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：单词的添加与查找 
 * 描述：
 * 设计一个包含下面两个操作的数据结构：addWord(word), search(word)
 * addWord(word)会在数据结构中添加一个单词。而search(word)则支持普通的单词查询或是只包含.和a-z的简易正则表达式的查询。
 * 
 * 注意事项：
 * 你可以假设所有的单词都只包含小写字母 a-z。
 * 
 * 样例：
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad")  // return false
 * search("bad")  // return true
 * search(".ad")  // return true
 * search("b..")  // return true
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月16日 下午2:48:37
 */
public class WordDictionary {
    
    Trie trie = new Trie(); 
    
    public void addWord(String word) {
        // Write your code here
        addWordToTrie(trie, word, 0, word.length());
    }
    
    private void addWordToTrie(Trie trie, String word, int targetIndex, int length) {
        if(targetIndex < length) {
            char currCh = word.charAt(targetIndex);
            Trie targetTrie = trie.setChild(currCh);
            addWordToTrie(targetTrie, word, targetIndex+1, length);
        } else if(targetIndex == length) {
            trie.setEnd();
        } 
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        List<Trie> tries = new ArrayList<>();
        tries.add(trie);
        
        for (char c : word.toCharArray()) {
            List<Trie> allTrie = new ArrayList<>();
            for (Trie trie : tries) {
                List<Trie> findTries = trie.getTries(c);
                allTrie.addAll(findTries);
            }
            
            if(allTrie.size() == 0) {
                return false;
            } else {
                tries = allTrie;
            }
        }
        
        for (Trie trie : tries) {
            if(trie.hasEnd()) {
                return true;
            }
        }
        
        
        return false;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        
        boolean ret = wordDictionary.search("pad");
        System.out.println("ret : " + ret);
        ret = wordDictionary.search("bad");
        System.out.println("ret : " + ret);
        ret = wordDictionary.search(".ad");
        System.out.println("ret : " + ret);
        ret = wordDictionary.search("b..");
        System.out.println("ret : " + ret);
    }
}

/**
 * 查找树(单词树)
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年8月16日 下午3:44:35
 */
class Trie {
    
    Trie[] tries = null;

    public Trie() {
        tries = new Trie[27];
        initNull(tries);
    }
    
    /**
     * 
     * @param tries
     */
    private void initNull(Trie[] tries) {
        int length = tries.length;
        for(int i=0; i<length; ++i) {
            tries[i] = null;
        }
    }
    
    /**
     * 
     * @param c
     * @param trie
     */
    public Trie setChild(char c) {
        Trie targetTrie = tries[c-'a'];
        if(null==targetTrie) {
            targetTrie = new Trie();
            tries[c-'a'] = targetTrie;
        }
        
        return targetTrie;
    }
    
    /**
     * 
     * @param c
     * @return
     */
    public List<Trie> getTries(char c) {
        if(c == '.') {
            return getTrieList();
        } else {
            Trie targetTrie = getTrie(c);
            List<Trie> retList = new ArrayList<>();
            if(null!=targetTrie) {
                retList.add(targetTrie);
            }
            return retList;
        }
    }
    
    /**
     * 
     * @param c
     * @return
     */
    public Trie getTrie(char c) {
        return tries[c-'a'];
    }
    
    /**
     * 
     * @return
     */
    public List<Trie> getTrieList() {
        List<Trie> retList = new ArrayList<>();
        for (Trie trie : tries) {
            if(null!=trie) {
                retList.add(trie);
            }
        }
        return retList;
    }
    
    /**
     * 设置一个结束的结点
     */
    public void setEnd() {
        tries[26] = new Trie();
    }
    
    /**
     * 是否有结尾
     * @return
     */
    public boolean hasEnd() {
        return null!=tries[26];
    }
}


