package io.resttestgen.database.Writer;

import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestSequence;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.TestInteractionRepository;
import io.resttestgen.database.Repository.TestSequenceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportWriterDb {

    private Job job;

    public ReportWriterDb(){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();
    }

    public void write(io.resttestgen.core.testing.TestSequence testSequence){


        TestSequenceRepository testSequenceRepository = new TestSequenceRepository();
        TestInteractionRepository testInteractionRepository = new TestInteractionRepository();

        TestSequence ts = new TestSequence();

        //Init TestSequence
        ts.setJob(job);
        ts.setName(testSequence.getName().substring(0,testSequence.getName().indexOf('-')));
        ts.setGenerator(testSequence.getGenerator());
        ts.setGeneratedAt(testSequence.getGeneratedAt());

        List<io.resttestgen.core.testing.TestInteraction> testInteractionsList = testSequence.getTestInteractions();
        Set<TestInteraction> testInteractions = new HashSet<>();

        for(io.resttestgen.core.testing.TestInteraction ti : testInteractionsList){
            ti.getRequestMethod();
            ti.getRequestURL();
            ti.getRequestHeaders();
            ti.getRequestBody();
            ti.getRequestSentAt();
            ti.getResponseProtocol();
            ti.getResponseStatusCode();
            ti.getResponseHeaders();
            ti.getResponseBody();
            ti.getResponseReceivedAt();
            ti.getExecutionTime().toString();
            ti.getTestStatus();
        }

        testSequenceRepository.add(ts);
        testSequenceRepository.close();






    }


}
