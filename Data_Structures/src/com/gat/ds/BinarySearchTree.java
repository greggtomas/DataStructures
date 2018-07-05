package com.gat.ds;

import java.util.Stack;
/*
 * Binary Search Tree
 * 
 */
public class BinarySearchTree {

	private Node rootNode;
	
	public BinarySearchTree(int value) {
		// create the root node
		rootNode = new Node(value);
	}
	
	/*
	 * Insert method is the public accessor 
	 * 
	 * @param - it is the value to be inserted into the tree
	 */
	public void insert(int value) {
		Node newNode = new Node(value);
		insertRecord(this.rootNode, newNode);
	}
	
	/*
	 * private method to actually find the location and 
	 * insert a new node with the value onto the tree.
	 * 
	 * @param node represents the current node
	 * @param newNode represents the new node
	 */
	private void insertRecord(Node node, Node newNode) {
		// find out if the new node's value is greater or less than
		// current node.
		if (newNode.getData() > node.getData()) {
			if (node.getRight() == null) {
				node.setRight(newNode);
			}else { // keep traversing the right side until there is a null
				insertRecord(node.getRight(), newNode);
			}
		}else { // must go on the left side of the current node.
			if(node.getLeft() == null) {
				node.setLeft(newNode);
			}else {
				insertRecord(node.getLeft(), newNode);
			}
		}
	}
	
	public void delete(int value) {
		deleteRecord(this.rootNode, value);
	}
	
	public void deleteRecord(Node currentNode, int value) {
		if (value < currentNode.getData()) {
			// value to delete is somewhere in the left side of the tree.
			if (currentNode.getLeft().getData() == value) {
				if(currentNode.getLeft().getLeft() == null &&
						currentNode.getLeft().getRight() == null) {
					currentNode.setLeft(null);
				}else if(currentNode.getLeft().getLeft() != null &&
						   currentNode.getLeft().getRight() != null) {
					// this takes care of the 3rd case:
					//		Node with 2 children
					Node oldRight = currentNode.getLeft().getRight();
					currentNode.setLeft(currentNode.getLeft().getLeft());
					currentNode.getLeft().setRight(oldRight);
				}else if(currentNode.getLeft().getLeft() != null) {
					// this takes care of the 2nd case: 
					//      Node with 1 child
					// it deletes the current node's left child by setting
					// it to point to the left child's child.
					currentNode.setLeft(currentNode.getLeft().getLeft());
				}else {
					currentNode.setLeft(currentNode.getLeft().getRight());
				}
			}else {
				deleteRecord(currentNode.getLeft(), value);
			}
		}else {
			// value to delete is somewhere in the right side of the tree.
			if (currentNode.getRight().getData() == value) {
				// The current node's child does not have any child nodes
				// so just delete the child node of the current node by
				// setting it to null
				if(currentNode.getRight().getRight() == null &&
						currentNode.getRight().getLeft() == null) {
					currentNode.setRight(null);
				}else if(currentNode.getRight().getLeft() != null &&
						   currentNode.getRight().getRight() != null) {
					// this takes care of the 3rd case:
					//		Node with 2 children
					Node oldRight = currentNode.getRight().getRight();
					currentNode.setRight(currentNode.getRight().getLeft());
					currentNode.getRight().setRight(oldRight);
				}else if(currentNode.getRight().getRight() != null) {
					currentNode.setRight(currentNode.getRight().getRight());
				}else {
					currentNode.setRight(currentNode.getRight().getLeft());
				}
			}else {
				deleteRecord(currentNode.getRight(), value);
			}
		}
		
	}
	
	public int find(int value) {
		return findKey(this.rootNode, value);
		
	}
	
	private int findKey(Node node, int value) {
		if (node == null) {
			return 0;
		}else if (node.getData() == value) {
			return node.getData();
		}else if (value < node.getData()) {
			return findKey(node.getLeft(), value);
		}else {
			return findKey(node.getRight(), value);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(10);
		bst.insert(15);
		bst.insert(14);
		bst.insert(8);
		bst.insert(9);
		bst.insert(7);
		bst.insert(13);
		bst.insert(17);
		bst.insert(16);
		bst.insert(18);
		bst.breadth();
//		System.out.println("");
//		bst.delete(9);
//		System.out.println("");
//		bst.breadth();
//		bst.insert(9);
//		System.out.println("");
//		bst.breadth();
//		bst.delete(17);
		//bst.delete(8);
		System.out.println("");
		bst.breadth();
		System.out.println("");
		bst.inOrder();
		System.out.println("");
		bst.preOrder();
		System.out.println();
		System.out.println("*** Post Order ***");
		bst.postOrder();
//		int foundValue = bst.find(14);
//		System.out.println();
//		if (foundValue > 0) {
//			System.out.println(foundValue+" has been found");
//		}else {
//			System.out.println(foundValue+" was not found");
//		}
		
	}
	
//	public static void main(String[] args) {
//		BinarySearchTree bst = new BinarySearchTree(5);
//		//    5
//		//      \
//		//      7
//		bst.insert(7);
//		//    5
//		//      \
//		//        7
//		//          \
//		//			  9
//		bst.insert(9);
//		//    5
//		//      \
//		//        7
//		//          \
//		//			  9
//		//           /
//		//          8
//		bst.insert(8);
//		//    5
//		//   /   \
//		//  2      7
//		//          \
//		//			  9
//		//           /
//		//          8
//		bst.insert(2);
//		//      5
//		//     /  \
//		//    2     7
//		//   /        \
//		//	1		    9
//		//             /
//		//            8
//		bst.insert(1);
//		//      5
//		//     /  \
//		//    2     7
//		//   / \      \
//		//	1	3       9
//		//             /
//		//            8
//		bst.insert(3);
//		//      5
//		//     /  \
//		//    2     7
//		//   / \   /   \
//		//	1	3 6      9
//		//             /
//		//            8
//		bst.insert(6);
//		bst.breadth();
//	}
	
	public void breadth() {
		if (this.rootNode == null) { 
			return;
		}
		System.out.print(this.rootNode.getData());
		System.out.print(",");
		printBreadth(this.rootNode);
	}
	
	private void printBreadth(Node node) {
		
	    if (node.getLeft() != null) {
	    	System.out.print(node.getLeft().getData());
	    	System.out.print(",");
	    }
	    if (node.getRight() != null){
	    	System.out.print(node.getRight().getData());
	    	System.out.print(",");
	    }
	    if (node.getLeft() != null) {
	    	printBreadth(node.getLeft());
	    }
	    if (node.getRight() != null) {
	    	printBreadth(node.getRight());
	    }
	}
	
	public void preOrder() {
		preOrder(this.rootNode);
		System.out.println();
		System.out.println("*************");
		preOrderNonRecursive(this.rootNode);
	}
	
	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.getData());
			System.out.print(" , ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	public void preOrderNonRecursive(Node root) {
		Stack s = new Stack();
		while(true) { // while for the stack
			while(root != null) { // loop throught the left sub-tree
				System.out.print(root.getData());
				System.out.print(" ");
				s.push(root);
				root = root.getLeft();
			}
			if(s.isEmpty()) {
				break;
			}
			root = (Node)s.pop();
			root = root.getRight();
		}
	}
	
	
	public void inOrder() {
		inOrder(this.rootNode);
	}
	
	private void inOrder(Node node) {
		if (node != null) {
			
			inOrder(node.getLeft());
			System.out.print(node.getData());
			System.out.print(" , ");
			inOrder(node.getRight());
		}
	}
	
	public void postOrder() {
		postOrder(this.rootNode);
		System.out.println();
		System.out.println("*************");
		System.out.println("non-recursive");
		postOrderNonRecursive(this.rootNode);
	}
	
	private void postOrder(Node node) {
		if(node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.print(node.getData());
			System.out.print(" , ");
		}
	}
	
	// TODO does not work
	private void postOrderNonRecursive(Node node) {
	
		Stack<Node> S = new Stack<Node>();
        
        // Check for empty tree
        if (node == null)
            return;
        S.push(node);
        Node prev = null;
        while (!S.isEmpty()) 
        {
            Node current = S.peek();
  
            /* go down the tree in search of a leaf an if so process it 
            and pop stack otherwise move down */
            if (prev == null || prev.getLeft() == current || 
                                        prev.getRight() == current) 
            {
                if (current.getLeft() != null)
                    S.push(current.getLeft());
                else if (current.getRight() != null)
                    S.push(current.getRight());
                else
                {
                    S.pop();
                    System.out.print(current.getData());
                    System.out.print(" , ");
                }
  
                /* go up the tree from left node, if the child is right 
                   push it onto stack otherwise process parent and pop 
                   stack */
            } 
            else if (current.getLeft() == prev) 
            {
                if (current.getRight() != null)
                    S.push(current.getRight());
                else
                {
                    S.pop();
                    System.out.println(current.getData());
                    System.out.print(" , ");
                }
                  
                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            } 
            else if (current.getRight() == prev) 
            {
                S.pop();
                System.out.print(current.getData());
                System.out.print(" , ");
            }
  
            prev = current;
        }

	}
}
