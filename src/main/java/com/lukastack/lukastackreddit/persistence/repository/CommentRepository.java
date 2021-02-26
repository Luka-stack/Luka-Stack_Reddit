package com.lukastack.lukastackreddit.persistence.repository;

import com.lukastack.lukastackreddit.persistence.entity.CommentEntity;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPost(PostEntity post);

    List<CommentEntity> findByUser(UserEntity user);
}
