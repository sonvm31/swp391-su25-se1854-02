package backend.systemconfiguration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.systemconfiguration.dto.CreateSystemConfigurationRequest;
import backend.systemconfiguration.dto.UpdateSystemConfigurationRequest;
import backend.systemconfiguration.model.SystemConfiguration;
import backend.systemconfiguration.repository.SystemConfigurationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemConfigurationService {
    @Autowired
    SystemConfigurationRepository systemConfigurationRepository;

    // Tạo cấu hình hệ thống
    public String create(CreateSystemConfigurationRequest request) {
        var systemConfiguration = SystemConfiguration.builder()
            .name(request.name())
            .value(request.value())
            .build();
        systemConfigurationRepository.save(systemConfiguration);

        return "SYSTEM CONFIGURATION CREATED SUCCESSFULLY WITH ID: " + systemConfiguration.getId();
    }

    // Xem danh sách cấu hình hệ thống 
    public List<SystemConfiguration> list() {
        return systemConfigurationRepository.findAll();
    }

    // Xem chi tiết cấu hình hệ thống
    public SystemConfiguration get(Long id) {
        return systemConfigurationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SYSTEM CONFIGURATION FOUND WITH ID: " + id));
    }

   // Chỉnh cấu hình hệ thống
    public String update(long id, UpdateSystemConfigurationRequest request) {        
        SystemConfiguration systemConfiguration = systemConfigurationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SYSTEM CONFIGURATION FOUND WITH ID" + id));

        Optional.of(request.name()).ifPresent(systemConfiguration::setName);
        Optional.of(request.value()).ifPresent(systemConfiguration::setValue);
        
        return "SYSTEM CONFIGURATION UPDATED SUCCESSFULLY WITH ID: " + id;
    }   
 
    // Xóa cấu hình hệ thống
    public String delete(long id) {
        systemConfigurationRepository.delete(systemConfigurationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SYSTEM CONFIGURATION FOUND WITH ID: " + id)));
        
        return "SYSTEM CONFIGURATION DELETED SUCCESSFULLY WITH ID: " + id;
    }
}
