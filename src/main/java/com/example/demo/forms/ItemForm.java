package com.example.demo.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;


import lombok.Data;

@Data
public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	private long id;

	@NotBlank
	@Size(max = 20, min = 1)
	private String name;

	@Min(1)
	@Max(9999999)
	private int price;

	@NotBlank
	@Size(max = 400, min = 1)
	private String description;
	
	public void clear() {
		name = null;
		price = 0;
		description = null;
	}
}