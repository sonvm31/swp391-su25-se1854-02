package backend.regimen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.regimen.dto.CreateRegimenRequest;
import backend.regimen.dto.UpdateRegimenRequest;
import backend.regimen.model.Regimen;
import backend.regimen.repository.RegimenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReginmenService {
    @Autowired
    private final RegimenRepository regimenRepository;

    // Create regimen
    public String create(CreateRegimenRequest request) {
        var regimen = Regimen.builder()
            .regimenName(request.regimenName())
            .components(request.components())
            .description(request.description())
            .indications(request.indications())
            .contradications(request.contradications())
            .build();
        regimenRepository.save(regimen);

        return "REGIMEN CREATED SUCCESSFULLY WITH ID: " + regimen.getId();
    }

    // List regimens
    public List<Regimen> list() {
        return regimenRepository.findAll();
    }

    //  Read regimen detail
    public Regimen get(long id) {
        return regimenRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO REGIMEN FOUND WITH ID: " + id));
    }

    // Update regimen
    public String update(long id, UpdateRegimenRequest request) {
        Regimen regimen = regimenRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO REGIMEN FOUND WITH ID: " + id));
        
        Optional.ofNullable(request.regimenName()).ifPresent(regimen::setRegimenName);
        Optional.ofNullable(request.components()).ifPresent(regimen::setComponents);
        Optional.ofNullable(request.description()).ifPresent(regimen::setDescription);
        Optional.ofNullable(request.indications()).ifPresent(regimen::setIndications);
        Optional.ofNullable(request.contradications()).ifPresent(regimen::setContradications);
        regimenRepository.save(regimen);

        return "REGIMEN UPDATED SUCCESSFULLY WITH ID: " + id;
    }

    // Delete regimen
    public String delete(long id) {
        regimenRepository.delete(regimenRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO REGIMEN FOUND WITH ID: " + id)));
        
        return "REGIMEN DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // List regimens by doctor ID
    public List<Regimen> getByDoctorId(long doctoId) {
        return regimenRepository.findByDoctorId(doctoId);
    }
}
