package com.eneas.sensors.device.manager.api.dtos;

import lombok.Data;

@Data
public class SensorInput {
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
}