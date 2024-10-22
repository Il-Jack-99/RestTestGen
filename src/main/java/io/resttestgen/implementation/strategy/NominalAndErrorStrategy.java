package io.resttestgen.implementation.strategy;

import io.resttestgen.core.openapi.Operation;
import io.resttestgen.core.testing.Strategy;
import io.resttestgen.core.testing.TestRunner;
import io.resttestgen.core.testing.TestSequence;
import io.resttestgen.core.testing.operationsorter.OperationsSorter;
import io.resttestgen.database.Writer.CoverageReportWriterDb;
import io.resttestgen.database.Writer.ReportWriterDb;
import io.resttestgen.database.Writer.RestAssuredWriterDb;
import io.resttestgen.implementation.fuzzer.ErrorFuzzer;
import io.resttestgen.implementation.fuzzer.NominalFuzzer;
import io.resttestgen.implementation.operationssorter.GraphBasedOperationsSorter;
import io.resttestgen.implementation.oracle.StatusCodeOracle;
import io.resttestgen.implementation.writer.CoverageReportWriter;
import io.resttestgen.implementation.writer.RestAssuredWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public class NominalAndErrorStrategy extends Strategy {

    private CoverageReportWriterDb coverageReportWriterDb;
    private final ReportWriterDb reportWriterDb = new ReportWriterDb();  // Modificata per MongoDB
    private final RestAssuredWriterDb restAssuredWriterDb = new RestAssuredWriterDb();  // Modificata per MongoDB

    private static final Logger logger = LogManager.getLogger(NominalAndErrorStrategy.class);

    private final TestSequence globalNominalTestSequence = new TestSequence();

    public void start() {

        // Secondo l'ordine fornito dal grafo, esegui il nominal fuzzer
        OperationsSorter sorter = new GraphBasedOperationsSorter();
        while (!sorter.isEmpty()) {
            Operation operationToTest = sorter.getFirst();
            logger.debug("Testing operation " + operationToTest);
            NominalFuzzer nominalFuzzer = new NominalFuzzer(operationToTest);
            List<TestSequence> nominalSequences = nominalFuzzer.generateTestSequences(20);

            for (TestSequence testSequence : nominalSequences) {

                // Esegui la sequenza di test
                TestRunner testRunner = TestRunner.getInstance();
                testRunner.run(testSequence);

                // Valuta la sequenza con gli oracoli
                StatusCodeOracle statusCodeOracle = new StatusCodeOracle();
                statusCodeOracle.assertTestSequence(testSequence);

                // Salva il report della sequenza di test su MongoDB
                reportWriterDb.write(testSequence);

                // Scrivi il report RestAssured in MongoDB
                RestAssuredWriter restAssuredWriter = new RestAssuredWriter(testSequence);
                restAssuredWriterDb.addToNominalMap(restAssuredWriter.testAssuredFileName(), restAssuredWriter.testAssuredContent());
            }
            globalNominalTestSequence.append(nominalSequences);
            sorter.removeFirst();
        }

        // Salva i test nominali eseguiti in MongoDB
        restAssuredWriterDb.writeNominal();

        // Mantieni solo le interazioni di test riuscite nella sequenza
        globalNominalTestSequence.filterBySuccessfulStatusCode();

        // Genera i test di errore utilizzando il fuzzer di errore
        ErrorFuzzer errorFuzzer = new ErrorFuzzer(globalNominalTestSequence);
        errorFuzzer.generateTestSequences(10);

        // Salva i risultati dei test di errore in MongoDB
        restAssuredWriterDb.addErrorMap(errorFuzzer.getErrorMap());
        restAssuredWriterDb.writeError();
        restAssuredWriterDb.write();

        // Chiudi i repository di MongoDB
        reportWriterDb.closeAllRepository();
        restAssuredWriterDb.closeAllRepository();

        // Scrivi il report di copertura su MongoDB
        try {
            CoverageReportWriter coverageReportWriter = new CoverageReportWriter(TestRunner.getInstance().getCoverage());
            coverageReportWriter.write();

            coverageReportWriterDb = new CoverageReportWriterDb(TestRunner.getInstance().getCoverage());
            coverageReportWriterDb.writeSingleCoverage();
            coverageReportWriterDb.writeStats();
            coverageReportWriterDb.closeAllRepository();

        } catch (IOException e) {
            logger.warn("Could not write Coverage report to file.");
            e.printStackTrace();
        }
    }
}
