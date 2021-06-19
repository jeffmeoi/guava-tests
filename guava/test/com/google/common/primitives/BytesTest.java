package com.google.common.primitives;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BytesTest {

    private byte[] arr1 = new byte[]{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
    private byte[] zeros = new byte[]{ 0, 0, 0 };
    private byte[] empty = new byte[]{};

    @Test
    public void testHashCode() {
        assertEquals(1, Bytes.hashCode((byte) 1));
    }

    @Test
    public void contains() {
        assertTrue(Bytes.contains(arr1, (byte) 1));
        assertFalse(Bytes.contains(arr1, (byte) 0));
    }

    @Test
    public void indexOf() {
        assertEquals(2, Bytes.indexOf(arr1, (byte) 3));
        assertEquals(-1, Bytes.indexOf(arr1, (byte) 0));
        assertEquals(0, Bytes.indexOf(arr1, empty));
        assertEquals(1, Bytes.indexOf(arr1, new byte[]{2,3}));
        assertEquals(-1, Bytes.indexOf(arr1, new byte[]{2,1}));
    }

    @Test
    public void lastIndexOf() {
        assertEquals(7, Bytes.lastIndexOf(arr1, (byte) 3));
        assertEquals(-1, Bytes.lastIndexOf(arr1, (byte) 0));
    }

    @Test
    public void concat() {
        byte[] res = Bytes.concat(arr1, zeros, empty);
        assertEquals(13, res.length);
        assertEquals(3, res[2]);
        assertEquals(5, res[9]);
        assertEquals(0, res[10]);
    }

    @Test
    public void ensureCapacity() {
        byte[] res = Bytes.ensureCapacity(arr1, 5, 3);
        assertSame(res, res);
        res = Bytes.ensureCapacity(arr1, 11, 3);
        assertEquals(14, res.length);
        try {
            res = Bytes.ensureCapacity(arr1, -1, 3);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            res = Bytes.ensureCapacity(arr1, 1, -3);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void toArray() {
        List<Number> list = new ArrayList<Number>(){{
           add(1);
           add(2);
           add(3);
        }};
        byte[] res = Bytes.toArray(list);
        assertEquals(3, res.length);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
        assertEquals(3, res[2]);
        res = Bytes.toArray(Bytes.asList((byte)1, (byte)2, (byte)3));
        assertEquals(3, res.length);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
        assertEquals(3, res[2]);
    }

    @Test
    public void asList() {
        List<Byte> res = Bytes.asList();
        assertEquals(0, res.size());
        assertTrue(res.isEmpty());
        res = Bytes.asList((byte)1, (byte)2, (byte)3);
        assertEquals(3, res.size());
        assertEquals(1, (byte)res.get(0));
        assertEquals(2, (byte)res.get(1));
        assertEquals(3, (byte)res.get(2));
        assertFalse(res.isEmpty());
        assertEquals(true, res.contains((byte) 1));
        assertEquals(false, res.contains((byte) -1));
        assertEquals(false, res.contains(new Object()));
        assertEquals(-1, res.indexOf(new Object()));
        assertEquals(0, res.indexOf((byte) 1));
        assertEquals(-1, res.indexOf((byte) -1));
        assertEquals(-1, res.lastIndexOf(new Object()));
        assertEquals(-1, res.lastIndexOf((byte) -1));
        res.set(2, (byte)1);
        assertEquals(2, res.lastIndexOf((byte) 1));
        assertEquals(0, res.subList(0, 0).size());
        assertEquals(2, res.subList(0, 2).size());
        assertEquals(2, (byte)res.subList(0, 2).get(1));
        assertEquals(true, res.equals(res));
        assertEquals(true, res.equals(Bytes.asList((byte)1, (byte)2, (byte)1)));
        assertEquals(false, res.equals(Bytes.asList((byte)1, (byte)2, (byte)3)));
        assertEquals(false, res.equals(Bytes.asList((byte)1, (byte)2)));
        assertEquals(false, res.equals(Arrays.asList((byte)1, (byte)2)));
        assertEquals("[1, 2, 1]", res.toString());
        assertEquals(30815, res.hashCode());
    }

    @Test
    public void reverse() {
        byte[] arr = new byte[]{1,2,3};
        Bytes.reverse(arr);
        assertEquals(3, arr.length);
        assertEquals(3, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(1, arr[2]);

    }

}