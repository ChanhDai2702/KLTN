package com.example.demo.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ChuyenNganh;
import com.example.demo.model.DangKyLopHocPhan;
import com.example.demo.model.LopHoc;
import com.example.demo.model.NienKhoa;
import com.example.demo.model.SinhVien;
import com.example.demo.service.DangKyLopHocPhanService;
import com.example.demo.service.NienKhoaService;
import com.example.demo.service.SinhVienService;

@Controller
public class HomeControllerWeb {
	@Autowired
	private SinhVienService sinhVienService;
	@Autowired
	private DangKyLopHocPhanService dangKyHPService;
	@Autowired
	private NienKhoaService nienKhoaService;
	@RequestMapping(value = {"/sinhvien/dashboard","/"})
	public String redirectHome(Model model,HttpServletRequest request,Principal principal) {
		SinhVien sv=sinhVienService.getSVbyID(Long.valueOf(principal.getName()));
		LopHoc lh=sv.getLopHoc();
		ChuyenNganh cn= lh.getChuyenNganh();
		model.addAttribute("sv",sv);
		model.addAttribute("lhsv",lh);
		model.addAttribute("cn",cn);
		return "home";
	}
	@RequestMapping(value = "/congnosinhvien")
	public String redirectCongNo(Model model,HttpServletRequest request,Principal principal) {
		SinhVien sv=sinhVienService.getSVbyID(Long.valueOf(principal.getName()));
		List<DangKyLopHocPhan> listDKHPBySVID=dangKyHPService.findAllLHPBySVID(sv.getMasv());
		float tongcongno=0;
		for(int j=0;j<listDKHPBySVID.size();j++) {
			tongcongno+=listDKHPBySVID.get(j).getDangkylophocphan().getHocphi();
			if(listDKHPBySVID.get(j).getTrangthaihocphi().equalsIgnoreCase("Đã thu")) {
				tongcongno=tongcongno-listDKHPBySVID.get(j).getDangkylophocphan().getHocphi();
			}


		}
		model.addAttribute("tongcongno",tongcongno);
		LopHoc lh=sv.getLopHoc();
		ChuyenNganh cn= lh.getChuyenNganh();
		model.addAttribute("sv",sv);
		model.addAttribute("lhsv",lh);
		model.addAttribute("cn",cn);
		List<DangKyLopHocPhan> listSVDKHPByNK=new ArrayList<DangKyLopHocPhan>();
		if(request.getParameter("HKnienKhoa")!=null) {
			listSVDKHPByNK=dangKyHPService.getAllLHPBYSVIDandNienKhoa(Long.valueOf(sv.getMasv()), Long.valueOf(request.getParameter("HKnienKhoa")));
			model.addAttribute("listSVDKHPByNK",listSVDKHPByNK);
			model.addAttribute("lastselected",Long.valueOf(request.getParameter("HKnienKhoa")));
		}
		
		List<NienKhoa> listNienKhoa=nienKhoaService.getAllNienKhoa();
		model.addAttribute("listNienKhoa",listNienKhoa);
	
		float congno=0;

		for(int i=0;i<listSVDKHPByNK.size();i++) {
			congno+=listSVDKHPByNK.get(i).getDangkylophocphan().getHocphi();
			if(listSVDKHPByNK.get(i).getTrangthaihocphi().equalsIgnoreCase("Đã thu")) {
				congno=congno-listSVDKHPByNK.get(i).getDangkylophocphan().getHocphi();
			}
			
			model.addAttribute("congno",congno);
			
			
		}
		return "congno";
	}
}
