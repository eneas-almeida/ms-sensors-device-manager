package com.eneas.sensors.device.manager.api.dtos;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SensorDetailOutput {
    private TSID id;
    private String name;
}
