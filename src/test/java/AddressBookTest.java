import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AddressBookTest {

    @Test
    public void shouldReturnZeroMalesInAddressBookWhenEmpty() {

        AddressBook addressBook = new AddressBook();
        assertThat(addressBook.numberOfMales(), is(0));

    }

    @Test
    public void shouldBeAbleToPopulateAddressBookFromFile() throws URISyntaxException {
        AddressBook addressBook = new AddressBook();
        File file = new File(AddressBookTest.class.getResource("AddressBook").toURI());
        addressBook.loadFromFile(file);

        assertThat(addressBook.numberOfMales(), is(3));

    }

}