import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class EntryFactoryTest {

    @Test
    public void genderTypesAreMarshalledToGenderClass() throws IOException {
        File addressBook = createAddressBook("Bill McKnight, Male, 16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        Entry entry = entries.iterator().next();
        assertThat(entry.getDateOfBirth(), is("16/03/77"));
        assertThat(entry.getName(), is("Bill McKnight"));
        assertThat(entry.getGender(), is(Gender.MALE));
    }

    private File createAddressBook(String address) throws IOException {
        File addressBook = File.createTempFile("test", "test");
        FileWriter fw = new FileWriter(addressBook);
        fw.write(address);
        fw.close();
        return addressBook;
    }

    @Test
    public void addressBookEntriesWithMissingFieldsAreNotCreated() throws IOException {
        File addressBook = createAddressBook("");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingNameFieldAreNotCreated() throws IOException {
        File addressBook = createAddressBook(",Male,16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingGenderFieldAreNotCreated() throws IOException {
        File addressBook = createAddressBook("Bob,,16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingDobFieldAreNotCreated() throws IOException {
        File addressBook = createAddressBook("Bob,Male");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }
}