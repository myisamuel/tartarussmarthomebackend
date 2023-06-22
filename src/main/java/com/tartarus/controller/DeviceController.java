package com.tartarus.controller;

import com.tartarus.config.JwtTokenUtil;
import com.tartarus.dao.DeviceDao;
import com.tartarus.dao.UserDao;
import com.tartarus.model.DAOUser;
import com.tartarus.model.Device;
import com.tartarus.model.Premission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/getDevice", method = RequestMethod.GET)
    ResponseEntity<?> something()
    {
        List<Device> deviceList = deviceDao.findAll();
        return new ResponseEntity<>(deviceList,HttpStatus.OK);
    }

    @RequestMapping(value = "/setDevice", method = RequestMethod.POST)
    ResponseEntity<?> setDevice(@RequestBody Device device,HttpServletRequest data)
    {
        String header = data.getHeader("Authorization");
        String token = StringUtils.hasText(header) ? header.substring(7) : null;
        DAOUser user = userDao.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        Premission premission = new Premission();
        premission.setDevice(device);
        premission.setUser(user);
        device.getPremission().add(premission);
        return new ResponseEntity<>(deviceDao.save(device),HttpStatus.CREATED);
    }

}
