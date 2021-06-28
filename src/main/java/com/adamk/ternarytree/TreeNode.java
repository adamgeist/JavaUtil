package com.adamk.ternarytree;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

    private final T value;

    private TreeNode<T> smallerChild;
    private TreeNode<T> largerChild;
    private TreeNode<T> equalChild;

    public TreeNode(T value) {
        this.value = value;
    }

    public TreeNode<T> getEqualChild() {
        return equalChild;
    }

    public void setEqualChild(TreeNode<T> equalChild) {
        this.equalChild = equalChild;
    }

    public TreeNode<T> getLargerChild() {
        return largerChild;
    }

    public void setLargerChild(TreeNode<T> largerChild) {
        this.largerChild = largerChild;
    }

    public TreeNode<T> getSmallerChild() {
        return smallerChild;
    }

    public void setSmallerChild(TreeNode<T> smallerChild) {
        this.smallerChild = smallerChild;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int compareTo(TreeNode<T> o) {
        return value.compareTo(o.getValue());
    }
}
