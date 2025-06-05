package backend.regimen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.regimen.dto.CreateRegimenRequest;
import backend.regimen.dto.UpdateRegimenRequest;
import backend.regimen.model.Regimen;
import backend.regimen.repository.RegimenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReginmenService {
    private final RegimenRepository regimenRepository;

    // Tạo phác đồ 
    public String create(CreateRegimenRequest request) {
        var regimen = Regimen.builder()
            .regimenName(request.regimenName())
            .components(request.components())
            .description(request.description())
            .indications(request.indications())
            .contradications(request.contradications())
            .build();
        regimenRepository.save(regimen);

        return "Regimen created successfully with ID: " + regimen.getId() + ".";
    }

    // Xem danh sách phác đồ
    public List<Regimen> list() {
        List<Regimen> regimens = regimenRepository.findAll();
        if (regimens.isEmpty()) 
            throw new RuntimeException("No regimen found.");

        return regimens;
    }

    // Xem chi tiết phác đồ
    public Regimen get(int id) {
        return regimenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Regimen not found with ID: " + id + "."));
    }

    // Chỉnh sửa phác đồ
    public String update(int id, UpdateRegimenRequest request) {
        Regimen regimen = regimenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Regimen not found with ID: " + id + "."));
        
        Optional.of(request.regimenName()).ifPresent(regimen::setRegimenName);
        Optional.of(request.components()).ifPresent(regimen::setComponents);
        Optional.of(request.description()).ifPresent(regimen::setDescription);
        Optional.of(request.indications()).ifPresent(regimen::setIndications);
        Optional.of(request.contradications()).ifPresent(regimen::setContradications);
        regimenRepository.save(regimen);

        return "Regimen updated successfully with ID: " + id + ".";
    }

    // Xóa phác đồ
    public String delete(int id) {
        regimenRepository.delete(regimenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Regimen not foud with ID: " + id + ".")));
        
        return "Regimen deleted successfully with ID: " + id + ".";
    }
}
