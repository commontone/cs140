package lab09;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.Comparator;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTreeTester {
    List<Integer> list;
    BinarySearchTree<Integer> bst;

    @Before
    public void setup() {
        list = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 0, -1, 2, 3}));
        bst = new BinarySearchTree<Integer>(Comparator.comparingInt(i -> i));
        list.stream().forEach(i -> bst.insert(i));
    }

    @Test
    public void insertFound() {
        list.stream().forEach(i -> assertNotNull(bst.find(i)));
    }

    @Test
    public void othersNotFound() {
        assertNull(bst.find(6));
        assertNull(bst.find(-3));
        assertNull(bst.find(-4));
    }

    @Test
    public void elemsFound() {
        bst.getElems().stream().forEach(i -> assertNotNull(bst.find(i)));
    }

    @Test
    public void elemsWereAdded() {
    	assertEquals(7, bst.getElems().size());
        bst.getElems().stream().forEach(i -> assertNotNull(list.contains(i)));
    }

    @Test
    public void elemsCorrect() {
    	List<Integer> l = bst.getElems();
    	list.stream().forEach(i -> assertTrue(l.contains(i)));
    }
}