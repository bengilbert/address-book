package addressbook;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;


public class AddressBookTest {

    @Test
    public void shouldReturnZeroMalesInAddressBookWhenEmpty() {
        AddressBook addressBook = new AddressBook();
        assertThat(addressBook.numberOfMales(), is(0L));
    }

    @Test
    public void shouldTheNumberOfMales() throws URISyntaxException {
        AddressBook addressBook = new AddressBook();
        File file = new File(AddressBookTest.class.getResource("/AddressBook2").toURI());
        addressBook.loadFromFile(file);

        assertThat(addressBook.numberOfMales(), is(3L));
    }

    @Test
    public void shouldReturnTheOldestEntryInTheAddressBook() throws IOException {
        File addressBookFile = AddressBookFileFixture.createAddressBook("Bill McKnight, Male, 16/03/77",
                "Paul Robinson, Male, 15/01/85",
                "Gemma Lane, Female, 20/11/91",
                "Sarah Stone, Female, 20/09/80",
                "Wes Jackson, Male, 14/08/74");

        AddressBook addressBook = new AddressBook();
        addressBook.loadFromFile(addressBookFile);

        assertThat(addressBook.oldest(), is(not(empty())));
        assertThat(addressBook.oldest().get(0).getName(), is("Wes Jackson"));
    }

    @Test
    public void shouldReturnMultipleEntriesIfTheOldestEntriesSharesTheSameDateOfBirth() throws IOException {
        File addressBookFile = AddressBookFileFixture.createAddressBook("Bill McKnight, Male, 16/03/77",
                "Paul Robinson, Male, 16/03/77");

        AddressBook addressBook = new AddressBook();
        addressBook.loadFromFile(addressBookFile);

        assertThat(addressBook.oldest(), is(not(empty())));
        assertThat(addressBook.oldest().get(0).getName(), is("Paul Robinson"));
        assertThat(addressBook.oldest().get(1).getName(), is("Bill McKnight"));
    }

    @Test
    public void noOldestEntryReturnedForEmptyAddressBook() throws IOException {
        AddressBook addressBook = new AddressBook();
        assertThat(addressBook.oldest(), is((empty())));
    }


    @Test
    public void canReturnTheDifferenceInAgeBetweenTwoEntries() throws IOException {
        File addressBookFile = AddressBookFileFixture.createAddressBook("Bill McKnight, Male, 16/03/77",
                "Paul Robinson, Male, 15/01/85",
                "Gemma Lane, Female, 20/11/91",
                "Sarah Stone, Female, 20/09/80",
                "Wes Jackson, Male, 14/08/74");

        AddressBook addressBook = new AddressBook();
        addressBook.loadFromFile(addressBookFile);

        assertThat(addressBook.findDaysBetweenEntriesByName("Paul Robinson", "bill McKnight").get(), is(2862L));
    }

}