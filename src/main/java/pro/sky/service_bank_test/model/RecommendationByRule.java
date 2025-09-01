package pro.sky.service_bank_test.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recommendation")
public class RecommendationByRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_text")
    private String product_text;

    @OneToMany(mappedBy = "recommendationByRule", cascade = CascadeType.ALL)
    private List<Rule> rule;

    public RecommendationByRule(String product_id, String product_name, String product_text, List<Rule> rule) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_text = product_text;
        this.rule = rule;
    }

    public RecommendationByRule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_text() {
        return product_text;
    }

    public void setProduct_text(String product_text) {
        this.product_text = product_text;
    }

    public List<Rule> getRule() {
        return rule;
    }

    public void setRule(List<Rule> rule) {
        this.rule = rule;
    }
}
