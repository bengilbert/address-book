import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {

    @Test
    public void entriesWithSameNameAreEqual() {
        assertEquals(new Entry("name", "male", "1/1/1980"), new Entry("name", "female", "1/1/1980"));
        assertEquals(new Entry("name", "male", "1/1/1980"), new Entry("name", "male", "1/1/1990"));
        assertEquals(new Entry("name", "male", "dob"), new Entry("name", "male", "dob"));
        assertEquals(new Entry("name", "gender", "dob"), new Entry("NAME", "gender", "dob"));
    }
}