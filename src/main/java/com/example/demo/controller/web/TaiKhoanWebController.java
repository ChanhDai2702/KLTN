package com.example.demo.controller.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.TaiKhoan;
import com.example.demo.service.GiangVienService;
import com.example.demo.service.SinhVienService;
import com.example.demo.service.TaiKhoanService;

@Controller
public class TaiKhoanWebController {
	static String FORM_LOGIN = "loginPage";
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	

	
	@Autowired
	private SinhVienService sinhVienService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private GiangVienService giangVienService;
	
	@RequestMapping(value = "/capnhatmatkhau")
	public String updateMK(Model model,Principal principal) {
		System.out.println("aaaaaaaaaaaaaaaaa");
		TaiKhoan tk = taiKhoanService.getTKbyID(Long.parseLong(principal.getName()));
		if(sinhVienService.getSVbyID(tk.getTentaikhoan())!=null) {
			model.addAttribute("sv", sinhVienService.getSVbyID(tk.getTentaikhoan()));
		}else if(giangVienService.getGVbyID(tk.getTentaikhoan())!=null) {
			model.addAttribute("sv", giangVienService.getGVbyID(tk.getTentaikhoan()));
		}
		
		model.addAttribute("tk", tk);
		return "capnhatsv";
	}
	@PostMapping(value = "luupassword")
	public String doipass( HttpServletRequest request,Principal principal,RedirectAttributes redirAttrs) {
		TaiKhoan tk = taiKhoanService.getTKbyID(Long.parseLong(principal.getName()));
		String passcu = request.getParameter("pass_cu");
		
		String passmoi = (String) request.getParameter("pass_moi");
		
		if(passwordEncoder.matches(passcu, tk.getMatkhau())) {
			tk.setMatkhau(passwordEncoder.encode(passmoi));
			taiKhoanService.editTaiKhoan(tk);
			return "redirect:/capnhatmatkhau";
		}else {
			redirAttrs.addFlashAttribute("error","error");
			return "redirect:/capnhatmatkhau";
		}
	}
	
	@GetMapping(value = "/login")
    public String showFormLogin(Principal principal,Model model) {
		return principal!=null? "redirect:/" : FORM_LOGIN;
    }
}
