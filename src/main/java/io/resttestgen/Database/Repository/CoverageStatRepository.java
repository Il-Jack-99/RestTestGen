package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.CoverageStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverageStatRepository extends JpaRepository<CoverageStat, Long> {
}