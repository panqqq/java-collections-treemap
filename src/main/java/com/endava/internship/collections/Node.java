package com.endava.internship.collections;

class Node{
    Student student; //key
    Integer age; //value
    Node left;
    Node right;

    Node(Student value, Integer age) {
        student = value;
        //this.age = student.getAges();
        this.age = age;
        right = null;
        left = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "student=" + student +
                ", age=" + age +
                '}';
    }
}
