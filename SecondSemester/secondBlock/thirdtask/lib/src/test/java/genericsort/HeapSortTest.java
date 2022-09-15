package genericsort;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class HeapSortTest {

    public class User implements Comparable<User> {

        private String name;
        private Integer age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public int compareTo(User o) {
            int result = this.name.compareTo(o.name);

            if (result == 0) {
                result = this.age.compareTo(o.age);
            }
            return result;
        }
    }

    @Test
    public void sort_IllegalState_ShouldThrowException() {
        Character[] arr = new Character[5];

        IllegalStateException thrown =
                Assertions.assertThrows(IllegalStateException.class, () -> {
                    HeapSort.sort(arr);
                });
    }

    @Test
    public void sort_CharArray_ShouldSortArrayInLexicographicOrder() {
        Character[] arr = {'v', 'l', 'a', 'd', 'i', 's', 'l', 'a', 'v'};

        HeapSort.sort(arr);

        assertEquals('a', arr[0]);
        assertEquals('a', arr[1]);
        assertEquals('d', arr[2]);
        assertEquals('i', arr[3]);
        assertEquals('l', arr[4]);
        assertEquals('l', arr[5]);
        assertEquals('s', arr[6]);
        assertEquals('v', arr[7]);
        assertEquals('v', arr[8]);
    }

    @Test
    public void sort_StringArray_ShouldSortArrayInLexicographicOrder() {
        String[] arr = {"ssss", "kkk", "aaaz", "aaab", "nnnn", "c"};

        HeapSort.sort(arr);

        assertEquals("aaab", arr[0]);
        assertEquals("aaaz", arr[1]);
        assertEquals("c", arr[2]);
        assertEquals("kkk", arr[3]);
        assertEquals("nnnn", arr[4]);
        assertEquals("ssss", arr[5]);
    }

    @Test
    public void sort_IntegerArray_ShouldSortArrayInAscendingOrder() {
        Integer[] arr = {9999, 21, 0, 56, -123, 3};

        HeapSort.sort(arr);

        assertEquals(-123, arr[0]);
        assertEquals(0, arr[1]);
        assertEquals(3, arr[2]);
        assertEquals(21, arr[3]);
        assertEquals(56, arr[4]);
        assertEquals(9999, arr[5]);
    }

    @Test
    public void sort_DoubleArray_ShouldSortArrayInAscendingOrder() {
        Double[] arr = {1100.34, 0.0, 234.99, -80.01, 234.98};

        HeapSort.sort(arr);

        assertEquals(-80.01, arr[0]);
        assertEquals(0.0, arr[1]);
        assertEquals(234.98, arr[2]);
        assertEquals(234.99, arr[3]);
        assertEquals(1100.34, arr[4]);
    }

    @Test
    public void sort_UsersArray_ShouldSortArray() {
        User[] arr = new User[5];

        var user1 = new User("Masha", 23);
        var user2 = new User("Anna", 45);
        var user3 = new User("Vlad", 18);
        var user4 = new User("Anna", 16);
        var user5 = new User("Kira", 99);

        arr[0] = user1;
        arr[1] = user2;
        arr[2] = user3;
        arr[3] = user4;
        arr[4] = user5;

        HeapSort.sort(arr);

        assertEquals(user4, arr[0]);
        assertEquals(user2, arr[1]);
        assertEquals(user5, arr[2]);
        assertEquals(user1, arr[3]);
        assertEquals(user3, arr[4]);
    }
}
