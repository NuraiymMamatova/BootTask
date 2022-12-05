package peaksoft.examp_project_with_boot.service;

import peaksoft.examp_project_with_boot.entity.Company;
import peaksoft.examp_project_with_boot.entity.Student;

import java.util.List;

public interface CompanyService {

    void saveCompany(Company company);

    void deleteCompany(Long id);

    void updateCompany(Company company);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

}
