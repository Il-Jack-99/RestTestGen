package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.TestInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestInteractionRepository extends JpaRepository<TestInteraction, Long> {
}