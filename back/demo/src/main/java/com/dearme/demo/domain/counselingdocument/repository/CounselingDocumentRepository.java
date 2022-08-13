package com.dearme.demo.domain.counselingdocument.repository;

import com.dearme.demo.domain.counselingdocument.entity.CounselingDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselingDocumentRepository extends JpaRepository<CounselingDocument, Long> {
    void deleteCounselingDocumentByUser_IdAndId(String userId, Long id);

    Optional<CounselingDocument> findById(Long id);
}