package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.model.UserDetailsImpl;
import recipes.service.impl.RecipeServiceImpl;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class FirstController {
    private final RecipeServiceImpl recipeService;

    public FirstController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                                        @Valid @RequestBody Recipe recipe) {
        recipeService.save(user, recipe);
        return new ResponseEntity<>(Map.of("id", recipe.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Map<String, Object> showRecipe(@PathVariable("id") Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                                          @Valid @PathVariable("id") Long id) {
        return recipeService.removeRecipeById(user, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                                          @PathVariable("id") Long id,
                                          @Valid @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(user, id, recipe);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchRecipe(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "category", required = false) String category) {
        Optional<String> checkName = Optional.ofNullable(name);
        Optional<String> checkCategory = Optional.ofNullable(category);

        if ((checkName.isEmpty() && checkCategory.isEmpty()) || (checkName.isPresent() && checkCategory.isPresent())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (name == null) return recipeService.searchRecipeByCategory(category);

        return recipeService.searchRecipeByName(name);
    }
}
