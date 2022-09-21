package com.endava.internship.collections;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class StudentMap implements Map<Student, Integer> {
    private CustomTreeMap customTreeMap = new CustomTreeMap();


    //Constructs a new, empty tree map, using the natural ordering of its keys.
    public StudentMap() {

    }
    //Constructs a new tree map containing the same mappings as the given map, ordered according to the natural ordering of its keys.
    public StudentMap(Map<? extends Student, ? extends Integer> map) {
        putAll(map);
    }
    //Constructs a new, empty tree map, ordered according to the given comparator.

    //Constructs a new tree map containing the same mappings and using the same ordering as the specified sorted map.

    @Override
    public int size() {
        return customTreeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return customTreeMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        validateNullPointerException(o);
        validateClassCastException(o);
        Student key = (Student) o;
        return customTreeMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object o) {
        Integer value = (Integer) o;
        return customTreeMap.containsValue(value);
    }

    @Override
    public Integer get(Object o) {
        validateNullPointerException(o);
        validateClassCastException(o);
        Student key = (Student) o;
        return customTreeMap.get(key);
    }

    @Override
    public Integer put(Student student, Integer integer) {
        validateNullPointerException(student);
        validateClassCastException(student);
        return customTreeMap.addToNode(student, integer);
    }

    @Override
    public Integer remove(Object o) {
        validateNullPointerException(o);
        validateClassCastException(o);
        Student student = (Student) o;
        return customTreeMap.delete(student);
    }

    @Override
    public void putAll(Map<? extends Student, ? extends Integer> map) {
        if(map == null) {
          throw new NullPointerException();
        }
        for (Entry<? extends Student, ? extends Integer> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        customTreeMap.clearRoot();
    }

    @Override
    public Set<Student> keySet() {
        return customTreeMap.keySet();
    }

    @Override
    public Collection<Integer> values() {
        return customTreeMap.values();
    }

    @Override
    public Set<Entry<Student, Integer>> entrySet() {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    public void traverse() {
        customTreeMap.traverse();
    }

    private static void validateNullPointerException(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
    }

    private static void validateClassCastException(Object o) {
        if(!(o instanceof Student)) {
            throw new ClassCastException();
        }
    }

    public static class NullPointerException extends RuntimeException {

    }

    public static class ClassCastException extends RuntimeException {

    }


}
