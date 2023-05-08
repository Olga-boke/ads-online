package com.skypro.adsonline.repository;

import com.skypro.adsonline.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(value = "select * from comments where ad_id = :ad_id", nativeQuery = true)
    List<CommentEntity> findByAdId(@Param("ad_id") int adId);
}
