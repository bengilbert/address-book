import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<Entry>();

    public int numberOfMales() {
        return 0;
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
