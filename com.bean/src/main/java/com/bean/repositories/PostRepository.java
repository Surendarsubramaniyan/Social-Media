package com.bean.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
