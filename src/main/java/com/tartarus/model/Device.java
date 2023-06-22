package com.tartarus.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevice;

    @Column(nullable = false)
    private String name;

    private String runTime;

    private int voltSensitivity;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Premission> premission = new ArrayList<>();

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeviceLog> deviceLogs = new ArrayList<>();

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommandQueue> commandQueues = new ArrayList<>();

    public List<CommandQueue> getCommandQueues() {
        return commandQueues;
    }
    public List<Premission> getPremission() {
        return premission;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public int getVoltSensitivity() {
        return voltSensitivity;
    }

    public void setVoltSensitivity(int voltSensitivity) {
        this.voltSensitivity = voltSensitivity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DeviceLog> getDeviceLogs() {
        return deviceLogs;
    }

    public void setDeviceLogs(List<DeviceLog> deviceLogs) {
        this.deviceLogs = deviceLogs;
    }
}
