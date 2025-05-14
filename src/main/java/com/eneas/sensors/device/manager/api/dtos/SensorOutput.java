package com.eneas.sensors.device.manager.api.dtos;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SensorOutput {
    private TSID id;
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Date createdAt;
}
