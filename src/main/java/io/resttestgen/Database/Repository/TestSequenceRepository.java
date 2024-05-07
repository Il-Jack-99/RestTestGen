package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.TestSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSequenceRepository extends JpaRepository<TestSequence, Long> {
}