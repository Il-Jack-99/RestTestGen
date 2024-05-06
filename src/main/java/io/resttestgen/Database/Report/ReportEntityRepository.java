package io.resttestgen.Database.Report;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportEntityRepository extends JpaRepository<ReportEntity, Long> {
}
