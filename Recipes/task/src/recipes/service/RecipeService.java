package recipes.service;

import org.springframework.http.ResponseEntity;
import recipes.model.Recipe;
import recipes.model.UserDetailsImpl;

public interface RecipeService {
    void save(UserDetailsImpl user, Recipe recipe);

    ResponseEntity<?> removeRecipeById(UserDetailsImpl user, Long id);

    ResponseEntity<?> updateRecipe(UserDetailsImpl user, Long id, Recipe recipe);

    ResponseEntity<?> searchRecipeByName(String name);

    ResponseEntity<?> searchRecipeByCategory(String category);


}
