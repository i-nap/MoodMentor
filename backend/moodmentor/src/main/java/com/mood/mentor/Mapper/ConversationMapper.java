package com.mood.mentor.Mapper;

import com.mood.mentor.Entities.Conversation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConversationMapper {
    @Select("SELECT * FROM conversation WHERE (user1_id = #{user1_id} AND user2_id = #{user2_id}) OR (user1_id = #{user2_id} AND user2_id = #{user1_id}) ORDER BY created_at DESC LIMIT 50")
    List<Conversation> findConversation(Long user1_id, Long user2_id);

    @Insert("INSERT INTO conversation (user1_id, user2_id, user1_message, user2_message,current_timestamp) VALUES (#{user1_id}, #{user2_id}, #{user1_message}, #{user2_message},current_timestamp)")
    void insertConversation(Conversation conversation);

    @Select("Select user1_id , user2_id from conversation where (user1_id = #{user1_id} AND user2_id = #{user2_id}) OR (user1_id = #{user2_id} AND user2_id = #{user1_id})")
    List<Integer> findUsersConversation(Long user1_id, Long user2_id);


}
