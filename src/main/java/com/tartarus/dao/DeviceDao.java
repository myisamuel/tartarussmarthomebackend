package com.tartarus.dao;

import com.tartarus.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDao extends JpaRepository<Device,Integer> {
    Device findById(int id);
}
