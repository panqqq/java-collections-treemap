package com.endava.internship.collections;

import java.util.*;

public class CustomTreeMap{
    private Node root;
    private int size = 0;

    class Node {
        private Student key;
        private Integer value;
        private Node left;
        private Node right;

        public Node(Student key, Integer value) {
            size += 1;
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    //Add new Node
    public Integer addToNode(Student key, Integer value) {
        if (root == null) {
            root = new Node(key, value);
            return null;
        }

        Node current = root;
        Node parent;
        Integer previousValue;
        while (true) {
            parent = current;
            if (current.key.compareTo(key) == 0) {
                //Replace values
                previousValue = current.value;
                current.value = value;
                return previousValue;
            } else if (current.key.compareTo(key) > 0) {
                current = current.left;
                if (current == null) {
                    parent.left = new Node(key, value);
                    return null;
                }
            } else if (current.key.compareTo(key) < 0) {
                current = current.right;
                if (current == null) {
                    parent.right = new Node(key, value);
                    return null;
                }
            } else  //Should this be maintained??
                return null;
        }
    }



    public boolean containsKey(Student key) {
        Node current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return true;
            }
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else if (current.key.compareTo(key) < 0) {
                current = current.right;
            }
        }
        return false;
    }

    private Node deleteRecursive(Node current, Student key) {
        if (current == null) {
            return null;
        }

        if (key == current.key) {
            // Node to delete found
            //Case 1: the node is a leaf node
            if (current.left == null && current.right == null) {
                return null;
            }

            //Case 2: the node has one child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            //Case 3: the node has two children
            Student smallestKey = findSmallestKey(current.right);
            current.key = smallestKey;
            current.right = deleteRecursive(current.right, smallestKey);
            return current;
        }
        if (current.key.compareTo(key) > 0) {
            current.left = deleteRecursive(current.left, key);
            return current;
        }
        current.right = deleteRecursive(current.right, key);
        return current;
    }

    public Integer delete(Student key) {
        Node node = getNode(key);
        if(node != null ) {
            size -= 1;
            root = deleteRecursive(root, key);
            return node.value;
        }
        return null;
    }

    private Student findSmallestKey(Node root) {
        return root.left == null ? root.key : findSmallestKey(root.left);
    }

    public Integer get(Student key) {
        Node current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return current.value;
            }
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else if (current.key.compareTo(key) < 0) {
                current = current.right;
            }
        }
        return null;
    }

    public Node getNode(Student key) {
        Node current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                return current;
            }
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            } else if (current.key.compareTo(key) < 0) {
                current = current.right;
            }
        }
        return null;
    }

    private List<Node> getArray(Node node) {
        ArrayList<Node> nodes = new ArrayList<>();
        if(node==null) {
            return nodes;
        }
        if(node.left != null) {
            nodes.addAll(getArray(node.left));
        }
        if(node.right != null ) {
            nodes.addAll(getArray(node.right));
        }
        nodes.add(node);
        return nodes;

    }

    private List<Node> array() {
        return getArray(root);
    }

    public boolean containsValue(Integer value) {
        List<CustomTreeMap.Node> list = array();
        for (CustomTreeMap.Node node : list) {
            if(node.value == value) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clearRoot() {
        root = null;
        size = 0;
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(" " + node);
            traverseInOrder(node.right);
        }
    }

    public void traverse() {
        traverseInOrder(root);
    }

    private Set<Student> traverseInOrderForKeys(Node node) {
        Set<Student> set= new LinkedHashSet<>();
        if(node==null) {
            return set;
        }
        set.add(node.key);
        if (node != null) {
            set.addAll(traverseInOrderForKeys(node.left));
            set.addAll(traverseInOrderForKeys(node.right));
        }

        return set;
    }


    public Set<Student> keySet() {
        return traverseInOrderForKeys(root);
    }

    private Collection<Integer> traverseInOrderForValues(Node node) {
        Collection<Integer> coll= new ArrayList<>();
        if(node==null) {
            return coll;
        }
        coll.add(node.value);
        if (node != null) {
            coll.addAll(traverseInOrderForValues(node.left));
            coll.addAll(traverseInOrderForValues(node.right));
        }

        return coll;
    }


    public Collection<Integer> values() {
        return traverseInOrderForValues(root);
    }


}