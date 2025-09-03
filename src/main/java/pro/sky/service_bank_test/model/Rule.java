package pro.sky.service_bank_test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "arguments")
    private String arguments;

    @Column(name = "negate")
    private Boolean negate;

    @ManyToOne
    @JoinColumn(name = "recommendationByRule_id")
    private RecommendationByRule recommendationByRule;

    public Rule(String query, String arguments, Boolean negate, RecommendationByRule recommendationByRule) {
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
        this.recommendationByRule = recommendationByRule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public Boolean getNegate() {
        return negate;
    }

    public void setNegate(Boolean negate) {
        this.negate = negate;
    }

    public RecommendationByRule getRecommendationByRule() {
        return recommendationByRule;
    }

    public void setRecommendationByRule(RecommendationByRule recommendationByRule) {
        this.recommendationByRule = recommendationByRule;
    }
}
