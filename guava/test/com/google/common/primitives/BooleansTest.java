package com.google.common.primitives;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class BooleansTest {

    @Test
    public void testTrueFirst() {
        Comparator<Boolean> comparator = Booleans.trueFirst();
        List<Boolean> actual = new ArrayList<Boolean>(){{
            add(false);
            add(true);
            add(false);
        }};
        List<Boolean> expected = new ArrayList<Boolean>(){{
            add(true);
            add(false);
            add(false);
        }};
        actual.sort(comparator);
        assertEquals(expected, actual);
        assertEquals("Booleans.trueFirst()", comparator.toString());
    }

    @Test
    public void testFalseFirst() {
        Comparator<Boolean> comparator = Booleans.falseFirst();
        List<Boolean> actual = new ArrayList<Boolean>(){{
            add(false);
            add(true);
            add(false);
        }};
        List<Boolean> expected = new ArrayList<Boolean>(){{
            add(false);
            add(false);
            add(true);
        }};
        actual.sort(comparator);
        assertEquals(expected, actual);
        assertEquals("Booleans.falseFirst()", comparator.toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(1231, Booleans.hashCode(true));
        assertEquals(1237, Booleans.hashCode(false));
    }

    @Test
    public void testCompare() {
        assertEquals(0, Booleans.compare(true, true));
        assertEquals(0, Booleans.compare(false, false));
        assertEquals(1, Booleans.compare(true, false));
        assertEquals(-1, Booleans.compare(false, true));
    }

    @Test
    public void testContains() {
        boolean[] list = new boolean[] {
                false, true, false
        };
        assertTrue(Booleans.contains(list, true));
        assertTrue(Booleans.contains(list, false));
        boolean[] list2 = new boolean[] {
                false, false
        };
        assertFalse(Booleans.contains(list2, true));
        assertTrue(Booleans.contains(list2, false));
        boolean[] list3 = new boolean[] {
                true, true
        };
        assertTrue(Booleans.contains(list3, true));
        assertFalse(Booleans.contains(list3, false));
    }

    @Test
    public void testIndexOf() {
        boolean[] list = new boolean[] {
                false, true, false
        };
        assertEquals(1, Booleans.indexOf(list, true));
        assertEquals(0, Booleans.indexOf(list, false));
        boolean[] list2 = new boolean[] {
                false, false
        };
        assertEquals(-1, Booleans.indexOf(list2, true));
        assertEquals(0, Booleans.indexOf(list2, false));
        assertEquals(0, Booleans.indexOf(list, new boolean[]{}));
        assertEquals(1, Booleans.indexOf(list, new boolean[]{ true, false }));
        assertEquals(-1, Booleans.indexOf(list, new boolean[]{ false, false }));
    }

    @Test
    public void testLastIndexOf() {
        boolean[] list = new boolean[] {
                false, true, false
        };
        assertEquals(1, Booleans.lastIndexOf(list, true));
        assertEquals(2, Booleans.lastIndexOf(list, false));
        boolean[] list2 = new boolean[] {
                false, false
        };
        assertEquals(-1, Booleans.lastIndexOf(list2, true));
        assertEquals(1, Booleans.lastIndexOf(list2, false));
    }

    @Test
    public void testConcat() {
        boolean[] list = new boolean[] {
                false, true, false
        };
        boolean[] list2 = new boolean[] {
                false, false
        };
        assertEquals(5, Booleans.concat(list, list2).length);
    }

    @Test
    public void testEnsureCapacity() {
        boolean[] list = new boolean[] {
                false, true, false
        };
        boolean[] res = Booleans.ensureCapacity(list, 5, 3);
        assertEquals(8, res.length);
        res = Booleans.ensureCapacity(list, 2, 3);
        assertSame(list, res);
        try {
            res = Booleans.ensureCapacity(list, -1, 3);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            res = Booleans.ensureCapacity(list, 1, -3);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testJoin() {
        assertEquals("false,true,false", Booleans.join(",", false, true, false));
        assertEquals("", Booleans.join(","));
    }

    @Test
    public void testLexicographicalComparator() {
        Comparator<boolean[]> comparator = Booleans.lexicographicalComparator();
        assertTrue(comparator.compare(new boolean[]{}, new boolean[]{false}) < 0);
        assertTrue(comparator.compare(new boolean[]{false}, new boolean[]{false, true}) < 0);
        assertTrue(comparator.compare(new boolean[]{false, true}, new boolean[]{true}) < 0);
        assertEquals("Booleans.lexicographicalComparator()", comparator.toString());
    }

    @Test
    public void testToArray() {
        List<Boolean> list = new ArrayList<Boolean>(){{
            add(false);
            add(true);
            add(false);
        }};
        boolean[] res = Booleans.toArray(list);
        assertEquals(3, res.length);
        res = Booleans.toArray(Booleans.asList(false,true,false));
        assertEquals(3, res.length);
    }

    @Test
    public void testAsList() {
        List<Boolean> res = Booleans.asList(false,true,false);
        assertEquals(3, res.size());
        assertFalse(res.isEmpty());
        assertTrue(res.contains(false));
        assertFalse(res.contains(0));
        assertFalse(Booleans.asList(true, true).contains(false));
        assertEquals(1, res.indexOf(true));
        assertEquals(2, res.lastIndexOf(false));
        res.set(1, false);
        assertEquals(-1, res.indexOf(true));
        assertEquals(-1, res.lastIndexOf(true));
        assertEquals(-1, res.indexOf(1));
        assertEquals(-1, res.lastIndexOf(1));
        assertEquals(Boolean.FALSE, res.get(1));
        assertEquals(0, res.subList(0,0).size());
        assertEquals(Boolean.FALSE, res.subList(0,1).get(0));
        assertEquals(1, res.subList(0,1).size());
        assertTrue(res.equals(res));
        assertTrue(res.equals(Booleans.asList(false, false, false)));
        assertFalse(res.equals(Booleans.asList(true, false, false)));
        assertFalse(res.equals(Booleans.asList(false, false)));
        assertFalse(res.equals(new boolean[]{false, false}));
        assertEquals(1258132, res.hashCode());
        assertEquals("[false, false, false]", res.toString());
        res = Booleans.asList();
        assertEquals(0, res.size());
        assertTrue(res.isEmpty());
        assertEquals("[true, true]", Booleans.asList(true, true).toString());
    }

    @Test
    public void testCountTrue() {
        assertEquals(2, Booleans.countTrue(false, true, true, false));
    }

    @Test
    public void testReverse() {
        boolean[] tmp = new boolean[]{false, false, true};
        Booleans.reverse(tmp);
        assertTrue(tmp[0]);
        assertFalse(tmp[1]);
        assertFalse(tmp[2]);
        assertEquals(3, tmp.length);
    }

}