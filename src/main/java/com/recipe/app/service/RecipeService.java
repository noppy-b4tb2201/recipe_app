package com.recipe.app.service;

import com.recipe.app.DTO.RecipeDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface RecipeService {
    RecipeDTO addRecipe(@Valid RecipeDTO recipe);

    List<RecipeDTO> searchRecipe(String category, Integer rating);

    RecipeDTO searchRecipeById(Long id);
}
