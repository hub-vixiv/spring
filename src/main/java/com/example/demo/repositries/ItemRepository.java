package com.example.demo.repositries;

//import java.util.List;
//import java.util.Optional;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ItemEntity;
//import com.example.demo.forms.ItemForm;
//import com.example.demo.forms.ItemForm;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
//	Optional<ItemEntity> findById(Long id);
//	List<ItemEntity> findAll();
//	void saveAndFlush(ItemForm itemForm);
}