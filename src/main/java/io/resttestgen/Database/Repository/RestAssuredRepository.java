package io.resttestgen.Database.Repository;

import io.resttestgen.Database.Model.RestAssured;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestAssuredRepository extends JpaRepository<RestAssured, Long> {
}