package storage;

import core.StorageException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CSV storage for ledger.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private final File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Load lines from storage. Returns empty list if file does not exist.
     *
     * @throws IOException when file read fails
     */
    public List<String> load() throws IOException, StorageException {
        List<String> lines = new ArrayList<>();
        if (!file.exists()) return lines;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load file: " + e.getMessage(), e);
            throw new StorageException("Failed to load ledger file", e);
        }
        return lines;
    }

    /**
     * Save CSV lines (overwrite).
     */
    public void save(List<String> lines) throws IOException, StorageException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save file: " + e.getMessage(), e);
            throw new StorageException("Failed to save ledger file", e);
        }
    }
}


