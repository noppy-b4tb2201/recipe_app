package com.recipe.app.controller;

import com.recipe.app.DTO.RecipeDTO;
import com.recipe.app.model.Recipe;
import com.recipe.app.repository.RecipeRepository;
import com.recipe.app.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @Autowired
    RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> addRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        // TODO
        RecipeDTO addedRecipeDTO = recipeService.addRecipe(recipeDTO);
        return new ResponseEntity<>(addedRecipeDTO,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer rating) {
        // TODO
        List<RecipeDTO> RecipeDTOs = recipeService.searchRecipe(category, rating);
        return new ResponseEntity<>(RecipeDTOs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        // TODO
        RecipeDTO recipeDTO = recipeService.searchRecipeById(id);
        return new ResponseEntity<>(recipeDTO,HttpStatus.OK);
    }
}