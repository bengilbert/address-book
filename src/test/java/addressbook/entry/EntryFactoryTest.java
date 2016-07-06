package addressbook.entry;

import addressbook.AddressBookFileFixture;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class EntryFactoryTest {

    @Test
    public void genderTypesAreMarshalledToGenderClass() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Bill McKnight, Male, 16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        Entry entry = entries.iterator().next();
        assertThat(entry.getDateOfBirth(), is(LocalDate.of(1977, 3, 16)));
        assertThat(entry.getName(), is("Bill McKnight"));
        assertThat(entry.getGender(), Is.is(Gender.MALE));
    }

    @Test
    public void addressBookEntriesWithMissingFieldsAreNotCreated() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingNameFieldAreNotCreated() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook(",Male,16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingGenderFieldAreNotCreated() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Bob,,16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void addressBookEntriesWithMissingDobFieldAreNotCreated() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Bob,Male");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries.isEmpty(), is(true));
    }

    @Test
    public void dateOfBirthsInTheFutureAreTreatedAsBeingFrom19xx() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Bill McKnight, Male, 16/03/77");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        Entry entry = entries.iterator().next();
        assertThat(entry.getDateOfBirth(), is(LocalDate.of(1977, 3, 16)));
    }

    @Test
    public void datePrefixDefaultsTo2000() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Peter James, Male, 16/03/05");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        Entry entry = entries.iterator().next();
        assertThat(entry.getDateOfBirth(), is(LocalDate.of(2005, 3, 16)));
    }

    @Test
    public void entriesWithUnparsableDatesAreNotCreated() throws IOException {
        File addressBook = AddressBookFileFixture.createAddressBook("Peter James, Male, invalidDate");

        Set<Entry> entries = EntryFactory.fromFile(addressBook);
        assertThat(entries, is(Collections.emptySet()));
    }
}