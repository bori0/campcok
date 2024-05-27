package com.teambackend.specifications;


import com.teambackend.domain.Gocamping;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class DetailSpecifications {

    public static Specification<Gocamping> lctClsIn(List<String> lctCls) {
        return (root, query, criteriaBuilder) -> {
            if (lctCls == null || lctCls.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참
            } else {
                return criteriaBuilder.or(
                        lctCls.stream()
                                .map(cls -> criteriaBuilder.like(root.get("lctCl"), "%" + cls + "%"))
                                .toArray(Predicate[]::new)
                );
            }
        };
    }

    public static Specification<Gocamping> indutiesIn(List<String> induties) {
        return (root, query, criteriaBuilder) -> {
            if (induties == null || induties.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return root.get("induty").in(induties);
            }
        };
    }

    public static Specification<Gocamping> siteBottomClGreaterThanOrEqualTo(Integer value, String fieldName) {
        return (root, query, criteriaBuilder) -> {
            if (value != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), value);
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참
            }
        };
    }

    //or연산
    public static Specification<Gocamping> themaEnvrnClsIn(List<String> themaEnvrnCls) {
        return (root, query, criteriaBuilder) -> {
            if (themaEnvrnCls == null || themaEnvrnCls.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참
            } else {
                return criteriaBuilder.or(
                        themaEnvrnCls.stream()
                                .map(cls -> criteriaBuilder.like(root.get("themaEnvrnCl"), "%" + cls + "%"))
                                .toArray(Predicate[]::new)
                );
            }
        };
    }


    //and연산
    public static Specification<Gocamping> sbrsClsIn(List<String> sbrsCls) {
        return (root, query, criteriaBuilder) -> {
            if (sbrsCls == null || sbrsCls.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참
            } else {
                Predicate[] predicates = sbrsCls.stream()
                        .map(cls -> criteriaBuilder.like(root.get("sbrsCl"), "%" + cls + "%"))
                        .toArray(Predicate[]::new);
                return criteriaBuilder.and(predicates);
            }
        };
    }

    public static Specification<Gocamping> trlerAcmpnyAtEqual(String trlerAcmpnyAt) {
        return (root, query, criteriaBuilder) -> {
            if (trlerAcmpnyAt == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.equal(root.get("trlerAcmpnyAt"), trlerAcmpnyAt);
            }
        };
    }

    public static Specification<Gocamping> caravAcmpnyAtEqual(String caravAcmpnyAt) {
        return (root, query, criteriaBuilder) -> {
            if (caravAcmpnyAt == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.equal(root.get("caravAcmpnyAt"), caravAcmpnyAt);
            }
        };
    }

    public static Specification<Gocamping> animalCmgClsLike(String animalCmgCls) {
        return (root, query, criteriaBuilder) -> {
            if (animalCmgCls == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.like(root.get("animalCmgCl"),  animalCmgCls + "%");
            }
        };
    }

    public static Specification<Gocamping> regionFilteredIn(List<Gocamping> globalRegionFilteredData) {
        return (root, query, criteriaBuilder) -> {
            if (globalRegionFilteredData == null || globalRegionFilteredData.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 무조건 참
            } else {
                // globalRegionFilteredData 리스트의 ID를 추출하여 IN 조건으로 필터링
                List<String> ids = globalRegionFilteredData.stream()
                        .map(Gocamping::getContentId) // DataEntity의 ID 필드가 contentId라고 가정
                        .collect(Collectors.toList());
                return root.get("contentId").in(ids);
            }
        };
    }
}
