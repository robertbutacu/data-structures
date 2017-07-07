package BinarySearchTree;

import Queue.QueueArrayImpl;
import sun.reflect.generics.tree.Tree;

import java.util.Optional;

/**
 * Created by Robert-PC on 7/6/2017.
 */
public class TreeOperations {

    TreeNode root;

    public TreeOperations(TreeNode root){
        this.root = root;
    }

    public TreeOperations(){

    }

    public Integer getHeight(){

        return getHeight(root);
    }
    private Integer getHeight(TreeNode treeNode){
        if(treeNode == null)
            return -1;

        int leftHeight = getHeight(treeNode.getLeft());
        int rightHeight = getHeight(treeNode.getRight());

        return ((leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1);

    }

    public Optional<String> findMax(){
        TreeNode currentNode = root;

        if(currentNode == null){
            return Optional.empty();
        }

        while(currentNode.getRight() != null){
            currentNode = currentNode.getRight();
        }

        return Optional.of(Integer.toString(currentNode.getData()));

    }

    public void breadthFirstSearch(){
        QueueArrayImpl<TreeNode> queueArray = new QueueArrayImpl<>(100);
        queueArray.enqueue(root);
        BFS(queueArray);

    }

    private void BFS(QueueArrayImpl<TreeNode> queueArray){
        //finished parsing BST
        if(queueArray.isEmpty())
            return;
        TreeNode current = queueArray.dequeue().get();

        System.out.println("Current element : " + current.getData());

        if(current.getLeft() != null)
            queueArray.enqueue(current.getLeft());

        if(current.getRight() != null)
            queueArray.enqueue(current.getRight());

        BFS(queueArray);
    }

    public void InorderSearch(){

    }

    public void PreorderSearch(){

    }

    public void PostOrderSearch(){

    }

    public Optional<String> findMin(){
        TreeNode currentNode = root;

        if(currentNode == null){
            return Optional.empty();
        }

        while(currentNode.getLeft() != null)
            currentNode = currentNode.getLeft();

        return Optional.of(Integer.toString(currentNode.getData()));
    }

    public boolean isBinarySearchTree(){
        return isBST(root);
    }

    private boolean isBST(TreeNode current){
        if(current == null)
            return true;

        //in order to be a BST, any element from the left sub-tree must be lower or equal than the current node's data
        //and any element from the right sub-tree must be greater
        return (current.getData() >= Integer.parseInt(new TreeOperations(current.getLeft()).findMax().orElse(String.valueOf(Integer.MIN_VALUE)))
                && current.getData() < Integer.parseInt(new TreeOperations(current.getRight()).findMin().orElse(String.valueOf(Integer.MAX_VALUE)))
                && isBST(current.getLeft())
                && isBST(current.getRight()));
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
