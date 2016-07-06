import lombok.Value;

import java.util.Objects;


@Value
public class Entry {

    private final String name;
    private final String gender;
    private final String dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry otherEntry = (Entry) o;
        return name.equalsIgnoreCase(otherEntry.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
