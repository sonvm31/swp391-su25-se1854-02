package backend.systemconfiguration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.systemconfiguration.model.SystemConfiguration;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, Long> {
}