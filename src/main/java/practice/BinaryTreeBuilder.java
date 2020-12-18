package practice;

import java.util.*;

public class BinaryTreeBuilder {

    private Node root;

    List<Node> nodes = new ArrayList<>();

    public void build(int[] elements) {
        for (int i = 0; i < elements.length; i++) {
            int currentNodeValue = elements[i];

            Node currentNode;
            if (findNodeValue(currentNodeValue)) {
                currentNode = nodes.get(i);
            } else {
                currentNode = new Node(currentNodeValue);
                nodes.add(currentNode);
            }

            Node leftChild;
            int leftChildIndex = computeLeftChildIndex(i);
            if (leftChildIndex < elements.length) {
                leftChild = new Node(elements[leftChildIndex]);
                currentNode.setLeftChild(leftChild);
                nodes.add(leftChild);
            }

            Node rightChild;
            int rightChildIndex = computeRightChildIndex(i);
            if (rightChildIndex < elements.length) {
                rightChild = new Node(elements[rightChildIndex]);
                currentNode.setRightChild(rightChild);
                nodes.add(rightChild);
            }
        }
        root = nodes.get(0);
    }

    private boolean findNodeValue(int value) {
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private int computeLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int computeRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    public void dfsTraversalRecursive(Node node) {
        if (node != null) {
            System.out.println("Node: " + node.getValue());
            dfsTraversalRecursive(node.getLeftChild());
            dfsTraversalRecursive(node.getRightChild());
        }
    }

    public void dfsTraversalNonRecursive(Node node) {
        Stack<Node> stack = new Stack<>();

        stack.add(node);
        node.setIsVisited(true);
        node.print();
        while (!stack.isEmpty()) {
            Node current = stack.peek();
            if (hasUnVisitedLeftChild(current)) {
                Node lc = current.getLeftChild();
                lc.setIsVisited(true);
                stack.add(lc);
                lc.print();
            } else {
                current = stack.pop();
                if (hasUnvisitedRightChild(current)) {
                    Node rc = current.getRightChild();
                    rc.setIsVisited(true);
                    stack.add(rc);
                    rc.print();
                }
            }
        }
    }

    private boolean hasUnVisitedLeftChild(Node node) {
        return (node.getLeftChild() != null && !node.getLeftChild().isVisited());
    }

    private boolean hasUnvisitedRightChild(Node node) {
        return (node.getRightChild() != null && !node.getRightChild().isVisited());
    }

    public void bfsTraversal(Node node) {
        Queue<Node> queue = new PriorityQueue<>(1, new NodeComparator());

        queue.add(node);
        while (!queue.isEmpty()) {
            Node poppedNode = queue.remove();
            System.out.println("Node: " + poppedNode.getValue());

            if (poppedNode.getLeftChild() != null) {
                queue.add(poppedNode.getLeftChild());
            }

            if (poppedNode.getRightChild() != null) {
                queue.add(poppedNode.getRightChild());
            }
        }
    }

    public int countNodes(Node root) {
        int leftCount = 0;
        if (root.getLeftChild() != null) {
            leftCount += countNodes(root.getLeftChild());
        }

        int rightCount = 0;
        if (root.getRightChild() != null) {
            rightCount += countNodes(root.getRightChild());
        }
        return leftCount + rightCount + 1;
    }

    public int countLeafNodes(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 1;
        } else {
            int leftCount = 0;
            if (node.getLeftChild() != null) {
                leftCount += countLeafNodes(node.getLeftChild());
            }

            int rightCount = 0;
            if (node.getRightChild() != null) {
                rightCount += countLeafNodes(node.getRightChild());
            }
            return leftCount + rightCount;
        }
    }

    public boolean hasChildren(Node node) {
        return node.getLeftChild() != null && node.getRightChild() != null;
    }

    public static void main(String[] args) {
        int[] array = new int[7];

        for (int i = 0; i < 7; i++) {
            array[i] = i;
        }

        BinaryTreeBuilder binaryTreeBuilder = new BinaryTreeBuilder();
        binaryTreeBuilder.build(array);

        System.out.println("******************************");
        System.out.println("***Depth First Traversal NR***");
        System.out.println("******************************");
        binaryTreeBuilder.dfsTraversalNonRecursive(binaryTreeBuilder.root);


        System.out.println("******************************");
        System.out.println("***Depth First Traversal R ***");
        System.out.println("******************************");
        binaryTreeBuilder.dfsTraversalRecursive(binaryTreeBuilder.root);

        System.out.println("******************************");
        System.out.println("****Breadth First Traversal***");
        System.out.println("******************************");
        binaryTreeBuilder.bfsTraversal(binaryTreeBuilder.root);

        System.out.println("******************************");
        System.out.println("**********Node Count**********");
        System.out.println("******************************");
        System.out.println("Total Number of nodes: " +
                binaryTreeBuilder.countNodes(binaryTreeBuilder.root));

        System.out.println("******************************");
        System.out.println("**********Leaf Count**********");
        System.out.println("******************************");
        System.out.println("Total Number of leaf nodes: " +
                binaryTreeBuilder.countLeafNodes(binaryTreeBuilder.root));

    }
}