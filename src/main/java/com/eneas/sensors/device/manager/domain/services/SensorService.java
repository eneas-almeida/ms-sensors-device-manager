package com.eneas.sensors.device.manager.domain.services;

import com.eneas.sensors.device.manager.api.dtos.SensorDetailOutput;
import com.eneas.sensors.device.manager.api.dtos.SensorInput;
import com.eneas.sensors.device.manager.api.dtos.SensorOutput;
import com.eneas.sensors.device.manager.commons.IdGenerator;
import com.eneas.sensors.device.manager.domain.models.Sensor;
import com.eneas.sensors.device.manager.domain.models.SensorId;
import com.eneas.sensors.device.manager.domain.repositories.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    @Transactional
    public SensorOutput create(SensorInput input) {
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

        sensor = sensorRepository.saveAndFlush(sensor);

        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .build();
    }

    public SensorDetailOutput getOneWithDetail(TSID sensorId) {
        Optional<Sensor> sensorOpt = sensorRepository.findById(new SensorId(sensorId));

        if (sensorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Sensor sensor = sensorOpt.get();

        return SensorDetailOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .build();
    }

    public SensorOutput update(TSID sensorId, SensorInput input) {
        Optional<Sensor> sensorOpt = sensorRepository.findById(new SensorId(sensorId));

        if (sensorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Sensor sensor = sensorOpt.get();

        sensor.setName(input.getName());
        sensor.setIp(input.getIp());
        sensor.setLocation(input.getLocation());
        sensor.setProtocol(input.getProtocol());
        sensor.setModel(input.getModel());

        sensor = sensorRepository.saveAndFlush(sensor);

        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .createdAt(sensor.getCreatedAt())
                .build();
    }

    public void delete(TSID sensorId) {
        Optional<Sensor> sensorOpt = sensorRepository.findById(new SensorId(sensorId));

        if (sensorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Sensor sensor = sensorOpt.get();

        sensorRepository.delete(sensor);
    }

    public List<SensorOutput> listAll() {
        List<Sensor> sensors = sensorRepository.findAll();

        List<SensorOutput> list = sensors.stream()
                .map(sensor -> SensorOutput.builder()
                        .id(sensor.getId().getValue())
                        .name(sensor.getName())
                        .ip(sensor.getIp())
                        .location(sensor.getLocation())
                        .protocol(sensor.getProtocol())
                        .model(sensor.getModel())
                        .createdAt(sensor.getCreatedAt())
                        .build())
                .toList();

        return list;
    }
}
