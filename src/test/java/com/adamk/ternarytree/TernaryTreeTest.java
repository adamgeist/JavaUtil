package com.adamk.ternarytree;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TernaryTreeTest {

    /**
     * Test which adds the items from the coding question to a tree and prints them
     */
    @Test
    public void testAddToTree() {
        TernaryTree<Integer> testTree = new TernaryTree<>();
        List<Integer> list = Arrays.asList(5, 4, 9, 5, 7, 2, 2);
        testTree.addToTree(list);

        System.out.println(testTree);
    }

}
