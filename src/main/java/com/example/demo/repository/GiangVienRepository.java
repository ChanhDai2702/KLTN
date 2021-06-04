package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.GiangVien;

@Repository
public interface GiangVienRepository extends CrudRepository<GiangVien, Long> {
	@Query(value = "SELECT * FROM giangviens gv WHERE gv.tentaikhoan_gv like %name%",nativeQuery = true)
	public List<GiangVien> getGiangVienByName(String name);
	@Query(value = "SELECT * FROM giangviens gv WHERE gv.trangThai like %tt%",nativeQuery = true)
	List<GiangVien> getGiangVienByTT(String tt);
	List<GiangVien> findAll();
	
	

	@Query(value = "select * from giangvien gv join lophocphan lhp on lhp.giangvien_id=gv.magiangvien where lhp.malophocphan=:id",nativeQuery = true)
	public GiangVien getGVByIDMonHoc(Long id);
}
