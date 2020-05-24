package com.tmda.chatapp.repositories;

import com.tmda.chatapp.DTO.TimeTopicDTO;
import com.tmda.chatapp.DTO.TopTopicDTO;
import com.tmda.chatapp.DTO.UserTopicDTO;
import com.tmda.chatapp.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Topic findByName(String topicName);

    List<Topic> findAll();

    @Query(value = "select ROW_NUMBER() OVER(ORDER BY count(t) desc) AS position, " +
            " t.name as name, count(t) as count" +
            " from topics t group by t.name "+
            "order by count desc\n" +
            "limit 10", nativeQuery = true)
    List<TopTopicDTO> findTopTopic();

    @Query(value = "select name " +
            "     ,to_char(created, 'MM-DD-YYYY HH24:MI:SS') as timestamp " +
            "     ,to_char(created, 'MM-DD-YYYY') AS date"  +
            "     ,EXTRACT(DAY FROM created) AS day" +
            "     ,EXTRACT(HOUR FROM created) AS hour " +
            "     ,EXTRACT(MINUTE FROM created) AS minute " +
            "from topics " +
            "order by timestamp desc", nativeQuery = true)
    List<TimeTopicDTO> findTimeTopic();

    @Query(value = "select ROW_NUMBER() OVER(ORDER BY count(topics_id) desc) AS position," +
            "users_id as id, u.user_name as userName, count(topics_id) as count\n" +
            "from messages_topics\n" +
            "left join messages m on messages_topics.messages_id = m.id\n" +
            "left join users u on m.users_id = u.id\n" +
            "group by users_id, u.user_name\n" +
            "order by count desc\n" +
            "limit 10", nativeQuery = true)
    List<UserTopicDTO> findUserTopic();



}
