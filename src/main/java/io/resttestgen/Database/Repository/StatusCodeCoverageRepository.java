package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.StatusCodeCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCodeCoverageRepository extends JpaRepository<StatusCodeCoverage, Long> {
}