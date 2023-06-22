package com.tartarus.controller;

import com.tartarus.dao.DeviceDao;
import com.tartarus.dao.PremissionDao;
import com.tartarus.dao.UserDao;
import com.tartarus.model.DAOUser;
import com.tartarus.model.Device;
import com.tartarus.model.Premission;
import com.tartarus.util.PremissionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/premission")
@CrossOrigin(origins = "*")
public class PremissionController {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PremissionDao premissionDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PremissionHandler premissionHandler;

    @RequestMapping(value = "/addpremission/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> addPremission(@RequestBody DAOUser targetUser, @PathVariable("id")int id, HttpServletRequest request){

        if(premissionHandler.isPremitted(request,id)){
            Premission premission = new Premission();
            Device device = deviceDao.findById(id);
            DAOUser user = userDao.findByUsername(targetUser.getUsername());
            logger.info("{}",user);
            premission.setUser(user);
            premission.setDeleteFlag(false);
            premission.setDevice(device);
            premissionDao.save(premission);
            return new ResponseEntity<>(premission, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(premissionHandler.messageNotPremitted(),HttpStatus.FORBIDDEN);
        }
    }

    //@RequestMapping(value = "/deletepremission/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("/deletepremission/{id}")
    public ResponseEntity<?> deletePremission(@RequestBody DAOUser user, @PathVariable("id")int id,HttpServletRequest request){
        if(premissionHandler.isPremitted(request,id)){
            DAOUser targetUser = userDao.findByUsername(user.getUsername());
            Premission premission = premissionDao.fetchByUserAndDevice(targetUser.getId(),id);
            premission.setDeleteFlag(true);
            premissionDao.save(premission);
            return new ResponseEntity<>(premission,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(premissionHandler.messageNotPremitted(),HttpStatus.FORBIDDEN);
        }
    }
}
