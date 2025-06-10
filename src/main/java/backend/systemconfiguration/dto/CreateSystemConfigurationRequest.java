package backend.systemconfiguration.dto;

public record CreateSystemConfigurationRequest(
    String name, 

    String value
) {  
}
