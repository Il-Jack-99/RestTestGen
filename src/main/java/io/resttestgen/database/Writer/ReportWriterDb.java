package io.resttestgen.database.Writer;

import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestSequence;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.TestInteractionRepository;
import io.resttestgen.database.Repository.TestSequenceRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportWriterDb {

    private Job job;

    private TestSequenceRepository testSequenceRepository;
    private TestInteractionRepository testInteractionRepository;

    public ReportWriterDb(){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.testSequenceRepository = new TestSequenceRepository();
        this.testInteractionRepository = new TestInteractionRepository();
    }

    public void write(io.resttestgen.core.testing.TestSequence testSequence){


        //TestSequenceRepository testSequenceRepository = new TestSequenceRepository();
        //TestInteractionRepository testInteractionRepository = new TestInteractionRepository();

        TestSequence ts = new TestSequence();

        //Init TestSequence
        ts.setJob(job);
        ts.setName(testSequence.getName().substring(0,testSequence.getName().indexOf('-')));
        ts.setGenerator(testSequence.getGenerator());
        ts.setGeneratedAt(testSequence.getGeneratedAt());

        List<io.resttestgen.core.testing.TestInteraction> testInteractionsList = testSequence.getTestInteractions();
        Set<TestInteraction> testInteractions = new HashSet<>();


       TestSequence createdTestSequence = testSequenceRepository.add(ts);



        for(io.resttestgen.core.testing.TestInteraction ti : testInteractionsList){
            TestInteraction testInteraction = new TestInteraction();

            testInteraction.setRequestMethod(ti.getRequestMethod().toString());
            testInteraction.setRequestUrl(ti.getRequestURL());
            testInteraction.setRequestHeader(ti.getRequestHeaders());
            testInteraction.setRequestBody(ti.getRequestBody());
            testInteraction.setRequestSentAt(ti.getRequestSentAt());
            testInteraction.setResponseProtocol(ti.getResponseProtocol());
            testInteraction.setResponseStatusCode(ti.getResponseStatusCode().toString());
            testInteraction.setResponseHeaders(ti.getResponseHeaders());
            testInteraction.setResponseBody(ti.getResponseBody());
            testInteraction.setResponseReceivedAt(ti.getResponseReceivedAt());
            //testInteraction.setExecutionTime(ti.getExecutionTime().toString());
            testInteraction.setTestStatus(ti.getTestStatus().toString());
            testInteraction.setSequence(createdTestSequence);

            testInteractionRepository.add(testInteraction);

            testInteractions.add(testInteraction);
        }










    }


}
