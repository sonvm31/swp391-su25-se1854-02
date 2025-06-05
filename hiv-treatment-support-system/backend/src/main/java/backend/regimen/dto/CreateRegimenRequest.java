package backend.regimen.dto;

public record  CreateRegimenRequest(
    String regimenName,

    String components,

    String description,

    String indications,

    String contradications,
    
    int recordId
) {
}
