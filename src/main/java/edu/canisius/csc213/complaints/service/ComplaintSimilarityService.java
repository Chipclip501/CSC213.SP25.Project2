package edu.canisius.csc213.complaints.service;

import edu.canisius.csc213.complaints.model.Complaint;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComplaintSimilarityService {

    private final List<Complaint> complaints;

    public ComplaintSimilarityService(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<Complaint> findTop3Similar(Complaint target) {
        // TODO: Return top 3 most similar complaints (excluding itself)
            return complaints.stream()
        .filter(complaint -> complaint.getComplaintId() != (target.getComplaintId()))
        .sorted(Comparator.comparingDouble(complaint -> -cosineSimilarity(target.getEmbedding(), complaint.getEmbedding())))
        .limit(3)
        .collect(Collectors.toList());
    }

    private double cosineSimilarity(double[] a, double[] b) {
        // TODO: Implement cosine similarity
        double dot = 0.0, norm1 = 0.0, norm2 = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            norm1 += a[i] * a[i];
            norm2 += b[i] * b[i];
        }
        return dot / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    private static class ComplaintWithScore {
        Complaint complaint;
        double score;

        ComplaintWithScore(Complaint c, double s) {
            this.complaint = c;
            this.score = s;
        }
    }
}
