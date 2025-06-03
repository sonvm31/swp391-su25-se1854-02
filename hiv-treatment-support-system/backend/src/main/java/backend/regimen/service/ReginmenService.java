package backend.regimen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.healthrecord.repository.HealthRecordRepository;
import backend.regimen.dto.CreateRegimenRequest;
import backend.regimen.dto.UpdateRegimenRequest;
import backend.regimen.model.Regimen;
import backend.regimen.repository.RegimenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReginmenService {
    private final RegimenRepository regimenRepository;
    private final HealthRecordRepository healthRecordRepository;

    // Tạo phác đồ 
    public String create(CreateRegimenRequest request) {
        var regimen = Regimen.builder()
        .regimenName(request.regimenName())
        .components(request.components())
        .description(request.description())
        .indications(request.indications())
        .contradications(request.contradications())
        .healthRecord(healthRecordRepository.findById(request.recordId()).get())
        .build();
        regimenRepository.save(regimen);

        return "Regimen created successfully with ID: " + regimen.getId() + ".";
    }

    // Xem danh sách phác đồ
    public List<Regimen> list() {
        return regimenRepository.findAll();
    }

    // Xem chi tiết phác đồ
    public Regimen get(int id) {
        return regimenRepository.findById(id).get();
    }

    // Chỉnh sửa phác đồ
    public String update(int id, UpdateRegimenRequest request) {
        Regimen regimen = regimenRepository.findById(id).orElseThrow(() -> new RuntimeException("Regimen not found with ID: " + id + "."));
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
        if (!regimenRepository.existsById(id)) {
            throw new RuntimeException("Regimen not foud with ID: " + id + ".");
        }

        regimenRepository.delete(regimenRepository.findById(id).get());
        
        return "Regimen deleted successfully with ID: " + id + ".";
    }
}
