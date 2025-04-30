package edu.canisius.csc213.complaints.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class EmbeddingLoader {

    /**
     * Loads complaint embeddings from a JSONL (newline-delimited JSON) file.
     * Each line must be a JSON object with:
     * {
     *   "complaintId": <long>,
     *   "embedding": [<double>, <double>, ...]
     * }
     *
     * @param jsonlStream InputStream to the JSONL file
     * @return A map from complaint ID to its embedding vector
     * @throws IOException if the file cannot be read or parsed
     */
    public static Map<Long, double[]> loadEmbeddings(InputStream jsonlStream) throws IOException {
        // TODO: Implement parsing of JSONL to extract complaintId and embedding
       /*  Map<Long, double[]> embeddings = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(jsonlStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, Object> obj = mapper.readValue(line, Map.class);
                Long complaintId = ((Number) obj.get("Complaint ID")).longValue();
                List<Number> embeddingList = (List<Number>) obj.get("embedding");

                double[] embedding = new double[embeddingList.size()];
                for (int i = 0; i < embeddingList.size(); i++) {
                    embedding[i] = embeddingList.get(i).doubleValue();
                }

                embeddings.put(complaintId, embedding);
            }
        }
        return embeddings;
    }
*/




        Map<Long, double[]> embeddings = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonlStream));
        String line;
        ObjectMapper objectMapper = new ObjectMapper();
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; // Skip empty lines
            // Parse each line as a JSON object
            Map<String, Object> jsonObject = objectMapper.readValue(line, new TypeReference<>() {});
            Long complaintId = Long.parseLong((String) jsonObject.get("id"));
            List<Double> embeddingList = objectMapper.convertValue(jsonObject.get("embedding"), new com.fasterxml.jackson.core.type.TypeReference<List<Double>>() {});

            // Convert List<Double> to double[]
            double[] embedding = new double[embeddingList.size()];
            for (int i = 0; i < embeddingList.size(); i++) {
                embedding[i] = embeddingList.get(i);
            }

            // Store in the map
            embeddings.put(complaintId, embedding);
        }
        return embeddings;
    }

}
