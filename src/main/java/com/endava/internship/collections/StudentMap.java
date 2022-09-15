package com.endava.internship.collections;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StudentMap implements Map<Student, Integer> {
    private CustomTreeMap customTreeMap = new CustomTreeMap();



    public StudentMap() {

    }


    @Override
    public int size() {
        return customTreeMap.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        // - Done
        return customTreeMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        //TODO
        Student student = (Student) o;
        return customTreeMap.containsNode(student);
    }

    @Override
    public boolean containsValue(Object o) {
        //TODO
        Integer value = (Integer) o;
        //!!
        return false;
    }

    @Override
    public Integer get(Object o) {
        //TODO
        return null;
    }

    @Override
    public Integer put(Student student, Integer integer) {
        //TODO
        return customTreeMap.put(student,integer);
    }

    @Override
    public Integer remove(Object o) {
        //TODO
        Student student = (Student) o;

        return null;
    }

    @Override
    public void putAll(Map<? extends Student, ? extends Integer> map) {
        //TODO
    }

    @Override
    public void clear() {
        //TODO
        customTreeMap = new CustomTreeMap();
    }

    @Override
    public Set<Student> keySet() {
        //TODO
        LinkedHashSet<Student> set = customTreeMap.traverseForKeys();
        return set;
    }

    @Override
    public Collection<Integer> values() {
        //TODO
        Collection<Integer> set = customTreeMap.traverseForValues();
        return set;
    }

    @Override
    public Set<Entry<Student, Integer>> entrySet() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    public void traverse() {
        customTreeMap.traverse();
    }
}

