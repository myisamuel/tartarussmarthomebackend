package com.tartarus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="premission")
public class Premission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPremission;

    @ManyToOne
    @JoinColumn(name = "idDevice",nullable = false)
    @JsonIgnore
    private Device device;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    @JsonIgnore
    private DAOUser user;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean deleteFlag;

    public Device getDevice() {
        return device;
    }

    public DAOUser getUser() {
        return user;
    }

    public int getIdPremission() {
        return idPremission;
    }

    public void setIdPremission(int idPremission) {
        this.idPremission = idPremission;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
