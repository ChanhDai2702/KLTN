package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.ChuyenNganh;

@Repository
public interface ChuyenNganhRepository extends CrudRepository<ChuyenNganh, Long> {
	List<ChuyenNganh> findAll();
	
	@Query(value = "select * from chuyennganhs where tennganh =? " ,nativeQuery = true)
	List<ChuyenNganh> getChuyenNganhByName(String name);
	@Query(value = "select * from chuyennganh cn join khoa k on k.makhoa=cn.khoa_id where khoa_id=:id",nativeQuery = true)
	List<ChuyenNganh> getChuyenNganhByKhoaID(Long id);
}
