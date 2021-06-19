package com.google.common.base;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaseFormatTest {

    @Test
    public void to() {
        assertEquals("helloWorld", CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "hello-world"));
        assertEquals("HelloWorld", CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "hello-world"));
        assertEquals("hello-world", CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_HYPHEN, "hello-world"));
        assertEquals("hello_world", CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, "hello-world"));
        assertEquals("HELLO_WORLD", CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, "hello-world"));

        assertEquals("hello", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "hello"));
        assertEquals("helloWorld", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "hello_world"));
        assertEquals("helloWorldWorld", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "hello_world_world"));
        assertEquals("HelloWorld", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "hello_world"));
        assertEquals("hello-world", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "hello_world"));
        assertEquals("hello_world", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, "hello_world"));
        assertEquals("HELLO_WORLD", CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, "hello_world"));

        assertEquals("helloWorld", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "HELLO_WORLD"));
        assertEquals("HelloWorld", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "HELLO_WORLD"));
        assertEquals("hello-world", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "HELLO_WORLD"));
        assertEquals("hello_world", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, "HELLO_WORLD"));
        assertEquals("HELLO_WORLD", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, "HELLO_WORLD"));
    }


    @Test
    public void converterTo() {
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        assertEquals("HELLO_WORLD", converter.convert("helloWorld"));
        assertEquals("helloWorld", converter.correctedDoBackward("HELLO_WORLD"));
        assertEquals("LOWER_CAMEL.converterTo(UPPER_UNDERSCORE)", converter.toString());
        assertEquals(CaseFormat.LOWER_CAMEL.hashCode() ^ CaseFormat.UPPER_UNDERSCORE.hashCode(), converter.hashCode());
        assertEquals(converter, CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE));
        assertNotEquals(converter, CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE));
        assertNotEquals(converter, CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE));
        assertNotEquals(converter, new Object());
    }

    @Test
    public void normalizeWord() {
        assertEquals("", CaseFormat.LOWER_CAMEL.normalizeWord(""));
        assertEquals("Helloworld", CaseFormat.LOWER_CAMEL.normalizeWord("HElloWorld"));
        assertEquals("Helloworld", CaseFormat.UPPER_CAMEL.normalizeWord("helloWorld"));
        assertEquals("hello-world", CaseFormat.LOWER_HYPHEN.normalizeWord("Hello-World"));
        assertEquals("hello_world", CaseFormat.LOWER_UNDERSCORE.normalizeWord("Hello_World"));
        assertEquals("HELLO_WORLD", CaseFormat.UPPER_UNDERSCORE.normalizeWord("Hello_World"));
    }
}