package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.Regimen;
import backend.repository.RegimenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReginmenService {
    private final RegimenRepository regimenRepository;

    public Optional<Regimen> getRegimenById(int id) {
        return regimenRepository.findById(id);
    }
}
