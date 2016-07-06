package addressbook.entry;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class EntryTest {

    @Test
    public void entriesWithSameNameAreEqual() {
        Assert.assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1, 1)), new Entry("name", Gender.FEMALE, LocalDate.of(1980, 1, 1)));
        assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1, 1)), new Entry("name", Gender.MALE, LocalDate.of(1990, 1, 1)));
        assertEquals(new Entry("name", Gender.MALE, LocalDate.of(1980, 1, 1)), new Entry("name", Gender.MALE, LocalDate.of(1980, 1, 1)));
        assertEquals(new Entry("name", Gender.UNKNOWN, LocalDate.of(1980, 1, 1)), new Entry("NAME", Gender.UNKNOWN, LocalDate.of(1980, 1, 1)));
    }
}