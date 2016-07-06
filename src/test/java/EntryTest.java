import org.junit.Test;


import java.time.LocalDate;

import static org.junit.Assert.*;

public class EntryTest {

    @Test
    public void entriesWithSameNameAreEqual() {
        assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1,1)), new Entry("name", Gender.FEMALE, LocalDate.of(1980, 1,1)));
        assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1,1)), new Entry("name", Gender.MALE, LocalDate.of(1990, 1,1)));
        assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1,1)), new Entry("name", Gender.MALE, LocalDate.of(1980, 1,1)));
        assertEquals(new Entry("name", Gender.UNKNOWN, LocalDate.of(1980, 1,1)), new Entry("NAME", Gender.UNKNOWN, LocalDate.of(1980, 1,1)));
    }
}