package com.recipe.app.service.impl;

import com.recipe.app.DTO.RecipeDTO;
import com.recipe.app.exception.APIException;
import com.recipe.app.model.Recipe;
import com.recipe.app.repository.RecipeRepository;
import com.recipe.app.service.RecipeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RecipeDTO addRecipe(@Valid RecipeDTO recipeDTO) {

        Recipe addedRecipe = modelMapper.map(recipeDTO, Recipe.class);
        Recipe addRecipe = recipeRepository.save(addedRecipe);
        RecipeDTO addedRecipeDTO = modelMapper.map(addRecipe, RecipeDTO.class);

        return addedRecipeDTO;
    }

    @Override
    public List<RecipeDTO> searchRecipe(String category, Integer rating) {

        if(category == null && rating == null) {
            List<Recipe> recipes = recipeRepository.findAll();

            List<RecipeDTO> recipeDTOS = recipes.stream()
                                                .map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
                                                .toList();

            return recipeDTOS;


        } else if(rating == null) {
            List<Recipe> recipes = recipeRepository.findByCategory(category);

            List<RecipeDTO> recipeDTOS = recipes.stream()
                                                .map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
                                                .toList();

            return recipeDTOS;
        } else if(category == null) {
            List<Recipe> recipes = recipeRepository.findByRating(rating);

            List<RecipeDTO> recipeDTOS = recipes.stream()
                                                .map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
                                                .toList();

            return recipeDTOS;
        } else {
            List<Recipe> recipes = recipeRepository.findByCategoryAndRating(category, rating);

            List<RecipeDTO> recipeDTOS = recipes.stream()
                                                .map(recipe -> modelMapper.map(recipe, RecipeDTO.class))
                                                .toList();

            return recipeDTOS;
        }
    }

    @Override
    public RecipeDTO searchRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                                .orElseThrow(() -> new APIException("NOt Found"));

        RecipeDTO recipeDTO = modelMapper.map(recipe, RecipeDTO.class);

        return recipeDTO;
    }
}
