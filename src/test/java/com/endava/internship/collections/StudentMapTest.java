package com.endava.internship.collections;

import org.assertj.core.api.AssertFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



public class StudentMapTest {

    private StudentMap stMap;
    private Student newStudent;
    private Student otherStudent;

    @BeforeEach
    void setUp() {
        newStudent = new Student("Linda Rice", LocalDate.of(1985,10,15),"Details newStudent");
        otherStudent = new Student("Ryan Brown", LocalDate.of(1978,3,4),"Details other");
        stMap = new StudentMap();
    }
    //TODO size
    @Test
    public void afterOnePut_StudentMapSizeShouldBeOne() {
        //Act
        stMap.put(newStudent,newStudent.getAges());

        //Assert
        assertEquals(1, stMap.size());
    }

    @Test
    public void afterPutAndRemoveMapSizeShouldBeZero() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertEquals(0, stMap.size());
    }

    @Test
    public void afterTwoPutAndOneRemove_MapSizeShouldBeOne() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.put(otherStudent, otherStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertEquals(1, stMap.size());
    }

    @Test
    public void whenClearMap_MapSizeShouldBeZero() {
        //Act
        stMap.put(newStudent,newStudent.getAges());
        stMap.put(otherStudent,otherStudent.getAges());
        stMap.clear();

        //Assert
        assertEquals(0, stMap.size());
    }
    //TODO isEmpty()
    @Test
    public void newlyCreatedMap_ShouldBeEmpty() {
        assertTrue(stMap.isEmpty());
        assertEquals(0, stMap.size());
    }

    @Test
    public void afterPutAndRemoveMapShouldBeEmpty() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertEquals(0, stMap.size());
        assertTrue(stMap.isEmpty());
    }

    @Test
    public void whenOneIsPutOneIsRemoved_MapShouldBeEmpty() {
        //Act
        stMap.put(newStudent,newStudent.getAges());

        //Assert
        assertEquals(newStudent.getAges(), stMap.remove(newStudent));
    }

    @Test
    public void whenClearMap_MapShouldBeEmpty() {
        //Act
        stMap.put(newStudent,newStudent.getAges());
        stMap.put(otherStudent,otherStudent.getAges());
        stMap.clear();

        //Assert
        assertTrue(stMap.isEmpty());
    }


    //TODO ContainsKey
    @DisplayName("Add students add check if map contains key")
    @ParameterizedTest
    @MethodSource("studentList")
    public void afterPut_MapContainsKey(Student student) {
        //Act
        stMap.put(student, student.getAges());

        //Assert
        assertTrue(stMap.containsKey(student));

    }

    @DisplayName("Add students add check if map not contain a not inserted key")
    @ParameterizedTest
    @MethodSource("studentList")
    public void mapNotContainsKey(Student student) {
        //Act
        stMap.put(student, student.getAges());

        //Assert
        assertFalse(stMap.containsKey(newStudent));
    }

    @Test
    public void WhenPutAndRemoveAStudent_ContainsKeyShouldBeFalse() {
        //Act
        stMap.put(newStudent,newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertFalse(stMap.containsKey(newStudent));
    }

    @Test
    public void whenCheckNullAsContainKeys_ThrowsNullPointerException() {
        assertThrows(StudentMap.NullPointerException.class, () -> stMap.containsKey(null));
    }

    @Test
    public void whenCheckKeyOfNotAnInstanceOfStudent_ThrowsClassCastException() {
        String name = "john";
        assertThrows(StudentMap.ClassCastException.class, () -> stMap.containsKey(name));
    }

    //TODO containsValue
    @DisplayName("Add students add check if map contains value")
    @ParameterizedTest
    @MethodSource("studentList")
    public void afterPut_MapContainsValue(Student student) {
        //Act
        stMap.put(student, student.getAges());

        //Assert
        assertTrue(stMap.containsValue((Integer) student.getAges()));
    }

    @DisplayName("Add students add check if map not contain a not inserted value")
    @ParameterizedTest
    @MethodSource("studentList")
    public void mapNotContainsValue(Student student) {
        //Act
        stMap.put(student, student.getAges());

        //Assert
        assertFalse(stMap.containsValue(newStudent.getAges()));

    }

    @Test
    public void WhenPutAndRemoveAStudent_ContainsValueShouldBeFalse() {
        //Act
        stMap.put(newStudent,newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertFalse(stMap.containsValue(newStudent.getAges()));
    }
    private static List<Student> studentList() {
        Student s1 = new Student("John Doe", LocalDate.of(2001,2,15),"Details");
        Student s2 = new Student("Jim Norton", LocalDate.of(1990,4,1),"Details");
        Student s3 = new Student("Mary Long", LocalDate.of(1995,12,25),"Details");
        return Arrays.asList(s1,s2,s3);
    }

    //TODO GET
    @Test
    public void checkValueOfAddedStudent() {
        //Act
        stMap.put(newStudent,newStudent.getAges());

        //Assert
        assertEquals(newStudent.getAges(), stMap.get(newStudent));
    }

    @ParameterizedTest
    @MethodSource("studentList")
    public void shouldReturnNullIfKeyNotExists(Student student) {
        //Act
        stMap.put(student,student.getAges());
        //Assert
        assertNull(stMap.get(newStudent));
    }

    @Test
    public void WhenGetANullObject_ThrowsNullPointerException() {
        assertThrows(StudentMap.NullPointerException.class, () -> stMap.get(null));
    }

    @Test
    public void passAKeyThatIsNotStudent_ThrowsClassCastException() {
        String parameter = "Name";
        Boolean b = false;
        Double d = 4.3d;

        assertAll(
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.get(parameter)),
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.get(b)),
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.get(d))
        );
    }

    //TODO PUT
    @Test
    public void whenPutANewStudent_MethodReturnsNull() {
        assertNull(stMap.put(newStudent, newStudent.getAges()));
    }

    @Test
    public void whenPutStudent_MapContainsStudent() {
        //Act
        stMap.put(newStudent, newStudent.getAges());

        //Assert
        assertTrue(stMap.containsKey(newStudent));
    }
    @Test
    public void whenPutStudent_MapGetStudent() {
        //Act
        stMap.put(newStudent, newStudent.getAges());

        //Assert
        assertEquals(newStudent.getAges(), stMap.get(newStudent));
    }

    @Test
    public void afterOnePut_MapSizeShouldBeOne() {
        //Act
        stMap.put(newStudent,newStudent.getAges());

        //Assert
        assertEquals(1, stMap.size());
        assertFalse(stMap.isEmpty());

    }

    @Test
    public void whenPutTwoTimesSameKeyShouldReturnPreviousValue() {
        stMap.put(newStudent, newStudent.getAges());
        assertEquals(newStudent.getAges(), stMap.put(newStudent, 80));

    }
    @Test
    public void whenPutAStudent_EmptyShouldBeFalse() {
        //Act
        stMap.put(newStudent, newStudent.getAges());

        //Assert
        assertFalse(stMap.isEmpty());

    }

    @Test
    public void WhenPutANullKey_ThrowsNullPointerException() {
        assertThrows(StudentMap.NullPointerException.class, () -> stMap.put(null,30));
    }

    //TODO remove
    @Test
    public void whenRemoveAKeyReturnValue() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        //Assert
        assertEquals(newStudent.getAges(), stMap.remove(newStudent));
    }

    @ParameterizedTest
    @MethodSource("studentList")
    public void whenRemoveAnUnmappedKeyReturnNull(Student student) {
        //Act
        stMap.put(student,student.getAges());

        //Assert
        assertNull(stMap.remove(newStudent));
    }

    @Test
    public void whenPutAndRemoveAStudent_SizeShouldBeZero() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertEquals(0, stMap.size());
    }

    @Test
    public void whenPutAndRemoveAStudent_MapShouldBeEmpty() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.remove(newStudent);

        //Assert
        assertTrue(stMap.isEmpty());
    }
    @Test
    public void WhenRemoveANullKey_ThrowsNullPointerException() {
        assertThrows(StudentMap.NullPointerException.class, () -> stMap.remove(null));
    }

    @Test
    public void WhenPassedKeyForRemoveIsNotAStudent_ThrowsClassCastException() {
        String parameter = "Name";
        Boolean b = false;
        Double d = 4.3d;

        assertAll(
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.remove(parameter)),
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.remove(b)),
                () -> assertThrows(StudentMap.ClassCastException.class, () -> stMap.remove(d))
        );
    }

    //TODO putAll
    @Test
    public void afterPutAll_ObjectsShouldBeAdded() {
        //Arrange
        Student s1 = new Student("John",LocalDate.of(2003,4,7),"Details");
        Student s2 = new Student("Mike",LocalDate.of(1995,4,7),"Details");
        Student s3 = new Student("Ron",LocalDate.of(2007,4,7),"Details");
        LinkedHashMap<Student,Integer> map = new LinkedHashMap<>();

        //Act
        map.put(s1,s1.getAges());
        map.put(s2,s2.getAges());
        map.put(s3,s3.getAges());
        stMap.putAll(map);

        //Assert
        assertAll(
                () -> assertTrue(stMap.containsKey(s1)),
                () -> assertTrue(stMap.containsKey(s2)),
                () -> assertTrue(stMap.containsKey(s3)),
                () -> assertEquals(s1.getAges(), stMap.get(s1)),
                () -> assertEquals(s2.getAges(), stMap.get(s2)),
                () -> assertEquals(s3.getAges(), stMap.get(s3))
        );
    }

    @Test
    public void afterPutThreeStudents_SizeShouldBeThree() {
        //Arrange
        Student s1 = new Student("John",LocalDate.of(2003,4,7),"Details");
        Student s2 = new Student("Mike",LocalDate.of(1995,4,7),"Details");
        Student s3 = new Student("Ron",LocalDate.of(2007,4,7),"Details");
        LinkedHashMap<Student,Integer> map = new LinkedHashMap<>();

        //Act
        map.put(s1,s1.getAges());
        map.put(s2,s2.getAges());
        map.put(s3,s3.getAges());
        stMap.putAll(map);

        //Assert
        assertEquals(3, stMap.size());
    }

    @Test
    public void nullSentAsParameterForPutAll_ThrowsNullPointerException() {
        assertThrows(StudentMap.NullPointerException.class, () -> stMap.putAll(null));
    }

    @Test
    public void specifiedKeyFromMapIsNull_ThrowsNullPointerException() {
        //Arrange
        LinkedHashMap<Student,Integer> map = new LinkedHashMap<>();

        //Act
        map.put(null,30);
        map.put(null,25);

        assertThrows(StudentMap.NullPointerException.class, () -> stMap.putAll(map));
    }

    //TODO clear()
    @Test
    public void whenClear_SizeShouldBeZero() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.clear();
        //Assert
        assertEquals(0, stMap.size());
        assertTrue(stMap.isEmpty());
        assertTrue(stMap.keySet().isEmpty());
        assertTrue(stMap.values().isEmpty());
    }

    @Test
    public void whenClear_MapShouldBeEmpty() {
        //Act
        stMap.put(newStudent, newStudent.getAges());
        stMap.clear();
        //Assert
        assertTrue(stMap.isEmpty());
    }

    //TODO keySet()
    @Test
    public void whenPutTwoStudentsInMapSizeOfKeySetShouldBeTwo() {
        //Arrange
        Student s1 = new Student("John", LocalDate.of(1999,3,4),"Details");
        Student s2 = new Student("Moore", LocalDate.of(1949,3,4),"Details");

        //Act
        stMap.put(s1,s1.getAges());
        stMap.put(s2,s2.getAges());

        //Assert
        assertEquals(2, stMap.keySet().size());
    }

    @Test
    public void putTwoStudentsAndCheckKeySet() {
        //Arrange
        Student s1 = new Student("John", LocalDate.of(1999,3,4),"Details");
        Student s2 = new Student("Moore", LocalDate.of(1949,3,4),"Details");

        //Act
        stMap.put(s1,s1.getAges());
        stMap.put(s2,s2.getAges());

        //Assert
        assertTrue(stMap.keySet().contains(s1));
        assertTrue(stMap.keySet().contains(s2));
    }

    //TODO values()
    @Test
    public void whenPutTwoStudentsInMapSizeOfValuesCollectionShouldBeTwo() {
        //Arrange
        Student s1 = new Student("John", LocalDate.of(1999,3,4),"Details");
        Student s2 = new Student("Moore", LocalDate.of(1949,3,4),"Details");

        //Act
        stMap.put(s1,s1.getAges());
        stMap.put(s2,s2.getAges());

        //Assert
        assertEquals(2, stMap.values().size());
    }

    @Test
    public void putTwoStudentsAndCheckValuesContains() {
        //Arrange
        Student s1 = new Student("John", LocalDate.of(1999,3,4),"Details");
        Student s2 = new Student("Moore", LocalDate.of(1949,3,4),"Details");

        //Act
        stMap.put(s1,s1.getAges());
        stMap.put(s2,s2.getAges());

        //Assert
        assertTrue(stMap.values().contains(s1.getAges()));
        assertTrue(stMap.values().contains(s2.getAges()));
    }


}
