package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.ParameterValueCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterValueCoverageRepository extends JpaRepository<ParameterValueCoverage, Long> {
}