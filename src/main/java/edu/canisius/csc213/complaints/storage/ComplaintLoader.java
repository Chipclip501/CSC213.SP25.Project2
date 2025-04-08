package edu.canisius.csc213.complaints.storage;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.canisius.csc213.complaints.model.Complaint;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        try (Scanner scnr = new Scanner(new FileInputStream(csvPath))) {
            while(scnr.hasNextLine()){
                

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return List.of(); // placeholder
    }
}
