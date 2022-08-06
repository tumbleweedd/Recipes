package recipes.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.DTO.RecipeDTO;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.model.UserDetailsImpl;
import recipes.repository.RecipeRepository;
import recipes.service.RecipeService;
import recipes.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Map<String, Object> getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return recipe.get().objectToReturn();
    }

    public void checkValidRequest(Recipe recipe) {
        if (recipe.getDirections() == null ||
                recipe.getIngredients() == null ||
                recipe.getDescription() == null ||
                recipe.getName() == null ||
                recipe.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void save(UserDetailsImpl user, Recipe recipe) {
        checkValidRequest(recipe);
        recipe.setDate(DateTimeUtil.getCurrentDateTime(LocalDateTime.now()));
        recipe.setUser(new User(user));
        recipeRepository.save(recipe);
    }

    @Override
    public ResponseEntity<?> removeRecipeById(UserDetailsImpl user, Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            if (recipeRepository.findUserIdInRecipe(id).equals(user.getId())) {
                recipeRepository.deleteRecipeById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> updateRecipe(UserDetailsImpl user, Long id, Recipe recipe) {
        if (recipeRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (recipeRepository.findUserIdInRecipe(id).equals(user.getId())) {
            recipe.setId(id);
            save(user, recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<?> searchRecipeByName(String name) {
        List<RecipeDTO> recipesToMapping = recipeRepository.findRecipesByNameIgnoreCaseContainsOrderByDateDesc(name);
        if (recipesToMapping.isEmpty()) return new ResponseEntity<>(List.of(), HttpStatus.OK);

        return new ResponseEntity<>(recipesToMapping, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> searchRecipeByCategory(String category) {
        List<RecipeDTO> recipesToMapping = recipeRepository.findRecipesByCategoryIgnoreCaseLikeOrderByDateDesc(category);

        if (recipesToMapping.isEmpty()) return new ResponseEntity<>(List.of(), HttpStatus.OK);

        return new ResponseEntity<>(recipesToMapping, HttpStatus.OK);

    }
}
