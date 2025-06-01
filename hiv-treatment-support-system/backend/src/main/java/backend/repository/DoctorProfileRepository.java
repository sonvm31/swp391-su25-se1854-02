package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.DoctorProfile;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Integer> {
    DoctorProfile findDoctorProfileById(int id);
}
