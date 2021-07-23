package com.gtsp.gtsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtsp.gtsp.model.Device;
import com.gtsp.gtsp.model.RefreshToken;

public interface DeviceRepository extends JpaRepository<RefreshToken, Long>{

	Device save(Device device);

}
