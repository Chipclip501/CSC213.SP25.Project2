package edu.canisius.csc213.complaints.storage;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.canisius.csc213.complaints.model.Complaint;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


/**
 * Handles loading of complaints and embedding data,
 * and returns a fully hydrated list of Complaint objects.
 */
public class ComplaintLoader {

    /**
     * Loads complaints from a CSV file and merges with embedding vectors from a JSONL file.
     *
     * @param csvPath    Resource path to the CSV file
     * @param jsonlPath  Resource path to the JSONL embedding file
     * @return A list of Complaint objects with attached embedding vectors
     * @throws Exception if file reading or parsing fails
     */
    public static List<Complaint> loadComplaintsWithEmbeddings(String csvPath, String jsonlPath) throws Exception {
        // TODO: Load CSV and JSONL resources, parse, and return hydrated Complaint list
        List<Complaint> complaints;
        try (InputStream csvStream = new FileInputStream(csvPath)) {
            complaints = new CsvToBeanBuilder<Complaint>(new InputStreamReader(csvStream, StandardCharsets.UTF_8))
                    .withType(Complaint.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        }

        // Load embeddings from JSONL
        Map<Long, double[]> embeddings;
        try (InputStream jsonlStream = new FileInputStream(jsonlPath)) {
            embeddings = EmbeddingLoader.loadEmbeddings(jsonlStream);
        }

        // Merge embeddings into complaints
        ComplaintMerger.mergeEmbeddings(complaints, embeddings);

        return complaints;
    }
}
