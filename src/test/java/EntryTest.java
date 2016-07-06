import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {

    @Test
    public void entriesWithSameNameAreEqual() {
        assertEquals(new Entry("name", Gender.MALE, "1/1/1980"), new Entry("name", Gender.FEMALE, "1/1/1980"));
        assertEquals(new Entry("name", Gender.MALE, "1/1/1980"), new Entry("name", Gender.MALE, "1/1/1990"));
        assertEquals(new Entry("name", Gender.MALE, "dob"), new Entry("name", Gender.MALE, "dob"));
        assertEquals(new Entry("name", Gender.UNKNOWN, "dob"), new Entry("NAME", Gender.UNKNOWN, "dob"));
    }
}