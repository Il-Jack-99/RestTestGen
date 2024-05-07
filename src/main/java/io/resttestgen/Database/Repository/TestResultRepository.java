package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}