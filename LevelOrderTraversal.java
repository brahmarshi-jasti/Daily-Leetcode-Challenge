import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class LevelOrderTraversal {

    static class Node {
        int data;
        Node left;
        Node right;

        // Constructor to initialize the node with a value
        Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    static List<Integer> levelOrder(Node root) {
        // Base case: if root is null, return an empty list
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> arr = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        // Add the root node to the queue
        q.add(root);

        // Iterate while the queue is not empty
        while (!q.isEmpty()) {
            // Get the front element of the queue
            Node temp = q.poll();
            arr.add(temp.data); // Append node's data to the list

            // If left child exists, add it to the queue
            if (temp.left != null) {
                q.add(temp.left);
            }

            // If right child exists, add it to the queue
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return arr;
    }

    static void printList(List<Integer> arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        // Getting level-order traversal
        List<Integer> result = levelOrder(root);

        // Printing the level-order traversal result
        System.out.print("Level-order traversal: ");
        printList(result);
    }
}
