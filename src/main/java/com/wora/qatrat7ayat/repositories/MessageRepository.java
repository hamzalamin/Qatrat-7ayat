package com.wora.qatrat7ayat.repositories;

import com.wora.qatrat7ayat.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender.email = :senderEmail AND m.receiver.id = :receiverId) OR " +
            "(m.sender.id = :receiverId AND m.receiver.email = :senderEmail) " +
            "ORDER BY m.timestamp ASC")
    List<Message> findMessagesBetween(@Param("senderEmail") String senderEmail, @Param("receiverId") Long receiverId);

}