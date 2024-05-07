package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.PathCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathCoverageRepository extends JpaRepository<PathCoverage, Long> {
}