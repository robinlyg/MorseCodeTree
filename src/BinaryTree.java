import org.w3c.dom.Node;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class BinaryTree<E> implements Serializable  {
    protected Node<E> root;

    public BinaryTree() {
        //consturcts empty bianry tree
        root = null;

    }

    protected BinaryTree(Node<E> root) {
        //constructs a binary tree with the given node as the root
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        //constructs a binary tree with the given data at the root and the two given subtrees
       root = new Node<>(data);
       //  this(new Node<>((data)));
        if (leftTree != null) {
            root.left = leftTree.root;
        } /*else {
            root.left = null;
        }*/
        if (rightTree != null) {
            root.right = rightTree.root;
        } /*else {
            root.right = null;
        }*/
    }

    public BinaryTree<E> getLeftSubtree() {
        //retuns left subtree
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }

    }

    public BinaryTree<E> getRightSubtree() {
        //retuns right subtree
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else
            return null;
    }

    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public E getData() {
        //returns data in the root
        return root.data;
    }

    @Override
    public String toString() {
        //retuns a string representation of the tree
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        //return sb.toString();
        return sb.toString();
    }

    private void toString(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.data);
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }

    public void preOrderTraverse(BiConsumer<E, Integer> consumer) {
        //perfors a preorder traversal of the tree. Each node and its depth are passed to the consumer function
        preOrderTraverse(root, 1, consumer);

    }

    private void preOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth, consumer);
        }
    }

    public static BinaryTree<String > readBinaryTree(Scanner scan) {
        //constructs a bianry tree by reading its data using Scanner scan
        //read a line that represents info at the root
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
            //if not null recursively read the left child and right child
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            //return a tree consisting of the root and the 2 children
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    //insert inner class Node<E> here
    protected static class Node<E> implements Serializable {
        //datat fields
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        //constructor - construct a node with given data and no children
        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node()
        {}

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }


        //return a string rep of the node
        public String toString() {
            return data.toString();
        }
    }

}
