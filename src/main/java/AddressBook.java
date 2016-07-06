import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<>();

    public long genderCount(final Gender gender) {
        return entries.stream().filter(entry -> gender.equals(entry.getGender())).count();
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
