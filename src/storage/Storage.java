package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV storage for ledger.
 */
public class Storage {
    private final File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public List<String> load() throws IOException {
        List<String> lines = new ArrayList<>();
        if (!file.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        }
        return lines;
    }

    public void save(List<String> lines) throws IOException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
    }
}

