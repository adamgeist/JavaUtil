package com.adamk.ternarytree;

import java.util.List;

public class TernaryTree<T extends Comparable<T>> {

    private TreeNode<T> root = null;

    /**
     * Add a list of comparable objects to the tree
     *
     * @param list List of object to be added
     */
    public void addToTree(List<T> list) {
        for (T t : list) {
            addToTree(t);
        }
    }

    /**
     * Add an object to the tree
     *
     * @param obj The object to be added to the tree
     */
    public void addToTree(T obj) {
        TreeNode<T> newNode = new TreeNode<>(obj);

        if (root == null) {
            root = newNode;
        } else {
            addToTree(root, newNode);
        }
    }

    /**
     * Recursive method for adding to the tree
     *
     * @param curNode Current node we are comparing to
     * @param newNode The node to be added
     */
    private void addToTree(TreeNode<T> curNode, TreeNode<T> newNode) {
        int diff = curNode.compareTo(newNode);

        if (diff < 0) {
            if (curNode.getLargerChild() != null) {
                addToTree(curNode.getLargerChild(), newNode);
            } else {
                curNode.setLargerChild(newNode);
            }
        } else if (diff > 0) {
            if (curNode.getSmallerChild() != null) {
                addToTree(curNode.getSmallerChild(), newNode);
            } else {
                curNode.setSmallerChild(newNode);
            }
        } else {
            //We must be equal
            if (curNode.getEqualChild() != null) {
                addToTree(curNode.getEqualChild(), newNode);
            } else {
                curNode.setEqualChild(newNode);
            }
        }
    }

    /**
     * Get the string representation of this tree
     *
     * @return String representing the tree
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Depth\tNode:Parent Values\n");
        sb.append("-----\t------------------\n");
        return printTreeDepthFirst(null, root, sb, 0).toString();
    }

    /**
     * Print the tree depth first
     *
     * @param parent Used for referencing the parent node in recursion, can be left null
     * @param node   The current node we are processing, start at root
     * @param sb     A StringBuilder for appending the output string
     * @param depth  The current depth in the tree
     * @return Returns a multi-line representation of the tree in depth first order
     */
    private StringBuilder printTreeDepthFirst(TreeNode<T> parent, TreeNode<T> node, StringBuilder sb, int depth) {
        depth++;
        sb.append(depth);
        for (int i = 0; i < depth + 1; i++) {
            sb.append("\t");
        }
        sb.append(String.format("%s:%s\n", node.getValue(), parent != null ? parent.getValue() : ""));

        if (node.getSmallerChild() != null) {
            printTreeDepthFirst(node, node.getSmallerChild(), sb, depth);
        }
        if (node.getEqualChild() != null) {
            printTreeDepthFirst(node, node.getEqualChild(), sb, depth);
        }
        if (node.getLargerChild() != null) {
            printTreeDepthFirst(node, node.getLargerChild(), sb, depth);
        }
        return sb;
    }


}
