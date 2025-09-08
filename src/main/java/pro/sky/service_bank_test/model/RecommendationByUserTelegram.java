package pro.sky.service_bank_test.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "userRecommendations")
public class RecommendationByUserTelegram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;

    public RecommendationByUserTelegram(UUID userID, String firsName, String lastName) {
        this.userId = userID;
        this.firsName = firsName;
        this.lastName = lastName;
    }

    public RecommendationByUserTelegram() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID user) {
        this.userId = userId;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
