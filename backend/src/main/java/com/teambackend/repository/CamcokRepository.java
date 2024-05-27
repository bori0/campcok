package com.teambackend.repository;


import com.teambackend.domain.Gocamping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CamcokRepository extends JpaRepository<Gocamping, String>, JpaSpecificationExecutor<Gocamping> {
    //단일 조회
    @Query("select d from Gocamping d where d.contentId = :contentId")
    Optional<Gocamping> selectOne(@Param("contentId") String contentId);

    //리스트 조회
    @Query("select d from Gocamping d")
    Page<Gocamping> selectList(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%도심%'")
    Page<Gocamping> selectdowntown(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%호수%'")
    Page<Gocamping> selectlake(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%강%'")
    Page<Gocamping> selectriver(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%계곡%'")
    Page<Gocamping> selectvalley(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%숲%'")
    Page<Gocamping> selectforest(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%산%'")
    Page<Gocamping> selectmountain(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%섬%'")
    Page<Gocamping> selectisland(Pageable pageable);

    @Query("select d FROM Gocamping d where d.lctCl Like '%해변%'")
    Page<Gocamping> selectbeach(Pageable pageable);

    @Query("select d from Gocamping d where d.facltNm like %:facltNm%")
    Page<Gocamping> findByFacltNmLike(@Param("facltNm") String facltNm, Pageable pageable);

    @Query("SELECT d FROM Gocamping d WHERE (:doNm = '' OR d.doNm LIKE %:doNm%) AND (:sigunguNm = '' OR d.sigunguNm LIKE %:sigunguNm%)")
    Page<Gocamping> findByRegionLike(@Param("doNm") String doNm, @Param("sigunguNm") String sigunguNm, Pageable pageable);

    @Query("SELECT d FROM Gocamping d WHERE (:doNm = '' OR d.doNm LIKE %:doNm%) AND (:sigunguNm = '' OR d.sigunguNm LIKE %:sigunguNm%)")
    List<Gocamping> findByRegionLike2(@Param("doNm") String doNm, @Param("sigunguNm") String sigunguNm);










    @Query("SELECT d FROM Gocamping d WHERE " +
            "(:lctCls IS NULL OR d.lctCl IN :lctCls) AND " +
            "(:induties IS NULL OR d.induty IN :induties) AND " +
            "(:siteBottomCl1 IS NULL OR d.siteBottomCl1 >= :siteBottomCl1) AND " +
            "(:siteBottomCl2 IS NULL OR d.siteBottomCl2 >= :siteBottomCl2) AND " +
            "(:siteBottomCl3 IS NULL OR d.siteBottomCl3 >= :siteBottomCl3) AND " +
            "(:siteBottomCl4 IS NULL OR d.siteBottomCl4 >= :siteBottomCl4) AND " +
            "(:siteBottomCl5 IS NULL OR d.siteBottomCl5 >= :siteBottomCl5) AND " +
            "(:themaEnvrnCls IS NULL OR d.themaEnvrnCl in :themaEnvrnCls) AND " +
            "(:sbrsCls IS NULL OR d.sbrsCl LIKE CONCAT('%', :sbrsCls, '%')) AND " +
            "(:trlerAcmpnyAt IS NULL OR d.trlerAcmpnyAt = :trlerAcmpnyAt) AND " +
            "(:caravAcmpnyAt IS NULL OR d.caravAcmpnyAt = :caravAcmpnyAt) AND " +
            "(:animalCmgCls IS NULL OR d.animalCmgCl in :animalCmgCls)")
    Page<Gocamping> findByFilters(
            @Param("lctCls") List<String>  lctCls,
            @Param("induties") List<String> induties,
            @Param("siteBottomCl1") Integer siteBottomCl1,
            @Param("siteBottomCl2") Integer siteBottomCl2,
            @Param("siteBottomCl3") Integer siteBottomCl3,
            @Param("siteBottomCl4") Integer siteBottomCl4,
            @Param("siteBottomCl5") Integer siteBottomCl5,
            @Param("themaEnvrnCls") List<String>  themaEnvrnCls,
            @Param("sbrsCls") List<String>  sbrsCls,
            @Param("trlerAcmpnyAt") String trlerAcmpnyAt,
            @Param("caravAcmpnyAt") String caravAcmpnyAt,
            @Param("animalCmgCls") String animalCmgCls,
            Pageable pageable);
}
