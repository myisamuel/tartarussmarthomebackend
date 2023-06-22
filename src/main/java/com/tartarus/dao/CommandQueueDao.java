package com.tartarus.dao;

import com.tartarus.model.CommandQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommandQueueDao extends JpaRepository<CommandQueue,Integer> {

    @Query(nativeQuery = true , value = "SELECT * FROM command_queue WHERE id_device = :idDevice AND delete_flag = false ORDER BY id_command ASC LIMIT 1")
    CommandQueue commandQueueById(@Param("idDevice")int idDevice);

}
