package backend.systemconfiguration.dto;

public record UpdateSystemConfigurationRequest(
    String name, 

    String value
) {  
}
