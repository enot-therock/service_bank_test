package pro.sky.service_bank_test.listener.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "userRecommendations")
public class RecommendationByUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "used_id")
    private UUID user;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;

    public RecommendationByUser(UUID user, String firsName, String lastName) {
        this.user = user;
        this.firsName = firsName;
        this.lastName = lastName;
    }

    public RecommendationByUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
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
