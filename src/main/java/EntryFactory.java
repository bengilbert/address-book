import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;


public class EntryFactory {

    public static Set<Entry> fromFile(File addressBookFile) {
        Set<Entry> entries = new HashSet<Entry>();

        try {
            Reader in = new FileReader(addressBookFile);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
                String name = record.get(0);
                String genderText = record.get(1);
                String dateOfBirth = record.get(2);

                Gender gender = Gender.UNKNOWN;

                if ("male".equalsIgnoreCase(genderText)) {
                    gender = Gender.MALE;
                } else if ("female".equalsIgnoreCase(genderText)) {
                    gender = Gender.FEMALE;
                }

                Entry entry = new Entry(name, gender, dateOfBirth);
                entries.add(entry);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }
}
