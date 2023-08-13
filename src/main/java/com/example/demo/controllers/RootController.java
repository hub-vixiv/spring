package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;
//import com.example.demo.models.ItemForm;

import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
import com.example.demo.repositries.ItemRepository;

//import java.util.List;
//import java.util.Optional;

@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	InquiryRepository repository;
	@Autowired
	InquiryRepository2 repository2;
	//↑インターフェースを継承した無名クラスをnewしている

	@GetMapping
	public String index() {
		return "root/index";
	}

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		// RDBと連携できることを確認しておきます。
		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}

	//お問い合わせ２
	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm) {
		return "root/form2";
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}

		// RDBと連携できることを確認しておきます。
		repository2.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。2");
		return "root/form2";
	}

}