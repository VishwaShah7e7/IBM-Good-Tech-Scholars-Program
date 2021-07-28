package com.gtsp.gtsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gtsp.gtsp.dto.DeviceDto;
import com.gtsp.gtsp.model.Device;
import com.gtsp.gtsp.repository.DeviceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class DeviceRegistrationService {

	private final DeviceRepository deviceRepository;
    //private final DeviceMapper deviceMapper;

    @Transactional
    public DeviceDto save(DeviceDto deviceDto) {
        Device save = deviceRepository.save(mapDtoToSubreddit(deviceDto));
        deviceDto.setId(save.getId());
        return deviceDto;
    }
    
    private Device mapDtoToSubreddit(DeviceDto deviceDto) {
    	return Device.builder().deviceType(deviceDto.getDeviceType()).description(deviceDto.getDescription()).build();
    }

    /*@Transactional
    public List<DeviceDto> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    /*public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }*/
}
