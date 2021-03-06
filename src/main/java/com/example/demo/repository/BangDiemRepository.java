package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.DiemTichLuyDTO;
import com.example.demo.model.BangDiem;

@Repository
public interface BangDiemRepository extends CrudRepository<BangDiem, Long> {
	@Query(value = "SELECT bd.* FROM bangdiem bd join "
			+ "sinhvien sv on bd.sinhvien_id = sv.masinhvien where bd.sinhvien_id =?",nativeQuery = true)
	List<BangDiem> getAllBangDiemByID(long id);

	@Query(value = "select  new HocKyDTO(lhp.hocky,lhp.nienkhoa) from bangdiem db join sinhvien sv on db.sinhvien_id = sv.masinhvien" + 
			"							join lophocphan lhp on db.lophocphan_id = lhp.malophocphan" + 
			"								where db.sinhvien_id =?",nativeQuery = true)
	List<DiemTichLuyDTO> getAllHocKyCoDiemID(long id);

	List<BangDiem> findAll();
	@Query(value = "select * from bangdiem bd join lophocphan lhp on bd.lophocphan_id=lhp.malophocphan join sinhvien sv on sv.masinhvien=bd.sinhvien_id join dangkyhocphan dkhp on sv.masinhvien=dkhp.sinhvien_id where bd.sinhvien_id=dkhp.sinhvien_id and bd.lophocphan_id=dkhp.lophocphan_id",nativeQuery = true)
	BangDiem getBangDiemSVBySVIDandMH();

}
