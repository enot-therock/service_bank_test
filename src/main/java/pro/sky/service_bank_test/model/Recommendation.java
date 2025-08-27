package pro.sky.service_bank_test.model;

import org.springframework.stereotype.Component;

@Component
public class Recommendation {

    private String id, name, text;

    public Recommendation(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Recommendation() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
