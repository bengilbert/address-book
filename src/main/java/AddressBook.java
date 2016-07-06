import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressBook {

    private Set<Entry> entries = new HashSet<Entry>();


    public int numberOfMales() {
        return 0;
    }


    public void loadFromFile(File addressBookFile) {

        try {
            Reader in = new FileReader(addressBookFile);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
                String name = record.get(0);
                String gender = record.get(1);
                String dateOfBirth = record.get(2);

                Entry entry = new Entry(name, gender, dateOfBirth);
                entries.add(entry);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
