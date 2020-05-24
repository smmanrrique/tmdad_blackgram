package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {

    User findById(int id);

    User findByUserName(String userName);

    List<User> findAll();

    List<User> findAllByUserNameAndPassword(String userName, String password);

//    User findByUserName(String username);

    @Query("SELECT u.admin FROM User u WHERE u.userName=:userName")
    boolean isAdmin(@Param("userName") String userName);

//    List<Article> findByTitleAndCategory(String title, String category);

//    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);

}
