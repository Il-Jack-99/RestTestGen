package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.ParameterCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterCoverageRepository extends JpaRepository<ParameterCoverage, Long> {
}