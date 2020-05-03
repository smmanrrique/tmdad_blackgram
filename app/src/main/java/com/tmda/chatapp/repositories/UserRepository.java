package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

    List<User> findAll();

//    List<Article> findByTitleAndCategory(String title, String category);

//    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);
}
