package com.recipe.app.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data

public class RecipeDTO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    @Min(1)
    @Max(5)
    private int rating;
}
