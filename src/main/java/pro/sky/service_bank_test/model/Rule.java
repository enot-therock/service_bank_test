package pro.sky.service_bank_test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "arguments")
    private List<String> arguments;

    @Column(name = "negate")
    private Boolean negate;

    @ManyToOne
    @JoinColumn(name = "recommendationByRule_id")
    private RecommendationByRule recommendationByRule;

    public Rule(String query, List<String> arguments, Boolean negate, RecommendationByRule recommendationByRule) {
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

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
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
