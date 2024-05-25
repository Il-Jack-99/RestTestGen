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
import io.resttestgen.database.Model.PathCoverage;
import io.resttestgen.database.Repository.*;

import java.nio.file.Path;
import java.util.Map;

public class CoverageReportWriterDb {

    private Job job;

    private CoverageStatRepository coverageStatRepository;

    private OperationCoverageRepository operationCoverageRepository;

    private PathCoverageRepository pathCoverageRepository;

    private StatusCodeCoverageRepository statusCodeCoverageRepository;

    private final CoverageManager coverageManager;
    private final Configuration configuration = Environment.getInstance().getConfiguration();


    public CoverageReportWriterDb(CoverageManager coverageManager){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.coverageStatRepository = new CoverageStatRepository();
        this.coverageManager = coverageManager;
        this.operationCoverageRepository = new OperationCoverageRepository();
        this.pathCoverageRepository = new PathCoverageRepository();
        this.statusCodeCoverageRepository = new StatusCodeCoverageRepository();
    }

    public void writeSingleCoverage() {


        for (Coverage coverage : coverageManager.getCoverages()) {


            if(coverage.getClass().getSimpleName().equals("OperationCoverage")){
                //writeSingleOperationCoverage(coverage.getReportAsJsonObject());
            }
            else if(coverage.getClass().getSimpleName().equals("PathCoverage")){
                //writeSinglePathCoverage(coverage.getReportAsJsonObject());
            }
            else if(coverage.getClass().getSimpleName().equals("StatusCodeCoverage")){
                System.out.println("Entro in sc");
                writeSingleStatusCodeCoverage(coverage.getReportAsJsonObject());
            }


        }
    }

    private void writeSingleStatusCodeCoverage(JsonObject reportAsJsonObject) {
        JsonObject documented = reportAsJsonObject.getAsJsonObject("documented");
        saveStatusCodeCoverageData(documented, "documented", "PathCoverage");
        JsonObject documentedTested = reportAsJsonObject.getAsJsonObject("documentedTested");
        saveStatusCodeCoverageData(documentedTested, "documentedTested", "PathCoverage");
        JsonObject notDocumentedTested = reportAsJsonObject.getAsJsonObject("notDocumentedTested");
        saveStatusCodeCoverageData(notDocumentedTested, "notDocumentedTested", "PathCoverage");
        JsonObject notTested = reportAsJsonObject.getAsJsonObject("notTested");
        saveStatusCodeCoverageData(notTested, "notTested", "PathCoverage");
    }

    private void saveStatusCodeCoverageData(JsonObject data, String category, String covType) {

        if (data != null) {
            for (String endpointMethod : data.keySet()) {
                JsonArray statusCodes = data.getAsJsonArray(endpointMethod);
                String[] parts = endpointMethod.split(" ", 2);
                if (parts.length == 2) {
                    String method = parts[0];
                    String endpoint = parts[1];
                    for (JsonElement codeElement : statusCodes) {
                        String statusCode = codeElement.getAsString();

                        System.out.println("Category: "+category + " endpoint : "+endpoint + " method: "+method+" status Code "+statusCode + "\n");
                        // Ora puoi salvare i dati come richiesto, ad esempio:
                        //saveData(category, covType, method, endpoint, statusCode);
                    }
                }
            }
        }

    }


    private void writeSinglePathCoverage(JsonObject reportAsJsonObject){
        JsonArray documented = reportAsJsonObject.getAsJsonArray("documented");
        savePathCoverageData(documented, "documented", "PathCoverage");
        JsonArray documentedTested = reportAsJsonObject.getAsJsonArray("documentedTested");
        savePathCoverageData(documentedTested, "documentedTested", "PathCoverage");
        JsonArray notDocumentedTested = reportAsJsonObject.getAsJsonArray("notDocumentedTested");
        savePathCoverageData(notDocumentedTested, "notDocumentedTested", "PathCoverage");
        JsonArray notTested = reportAsJsonObject.getAsJsonArray("notTested");
        savePathCoverageData(notTested, "notTested", "PathCoverage");
    }

    private void savePathCoverageData(JsonArray data, String category, String covType) {


        if (data != null) {
            for (JsonElement element : data) {
                String endpoint = element.getAsString();

                PathCoverage pathCoverage = new PathCoverage();
                pathCoverage.setCategory(category);
                pathCoverage.setPath(endpoint);
                pathCoverage.setJob(job);

                pathCoverageRepository.add(pathCoverage);

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
