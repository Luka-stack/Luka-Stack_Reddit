package com.lukastack.lukastackreddit.persistence.entity;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postId;

    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;

    @Lob
    @Nullable
    private String description;

    private Integer voteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subId", referencedColumnName = "subId")
    private SubRedditEntity subReddit;
}
