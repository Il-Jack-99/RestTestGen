package io.resttestgen.database.Writer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.resttestgen.boot.Configuration;
import io.resttestgen.core.Environment;
import io.resttestgen.core.testing.Coverage;
import io.resttestgen.core.testing.coverage.CoverageManager;
//import io.resttestgen.core.testing.coverage.OperationCoverage;
import io.resttestgen.database.Model.CoverageStat;
import io.resttestgen.database.Model.OperationCoverage;
import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Repository.CoverageStatRepository;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.OperationCoverageRepository;

public class CoverageReportWriterDb {

    private Job job;

    private CoverageStatRepository coverageStatRepository;

    private OperationCoverageRepository operationCoverageRepository;

    private final CoverageManager coverageManager;
    private final Configuration configuration = Environment.getInstance().getConfiguration();


    public CoverageReportWriterDb(CoverageManager coverageManager){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.coverageStatRepository = new CoverageStatRepository();
        this.coverageManager = coverageManager;
        this.operationCoverageRepository = new OperationCoverageRepository();
    }

    public void writeSingleCoverage(){
        for(Coverage coverage : coverageManager.getCoverages()){
            //System.out.println("REPORT    COVERAGE    "+coverage.getClass().getSimpleName()+"/n");
            //System.out.println(coverage.getReportAsJsonObject());
            //System.out.println("/n");

            System.out.println("Sono in writeSingleCoverage");
            switch(coverage.getClass().getSimpleName()){
                case "OperationCoverage":
                    writeSingleOperationCoverage(coverage.getReportAsJsonObject());
            }
        }
    }

    private void writeSingleOperationCoverage(JsonObject reportAsJsonObject) {
        JsonArray documented = reportAsJsonObject.getAsJsonArray("documented");
        saveCoverageData(documented, "documented", "OperationCoverage");
        JsonArray documentedTested = reportAsJsonObject.getAsJsonArray("documentedTested");
        saveCoverageData(documentedTested, "documentedTested", "OperationCoverage");
        JsonArray notDocumentedTested = reportAsJsonObject.getAsJsonArray("notDocumentedTested");
        saveCoverageData(notDocumentedTested, "notDocumentedTested", "OperationCoverage");
        JsonArray notTested = reportAsJsonObject.getAsJsonArray("notTested");
        saveCoverageData(notTested, "notTested", "OperationCoverage");
    }

    private void saveCoverageData(JsonArray data, String category, String covType) {
        if (data != null) {
            for (JsonElement element : data) {
                String endpoint = element.getAsString();

                // Separare il metodo e l'endpoint
                String[] parts = endpoint.split(" ");
                if (parts.length == 2) {
                    String method = parts[0];
                    String path = parts[1];

                    // Estrai solo il percorso dall'endpoint
                    String[] pathParts = path.split("\\s*\\/");
                    if (pathParts.length > 0) {
                        path = "/" + pathParts[pathParts.length - 1];
                    }

                    if(covType == "OperationCoverage") {
                        OperationCoverage operationCoverage = new OperationCoverage();
                        operationCoverage.setCategory(category);
                        operationCoverage.setMethod(method);
                        operationCoverage.setEndpoint(path);
                        operationCoverage.setJob(job);

                        operationCoverageRepository.add(operationCoverage);
                    }

                }
            }
        }
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
