package com.gtsp.gtsp.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gtsp.gtsp.dto.DeviceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "device")
public class Device {
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private DeviceType deviceType ;
    private String description;
    @ManyToOne(fetch = LAZY)
    private User user;
}
