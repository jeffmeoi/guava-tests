package com.google.common.hash;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.Assert.*;

public class Crc32cHashFunctionTest {

    @Test
    public void bits() {
        assertEquals(32, new Crc32cHashFunction().bits());
    }

    @Test
    public void newHasher() {
        Hasher hasher = new Crc32cHashFunction().newHasher();
        hasher.putBoolean(false);
        hasher.putByte((byte)123);
        hasher.putChar('a');
        hasher.putFloat(123.123f);
        hasher.putBytes(new byte[]{1,2});
        assertEquals(109479762, hasher.hash().asInt());
        assertEquals(109479762, hasher.hash().asInt());
        hasher = new Crc32cHashFunction().newHasher();
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        hasher.putBytes(ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN).putLong(123));
        assertEquals(1499445213, hasher.hash().asInt());
        hasher = new Crc32cHashFunction().newHasher();
        assertEquals(0, hasher.hash().asInt());
    }

    @Test
    public void testToString() {
        assertEquals("Hashing.crc32c()", new Crc32cHashFunction().toString());
    }
}