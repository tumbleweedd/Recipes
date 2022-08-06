package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @JsonIgnore
    private String date;

    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "ingredients")
    private List<String> ingredients = new ArrayList<>();
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "directions")
    private List<String> directions = new ArrayList<>();

    public Map<String, Object> objectToReturn() {
        Map<String, Object> recipeFormatted = new LinkedHashMap<>();
        recipeFormatted.put("name", this.name);
        recipeFormatted.put("category", this.category);
        recipeFormatted.put("date", this.date);
        recipeFormatted.put("description", this.description);
        recipeFormatted.put("ingredients", this.ingredients);
        recipeFormatted.put("directions", this.directions);
        return recipeFormatted;
    }

    public Recipe(String name, String category, String description,
                  List<String> directions, List<String> ingredients) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.directions = directions;
        this.ingredients = ingredients;

    }


}
