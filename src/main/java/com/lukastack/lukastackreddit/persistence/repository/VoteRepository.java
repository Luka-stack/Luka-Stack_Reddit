package com.lukastack.lukastackreddit.persistence.repository;

import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import com.lukastack.lukastackreddit.persistence.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    Optional<VoteEntity> findTopByPostAndUserOrderByVoteIdDesc(PostEntity post, UserEntity user);
}
