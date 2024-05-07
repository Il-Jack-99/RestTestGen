package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.OperationCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationCoverageRepository extends JpaRepository<OperationCoverage, Long> {
}