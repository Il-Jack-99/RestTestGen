package io.resttestgen.database.Writer;

import io.resttestgen.boot.Configuration;
import io.resttestgen.core.Environment;
import io.resttestgen.core.testing.Coverage;
import io.resttestgen.core.testing.coverage.CoverageManager;
import io.resttestgen.database.Model.CoverageStat;
import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Repository.CoverageStatRepository;
import io.resttestgen.database.Repository.JobRepository;

public class CoverageReportWriterDb {

    private Job job;

    private CoverageStatRepository coverageStatRepository;

    private final CoverageManager coverageManager;
    private final Configuration configuration = Environment.getInstance().getConfiguration();


    public CoverageReportWriterDb(CoverageManager coverageManager){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.coverageStatRepository = new CoverageStatRepository();
        this.coverageManager = coverageManager;
    }

    public void writeStats(){
        for(Coverage coverage : coverageManager.getCoverages()){
            CoverageStat coverageStat = new CoverageStat();
            coverageStat.setJob(job);
            coverageStat.setCoverageType(coverage.getClass().getSimpleName());
            coverageStat.setDocumented(coverage.getToTest());
            coverageStat.setDocumentedTested(coverage.getNumOfTestedDocumented());
            coverageStat.setNotDocumentedTested(coverage.getNumOfTestedNotDocumented());
            coverageStat.setRate(coverage.getCoverage());

            coverageStatRepository.add(coverageStat);

        }

        coverageStatRepository.close();


    }

}
