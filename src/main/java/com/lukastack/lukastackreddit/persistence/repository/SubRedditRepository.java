package com.lukastack.lukastackreddit.persistence.repository;

import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubRedditRepository extends JpaRepository<SubRedditEntity, Long> {

    Optional<SubRedditEntity> findByName(String subRedditName);
}
