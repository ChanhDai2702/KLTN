package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.SinhVienService;
import com.example.demo.service.impl.SinhVienImpl;

public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(date.getDay());
		
		
	}
	
}
