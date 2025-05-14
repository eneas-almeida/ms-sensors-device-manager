package com.eneas.sensors.device.manager.api.controllers;

import com.eneas.sensors.device.manager.api.dtos.SensorInput;
import com.eneas.sensors.device.manager.commons.IdGenerator;
import com.eneas.sensors.device.manager.domain.models.Sensor;
import com.eneas.sensors.device.manager.domain.models.SensorId;
import com.eneas.sensors.device.manager.domain.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor create(@RequestBody SensorInput input) {
        Sensor sensor = Sensor
                .builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(true)
                .createdAt(new Date())
                .build();

        sensorRepository.saveAndFlush(sensor);

        return sensor;
    }
}
