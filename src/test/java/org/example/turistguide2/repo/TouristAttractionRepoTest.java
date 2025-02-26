package org.example.turistguide2.repo;

import org.example.turistguide2.model.TouristAttraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouristAttractionRepoTest {

    private TouristAttractionRepo repo;

    @BeforeEach
    void setUp() {
        repo = new TouristAttractionRepo();
        repo.addTouristAttractionToList(new TouristAttraction("Tivoli", "Forlystelsespark", "København", null));
        repo.addTouristAttractionToList(new TouristAttraction("Legoland", "Forlystelsespark", "Billund", null));
    }

    @Test
    void deleteTouristAttractionFromList_Success() {
        boolean result = repo.deleteTouristAttractionFromList("Tivoli");

        assertTrue(result, "Tivoli burde være slettet");
        assertNull(repo.findAttractionByName("Tivoli"), "Tivoli burde ikke findes længere");
    }

    @Test
    void deleteTouristAttractionFromList_NotFound() {
        boolean result = repo.deleteTouristAttractionFromList("FalskAttraktion");

        assertFalse(result, "FalskAttraktion burde ikke kunne slettes");
    }
}
