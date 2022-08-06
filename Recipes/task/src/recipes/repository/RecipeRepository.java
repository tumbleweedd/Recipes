package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import recipes.DTO.RecipeDTO;
import recipes.model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findById(Long id);
    @Transactional
    void deleteRecipeById(Long id);
    List<RecipeDTO> findRecipesByNameIgnoreCaseContainsOrderByDateDesc(String name);
    List<RecipeDTO> findRecipesByCategoryIgnoreCaseLikeOrderByDateDesc(String category);
    @Transactional
    @Query(value = "select r.user.id from Recipe r where r.id = ?1")
    Long findUserIdInRecipe(Long id);

}
