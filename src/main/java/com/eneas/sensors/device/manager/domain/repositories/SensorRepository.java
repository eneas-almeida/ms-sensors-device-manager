package com.eneas.sensors.device.manager.domain.repositories;

import com.eneas.sensors.device.manager.domain.models.Sensor;
import com.eneas.sensors.device.manager.domain.models.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
