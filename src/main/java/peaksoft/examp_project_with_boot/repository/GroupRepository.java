package peaksoft.examp_project_with_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}