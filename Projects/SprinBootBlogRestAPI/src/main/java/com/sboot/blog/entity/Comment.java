package com.sboot.blog.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="body")
    private String body;

    // fetch when needed = Lazy
    // fetch immediately
    @ManyToOne(fetch = FetchType.LAZY) // one post has multiple user
    @JoinColumn(name = "post_id" , nullable = false) // use as foregin key in comments table
    private Post post;
}
