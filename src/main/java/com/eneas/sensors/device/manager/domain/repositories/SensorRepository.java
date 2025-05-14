package com.eneas.sensors.device.manager.domain.repositories;

import com.eneas.sensors.device.manager.domain.models.Sensor;
import com.eneas.sensors.device.manager.domain.models.SensorId;
import io.hypersistence.tsid.TSID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
    Optional<Sensor> findById(SensorId sensorId);
}
