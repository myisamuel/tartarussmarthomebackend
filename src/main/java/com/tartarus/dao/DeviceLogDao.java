package com.tartarus.dao;

import com.tartarus.model.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceLogDao extends JpaRepository<DeviceLog,Integer> {

    @Query(nativeQuery = true , value = "SELECT * FROM device_log WHERE id_device = :idDevice")
    List<DeviceLog> getDeviceLogFromDevice(int idDevice);
}
