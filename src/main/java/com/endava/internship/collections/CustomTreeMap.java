package com.endava.internship.collections;

import java.util.Collection;
import java.util.LinkedHashSet;

public class CustomTreeMap{
    private Node root;
    private LinkedHashSet<Student> setForKeys;
    private Collection<Integer> collectionForValues;
    private Integer valueInside;
    private int size = 0;
    private boolean changedSize = true;

    //Add new element
    private Node addRecursive(Node current, Student student, Integer age) {
        if (current == null) {
            valueInside = age;
            return new Node(student, age);
        }

        if (student.compareTo(current.student) < 0) {
            current.left = addRecursive(current.left, student, age);
        } else if (student.compareTo(current.student) > 0) {
            current.right = addRecursive(current.right, student, age);
        } else {
            // student already exists
            valueInside = current.age;
            current.age = age;
            return current;
        }

        return current;
    }

    public void add(Student key, Integer age) {
        root = addRecursive(root, key, age);
    }

    public Integer put(Student key, Integer age) {
        add(key,age);
        Integer val = valueInside;
        valueInside = null;
        return val;
    }

    //Traverse Tree
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

    //Contains an student(key)
    private boolean containsNodeRecursive(Node current, Student student) {
        if (current == null) {
            return false;
        }
        if (student == current.student) {
            return true;
        }
        if(student.compareTo(current.student) < 0) {
            return containsNodeRecursive(current.left, student);
        } else {
            return containsNodeRecursive(current.right, student);
        }
        /*return student < current.student
                ? containsNodeRecursive(current.left, student)
                : containsNodeRecursive(current.right, student);*/
    }

    public boolean containsNode(Student student) {
        return containsNodeRecursive(root, student);
    }

    //Delete a node
    private Node deleteRecursive(Node current, Student student) {
        if (current == null) {
            return null;
        }

        if (student == current.student) {
            // Node to delete found

            //1. The node has no children
            if (current.left == null && current.right == null) {
                return null;
            }

            //2. The node has one child
            // ??
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }


            //3. The node has two children
            Student smallestValue = findSmallestValue(current.right);
            current.student = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if(student.compareTo(current.student) < 0) {
            current.left = deleteRecursive(current.left, student);
        } else {
            current.right = deleteRecursive(current.right, student);
        }
        return current;


    }


    public void delete(Student student) {
        root = deleteRecursive(root, student);
    }
    //Smallest Student
    private Student findSmallestValue(Node root) {
        return root.left == null ? root.student : findSmallestValue(root.left);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean traversePreOrderForContainsValue(Node node, Integer value) {
       //Not working
        if (node != null) {
            if (node.student.getAges().equals(value)) {
                return true;
            }
            traversePreOrderForContainsValue(node.left, value);
            traversePreOrderForContainsValue(node.right, value);


        }
        return false;
    }

    public boolean containsValue(Integer value) {
        return traversePreOrderForContainsValue(root, value);
    }

    private void traverseInOrderForKeys(Node node) {
        if (node != null) {
            traverseInOrderForKeys(node.left);
            setForKeys.add(node.student);
            traverseInOrderForKeys(node.right);
        }
    }

    public LinkedHashSet<Student> traverseForKeys() {
        setForKeys = new LinkedHashSet<>();
        traverseInOrderForKeys(root);
        return setForKeys;
    }


    private void traverseInOrderForValues(Node node) {
        if (node != null) {
            traverseInOrderForValues(node.left);
            collectionForValues.add(node.age);
            traverseInOrderForValues(node.right);
        }
    }

    public Collection<Integer> traverseForValues() {
        collectionForValues = new LinkedHashSet<>();
        traverseInOrderForValues(root);
        return collectionForValues;
    }

    private void traverseForSize(Node node) {
        if (node != null) {
            size += 1;
            traverseForSize(node.left);
            traverseForSize(node.right);
        }
    }

    public int size() {
        if (changedSize) {
            size = 0;
            traverseForSize(root);
        }
        return size;
    }

}
