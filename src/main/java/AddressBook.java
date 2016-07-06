import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<>();

    public long genderCount(final Gender gender) {
        return entries.stream().filter(entry -> gender.equals(entry.getGender())).count();
    }

    public Optional<Entry> oldest() {
        return entries.stream().min(Comparator.comparing(Entry::getDateOfBirth));
    }

    public void loadFromFile(File addressBookFile) {
        entries.addAll(EntryFactory.fromFile(addressBookFile));
    }
}
