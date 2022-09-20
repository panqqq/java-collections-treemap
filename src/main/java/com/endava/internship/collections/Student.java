package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
public class Student implements Comparable<Student>//TODO consider implementing any interfaces necessary for your collection
{//implement Comparable, compareTo
    private String name;
    private LocalDate dateOfBirth;
    private String details;
    private Integer age;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
        age = setAges(dateOfBirth);
    }

    @Override
    public int compareTo(Student student) {
        if(getName().equals(student.getName())) {
            return dateOfBirth.compareTo(student.dateOfBirth);
        }
        return getName().compareTo(student.getName());
    }


    public String getName() { return name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getDetails() { return details; }

    public Integer getAges() { return age; }

    private Integer setAges(LocalDate dateOfBirth) {
        Integer ages = LocalDate.now().getYear() - dateOfBirth.getYear();
        LocalDate dob = dateOfBirth.withYear(2022);
        if(LocalDate.now().isBefore(dob)) {
            ages = ages - 1;
        }

        return ages;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", details='" + details + '\'' +
                //", age=" + age +
                //", hashName=" + name.hashCode() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(dateOfBirth, student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    /*
    TODO consider overriding any methods for this object to function properly within a collection:
        1. A student is considered unique by a combination of their name and dateOfBirth
        2. Student names are sorted alphabetically, if two students have the same name, then the older one is
        placed before the younger student in an ordered student list.
    */
}