package com.eneas.sensors.device.manager.api.controllers;

import com.eneas.sensors.device.manager.api.dtos.SensorDetailOutput;
import com.eneas.sensors.device.manager.api.dtos.SensorInput;
import com.eneas.sensors.device.manager.api.dtos.SensorOutput;
import com.eneas.sensors.device.manager.domain.services.SensorService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        SensorOutput sensorOutput = sensorService.create(input);
        return sensorOutput;
    }

    @GetMapping
    public Page<SensorOutput> search(@PageableDefault Pageable pageable) {
        Page<SensorOutput> sensors = sensorService.search(pageable);
        return sensors;
    }

    @GetMapping("/{sensorId}/detail")
    @ResponseStatus(HttpStatus.OK)
    public SensorDetailOutput getOneWithDetail(@PathVariable TSID sensorId) {
        SensorDetailOutput sensorDetailOutput = sensorService.getOneWithDetail(sensorId);
        return sensorDetailOutput;
    }

    @PatchMapping("/{sensorId}")
    @ResponseStatus(HttpStatus.OK)
    public SensorOutput update(@PathVariable TSID sensorId, @RequestBody SensorInput input) {
        SensorOutput sensorOutput = sensorService.update(sensorId, input);
        return sensorOutput;
    }

    @DeleteMapping("/{sensorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        sensorService.delete(sensorId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SensorOutput> listAll() {
        return sensorService.listAll();
    }
}
