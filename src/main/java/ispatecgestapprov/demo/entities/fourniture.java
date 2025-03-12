package ispatecgestapprov.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class fourniture extends idclass{

    @Column(name="type_fourniture", unique = false, nullable = false)
    private String type_fourniture;

    @Column(name="description", unique = false, nullable = true)
    private String description;

    public String getType_fourniture() {
        return type_fourniture;
    }

    public void setType_fourniture(String type_fourniture) {
        this.type_fourniture = type_fourniture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    




    

}
