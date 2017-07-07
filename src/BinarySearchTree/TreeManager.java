package BinarySearchTree;

import sun.reflect.generics.tree.Tree;

import java.util.Optional;

/**
 * Created by Robert-PC on 7/5/2017.
 */
public class TreeManager {

    private TreeNode root;


    public TreeManager(){

    }

    public TreeManager(TreeNode root){
        this.root = root;
    }


    public TreeNode getRoot() {
        return root;
    }


    public boolean isEmpty(){
        return this.root == null;
    }

    public TreeNode insert(TreeNode treeNode, TreeNode newNode){
        if(isEmpty()){
            this.root = newNode;
            return root;
        }

        if(treeNode == null){
            // inserting new node
            return newNode;
        }

        if(newNode.getData() <= treeNode.getData()){
            //searching in left side of the tree
            treeNode.setLeft(insert(treeNode.getLeft(),newNode));
        }
        else{
            treeNode.setRight(insert(treeNode.getRight(),newNode));
        }

        return treeNode;

    }

    public boolean search(int data){
        return searchData(root, data);
    }

    private boolean searchData(TreeNode treeNode, int data){
        if(treeNode == null){
            return false;

        }

        if(treeNode.getData() == data){
            return true;

        }

        System.out.println("Current search for " + data + " : " + treeNode.getData() );
        if(treeNode.getData() > data)
            return searchData(treeNode.getLeft(), data);
        else
            return searchData(treeNode.getRight(),data);

    }

    public TreeNode delete(TreeNode current, int data){
        if(current == null){
            System.out.println("base case");
            return current;
        }

        if(data < current.getData()){
            System.out.println("searching data left");
            delete(current.getLeft(), data);

        }else
            if(data > current.getData()){
                System.out.println("searching data right");
                delete(current.getRight(), data);
            }
            else{
                if(current.getLeft() == null && current.getRight() == null){
                    System.out.println("leaf");
                    current = null;
                }
                else if(current.getRight() == null){
                    current = current.getLeft();

                }
                else if(current.getLeft() == null){
                    current = current.getRight();
                }
                else{
                    TreeNode replacementNode = this.getMinDataNode(this.root.getRight());

                    System.out.println("min  :" + replacementNode.getData());

                    current.setData(replacementNode.getData());

                    current.setRight(delete(current.getRight(), replacementNode.getData()));

                    System.out.println("current " + current.getData());
                }

            }

            return current;

    }

    private TreeNode getMinDataNode(TreeNode current){
        if(current.getLeft() == null)
            return current;
         return getMinDataNode(current.getLeft());
    }


}
