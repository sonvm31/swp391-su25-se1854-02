package backend.repository;

import backend.model.Regimen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RegimenRepository extends JpaRepository<Regimen, Integer> {
    public ArrayList<Regimen> findReginmenByRegimenID(int userID);
}
