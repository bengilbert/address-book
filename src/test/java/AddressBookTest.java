import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AddressBookTest {

    @Test
    public void shouldReturnZeroMalesInAddressBookWhenEmpty() {

        AddressBook addressBook = new AddressBook();
        assertThat(addressBook.genderCount(Gender.MALE), is(0L));

    }

    @Test
    public void shouldBeAbleToPopulateAddressBookFromFile() throws URISyntaxException {
        AddressBook addressBook = new AddressBook();
        File file = new File(AddressBookTest.class.getResource("AddressBook").toURI());
        addressBook.loadFromFile(file);

        assertThat(addressBook.genderCount(Gender.MALE), is(3L));
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

        assertThat(addressBook.oldest().isPresent(), is(true));
        assertThat(addressBook.oldest().get().getName(), is("Wes Jackson"));
    }

}