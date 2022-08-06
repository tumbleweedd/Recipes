package recipes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.model.Recipe;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private String name;
    private String description;
    private String category;
    private String date;
    private List<String> ingredients;
    private List<String> directions;

    public RecipeDTO(Recipe recipe) {
        this.name = recipe.getName();
        this.category = recipe.getCategory();
        this.date = recipe.getDate();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.directions = recipe.getDirections();
    }


}
