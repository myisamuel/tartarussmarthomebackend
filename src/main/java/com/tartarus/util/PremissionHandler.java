package com.tartarus.util;

import com.tartarus.config.JwtTokenUtil;
import com.tartarus.dao.PremissionDao;
import com.tartarus.dao.UserDao;
import com.tartarus.model.Device;
import com.tartarus.model.ErrorMessage;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class PremissionHandler {

    @Autowired
    ErrorMessage errorMessage;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDao userDao;

    @Autowired
    PremissionDao premissionDao;

    public boolean isPremitted(HttpServletRequest payloadData, int idDevice){

        boolean result = false;
        String header = payloadData.getHeader("Authorization");
        String token = StringUtils.hasText(header) ? header.substring(7) : null;
        String apiCallerUsername = jwtTokenUtil.getUsernameFromToken(token);
        long apiCallerId = userDao.findByUsername(apiCallerUsername).getId();

        if(premissionDao.fetchByUserAndDevice(apiCallerId,idDevice) != null && premissionDao.fetchByUserAndDevice(apiCallerId,idDevice).getIdPremission() != 0){

            result = true;

        }

        return result;

    }

    public ResponseEntity<?> messageNotPremitted(){
        errorMessage.setMessage("Not Premitted to operate device");
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }
}
