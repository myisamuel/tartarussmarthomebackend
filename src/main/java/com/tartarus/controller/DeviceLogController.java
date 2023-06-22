package com.tartarus.controller;

import com.tartarus.dao.DeviceDao;
import com.tartarus.dao.DeviceLogDao;
import com.tartarus.model.Device;
import com.tartarus.model.DeviceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:3000",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/log")
public class DeviceLogController {

    @Autowired
    DeviceDao deviceDao;
    @Autowired
    DeviceLogDao deviceLogDao;

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    ResponseEntity<?> addLog(@RequestBody Map<String,Object> logRequest, @PathVariable("id") int id){

        DeviceLog log = new DeviceLog();
        Device device = deviceDao.findById(id);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
        log.setVolt((int) logRequest.get("volt"));
        log.setWatt((int) logRequest.get("watt"));
        log.setTime(LocalDateTime.parse((String)logRequest.get("time"),parser));
        log.setDevice(device);
        deviceLogDao.save(log);

        return new ResponseEntity<DeviceLog>(log, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    ResponseEntity<?> getLog(@PathVariable("id")int id){

        return new ResponseEntity<>(deviceLogDao.getDeviceLogFromDevice(id),HttpStatus.OK);
    }


}
