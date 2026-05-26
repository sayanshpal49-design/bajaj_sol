package com.acropolis.bfhl.service;

import com.acropolis.bfhl.config.BfhlUserProperties;
import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        BfhlUserProperties props = new BfhlUserProperties();
        props.setFullName("sayansh_pal");
        props.setDobDdmmyyyy("30102005");
        props.setEmail("sayanshpal@gmail.com");
        props.setRollNumber("0827CI231121");
        service = new BfhlServiceImpl(props);
    }

    @Test
    void exampleA() {
        BfhlResponse response = service.process(
                new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$")));

        assertTrue(response.isSuccess());
        assertEquals("sayansh_pal_30102005", response.getUserId());
        assertEquals("sayanshpal@gmail.com", response.getEmail());
        assertEquals("0827CI231121", response.getRollNumber());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void exampleB() {
        BfhlResponse response = service.process(new BfhlRequest(Arrays.asList(
                "2", "a", "y", "4", "&", "-", "*", "5", "92", "b")));

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    void exampleC() {
        BfhlResponse response = service.process(
                new BfhlRequest(Arrays.asList("A", "ABCD", "DOE")));

        assertTrue(response.isSuccess());
        assertEquals(Collections.emptyList(), response.getOddNumbers());
        assertEquals(Collections.emptyList(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(Collections.emptyList(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void buildConcatStringHelpers() {
        assertEquals("Ra", BfhlServiceImpl.buildConcatString("aR"));
        assertEquals("EoDdCbAa", BfhlServiceImpl.buildConcatString("AABCDDOE"));
        assertEquals("", BfhlServiceImpl.buildConcatString(""));
    }
}
