package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.Odg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdgRepository extends JpaRepository<Odg, Long> {
}