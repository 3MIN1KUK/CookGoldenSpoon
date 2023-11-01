package com.m1k.goldenSpoon.recipe.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecipeMaterial {

	private String materialName;
	private String recipeMaterialQuantity;
	private int recipeMaterialOrder;
}
