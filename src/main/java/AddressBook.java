import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<>();

    public long numberOfMales() {
        return entries.stream().filter(entry -> Gender.MALE.equals(entry.getGender())).count();
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
