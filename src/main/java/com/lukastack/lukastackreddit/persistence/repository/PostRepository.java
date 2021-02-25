package com.lukastack.lukastackreddit.persistence.repository;

import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllBySubReddit(SubRedditEntity subRedditEntity);

    List<PostEntity> findAllByUser(UserEntity userEntity);
}
