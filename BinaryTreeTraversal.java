                            
import java.util.ArrayList;
import java.util.List;

// Node structure for the binary tree
class Node {
    int data;
    Node left;
    Node right;

    // Constructor to initialize
    // the node with a value
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}






public class BinaryTreeTraversal {
    // Function to perform postorder
    // traversal recursively
    static void postorder(Node root, List<Integer> arr){
        // Base case: if root is null, return
        if (root == null) {
            return;
        }
        
    

        // Traverse left subtree
        postorder(root.left, arr);
        // Traverse right subtree
        postorder(root.right, arr);
        // Visit the node
        // (append node's data to the array)
        arr.add(root.data);
    }

    // Function to get the postorder
    // traversal of a binary tree
    static List<Integer> postOrder(Node root){
        // Create a list to
        // store the traversal result

        List<Integer> arr = new ArrayList<>();
        postorder(root, arr);

        
        return arr;
    }



    static List<Integer> postOrderNew(Node root) {
        
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        
        result.addAll(postOrderNew(root.left));
        result.addAll(postOrderNew(root.right));

        result.add(root.data)

        return result;

    }
    

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        // Getting postorder traversal
        List<Integer> result = postOrder(root);

        // Printing the postorder
        // traversal result
        System.out.print("Postorder traversal: ");
        printList(result);
    }


    


}
                            
                        