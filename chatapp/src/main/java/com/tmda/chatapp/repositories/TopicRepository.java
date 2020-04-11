package com.tmda.chatapp.repositories;

import com.tmda.chatapp.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Topic findByName(String topicName);

    Topic findByDescription(String topicName);

    List<Topic> findAll();

//    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);
}
