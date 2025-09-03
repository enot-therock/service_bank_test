package pro.sky.service_bank_test.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Recommendation {

    private UUID id;
    private String name, text;

    public Recommendation(UUID id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Recommendation() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
