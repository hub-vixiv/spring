package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ItemEntity;
import com.example.demo.forms.ItemForm;
import com.example.demo.repositries.ItemRepository;

//サービスクラス　formとentityの間　変換？
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * IDから検索
	 * @param id
	 * @return
	 */
	public ItemForm getOne(Long id) {
		//ItemEntity型で取得
		ItemEntity item = itemRepository.findById(id).get();
		//ItemEntity　→　ItemForm
		ItemForm itemForm = entityToForm(item);
		return itemForm;
	}

	/**
	 * 全件検索
	 * @return
	 */
	public List<ItemForm> getList(){
		//return用のItemFormのリストをnew
		List<ItemForm> itemForms = new ArrayList<>();
		//ItemEntityで全件取得
		List<ItemEntity> items = itemRepository.findAll();
		//ItemEntity　→　ItemFormへ
		for(ItemEntity item: items) {
			ItemForm itemForm = entityToForm(item);
			itemForms.add(itemForm);
		}
		return itemForms;
	}

	/**
	 * 登録
	 * @param itemForm
	 */
	public void save(ItemForm itemForm) {
		//save用のItemEntityをnew
		ItemEntity itemEntity = new ItemEntity();
		//ItemForm　→　ItemEntity
		itemEntity = formToEntity(itemForm, itemEntity);
		itemRepository.saveAndFlush(itemEntity);
	}

	/**
	 * 更新
	 * @param id
	 * @param itemForm
	 */
	public void updateItem(Long id, ItemForm itemForm) {
		//ItemEntity型で取得
		ItemEntity itemEntity = itemRepository.findById(id).get();
		//ItemForm　→　ItemEntity
		itemEntity = formToEntity(itemForm, itemEntity);
		itemRepository.saveAndFlush(itemEntity);
	}

	/**
	 * 削除
	 * @param id
	 */
	public void deleteOne(Long id) {
		itemRepository.deleteById(id);
	}

	/**
	 * ItemForm　→　ItemEntity 変換
	 * @param item
	 * @return
	 */
	private ItemForm entityToForm(ItemEntity item) {
		ItemForm itemForm = new ItemForm();
		itemForm.setId(item.getId());
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		itemForm.setDescription(item.getDescription());
		return itemForm;
	}

	/**
	 * ItemForm　→　ItemEntity　変換
	 * @param itemForm
	 * @param itemEntity
	 */
	private ItemEntity formToEntity(ItemForm itemForm, ItemEntity itemEntity) {
		itemEntity.setName(itemForm.getName());
		itemEntity.setPrice(itemForm.getPrice());
		itemEntity.setDescription(itemForm.getDescription());
		return itemEntity;
	}
}
