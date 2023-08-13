package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.forms.ItemForm;
import com.example.demo.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

	//商品
	@Autowired
	ItemService itemService;
	//↑インターフェースを継承した無名クラスをnewしている

	/**
	 * 商品登録画面
	 * @param itemForm
	 * @return
	 */
	@GetMapping("/itemform")
	public String regist(ItemForm itemForm) {
		return "item/itemform";
	}

	/**
	 * 商品登録画面　登録後、商品一覧を表示
	 * @param itemForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/itemform")
	public String regist(@Validated ItemForm itemForm, BindingResult bindingResult, Model model) {
		//入力値のチェック 不備があればエラー表示する
		if (bindingResult.hasErrors()) {
			return "item/itemform";
		}

		// 不備が無ければDBへ登録処理する
		itemService.save(itemForm);
		model.addAttribute("message", "商品登録が完了しました。");
		return list(model);
	}

	/**
	 * 商品一覧画面の表示
	 * @param model
	 * @return
	 */
	@GetMapping("/itemlist")
	public String list(Model model) {
		List<ItemForm> items = itemService.getList();
		model.addAttribute("items", items); 
		return "item/itemlist";
	}

	/**
	 * 商品詳細画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public String showOne(@PathVariable Long id, Model model) {
		ItemForm item = itemService.getOne(id);
		model.addAttribute("item", item);
		return "item/itemshow";
	}

	/**
	 * 商品編集画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		ItemForm item = itemService.getOne(id);
		model.addAttribute("item", item);
		return "item/itemedit";
	}

	/**
	 * 商品更新　更新後、商品詳細画面を表示
	 * PutMappingは修正・更新用
	 * @param id
	 * @param itemForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @Validated @ModelAttribute ItemForm itemForm, BindingResult bindingResult, Model model) {
		
		//入力値のチェック　不備があればメッセージ表示
		if (bindingResult.hasErrors()) {
			model.addAttribute("item", itemForm);
			return "item/itemedit";
			//↑メッセージが表示されない
		}
		itemService.updateItem(id, itemForm);
		model.addAttribute("message", "商品情報の更新が完了しました。");	        
		return showOne(id, model);
	}
	
	/**
	 * 商品削除　削除後、商品一覧画面を表示
	 * @param id
	 * @param model
	 * @return
	 */
	@DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model) {
		itemService.deleteOne(id);
		model.addAttribute("message", "商品情報の削除が完了しました。");	 
		return list(model);
	}
}