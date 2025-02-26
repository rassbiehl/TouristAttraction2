package org.example.turistguide2.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagsTest {

    @Test
    void toLowerCaseWithSpace() {
        assertEquals("child friendly", Tags.CHILD_FRIENDLY.toLowerCaseWithSpace());
        assertEquals("cultural inheritance", Tags.CULTURAL_INHERITANCE.toLowerCaseWithSpace());
        assertEquals("royal family", Tags.ROYAL_FAMILY.toLowerCaseWithSpace());
    }
}