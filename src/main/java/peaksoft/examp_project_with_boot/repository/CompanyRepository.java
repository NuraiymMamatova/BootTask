package peaksoft.examp_project_with_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}