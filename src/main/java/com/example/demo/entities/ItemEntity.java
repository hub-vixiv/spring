package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity //DBのテーブルと紐づく
@Table(name = "items")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動連番付与
	private long id;

//	@NotBlank
//	@Size(max = 20)
	private String name;

//	@Min(1)
	private int price;

//	@NotBlank
//	@Size(max = 400)
	private String description;
	
//	public void clear() {
//		name = null;
//		price = 0;
//		description = null;
//	}
	
//	public void setId(long id) {
//		this.id = id;
//	}
}
