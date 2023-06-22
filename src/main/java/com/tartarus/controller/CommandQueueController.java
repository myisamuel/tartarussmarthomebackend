package com.tartarus.controller;

import com.tartarus.dao.CommandQueueDao;
import com.tartarus.dao.DeviceDao;
import com.tartarus.model.CommandQueue;
import com.tartarus.model.Device;
import com.tartarus.util.PremissionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;

@RestController
@CrossOrigin("*")
@RequestMapping("/command/device")
public class CommandQueueController {
    Logger logger = LoggerFactory.getLogger(CommandQueue.class);
    @Autowired
    CommandQueueDao commandQueueDao;

    @Autowired
    PremissionHandler premissionHandler;

    @Autowired
    DeviceDao deviceDao;

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    ResponseEntity<?> addCommand(@RequestBody CommandQueue commandQueue, @PathVariable("id") int idDevice, HttpServletRequest payloadData){

        ResponseEntity<?> response;
        logger.info("{}",payloadData);
        if(premissionHandler.isPremitted(payloadData,idDevice)) {
            commandQueue.setDeleteFlag(false);
            Device device = deviceDao.findById(idDevice);
            commandQueue.setDevice(device);
            response = new ResponseEntity<>(commandQueueDao.save(commandQueue), HttpStatus.CREATED);
        }else{
            response = premissionHandler.messageNotPremitted();
        }

        return response;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deletecommand(@PathVariable("id")int id){
        CommandQueue commandQueue = commandQueueDao.commandQueueById(id);

        ResponseEntity<?> result;
        if(commandQueue != null){
            commandQueue.setDeleteFlag(true);
            result = new ResponseEntity<>(commandQueueDao.save(commandQueue), HttpStatus.CREATED);
        }else{
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    ResponseEntity<?> getCommand(@PathVariable("id") int id){
        CommandQueue commandQueue = commandQueueDao.commandQueueById(id);
        return new ResponseEntity<>(commandQueue, HttpStatus.OK);
    }


}
