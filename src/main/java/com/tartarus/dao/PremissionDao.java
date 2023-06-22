package com.tartarus.dao;

import com.tartarus.model.Premission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PremissionDao extends JpaRepository<Premission,Integer> {
    Premission findById(int id);

    @Query(nativeQuery = true, value = "SELECT * from premission WHERE id_user= :idUser AND id_device= :idDevice")
    Premission fetchByUserAndDevice(long idUser,int idDevice);
}
