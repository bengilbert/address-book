package addressbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AddressBookFileFixture {
    public static File createAddressBook(final String... addresses) throws IOException {
        File addressBook = File.createTempFile("test", "test");
        FileWriter fw = new FileWriter(addressBook);

        for (int i=0; i<addresses.length; i++) {
            fw.write(addresses[i] + "\n");
        }
        fw.close();
        return addressBook;
    }
}
