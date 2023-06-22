package com.tartarus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "command_queue")
public class CommandQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommand;

    @Column(nullable = false)
    private String command;

    @ManyToOne
    @JoinColumn(name = "idDevice")
    @JsonIgnore
    private Device device;

    @Column(nullable = false)
    private boolean deleteFlag;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCommand() {
        return command;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public String getRunTimeCommand() {
        return command;
    }

    public void setRunTimeCommand(String runTimeCommand) {
        this.command = command;
    }
}
