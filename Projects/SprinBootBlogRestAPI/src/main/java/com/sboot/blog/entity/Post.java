package com.sboot.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Setter// it genreates all setter and getter and tostring and equalsandhashcode and requiresAllargsconstrutor
@AllArgsConstructor
@Getter
@NoArgsConstructor // we need for objects
@Entity // convert class into table
@Table(
        name="posts", // table name

        // if you want to make any field as unique then use this
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "title"
                })
        }
) //
public class Post {

    @Id // used for primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // make primary key this way
    private Long id;

    @Column(name = "title" , nullable = false) // give column name
    private String title;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "imageUrl" , nullable = false)
    private String imageUrl;

    @Column
    private Date dateCreated;

    @Column
    private Date dateUpdated;

    // cascade = CascadeType.ALL = when parent remove then child also remove
    // mappedBy = use when we want this bidirectional or one directional mapping and this comments field is not generated in post table
    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER) // one post has multiple user
    @JoinColumn(name = "user_id" , nullable = false) // use as foregin key in comments table
    private User users;

}
