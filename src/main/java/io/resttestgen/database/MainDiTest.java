package io.resttestgen.database;

import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestSequence;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.TestInteractionRepository;
import io.resttestgen.database.Repository.TestSequenceRepository;

import java.util.HashSet;
import java.util.Set;

public class MainDiTest {
    public static void main(String[] args) {
        /*
        JobRepository jobRepository = new JobRepository();
        Job job = jobRepository.find(1L);

        System.out.println(job.toString());

        TestSequence testSequence = new TestSequence();

        testSequence.setJob(job);
        testSequence.setName("Sequence 1");

        TestSequenceRepository testSequenceRepository = new TestSequenceRepository();
        testSequenceRepository.add(testSequence);



        TestSequence tsfind = testSequenceRepository.findById(5L);

        TestInteraction testInteraction1 = new TestInteraction();
        TestInteraction testInteraction2 = new TestInteraction();

        testInteraction1.setRequestBody("REQ body");
        testInteraction2.setRequestBody("REq bodu");
        testInteraction1.setResponseProtocol("GET");
        testInteraction2.setSequence(tsfind);
        testInteraction1.setSequence(tsfind);

        TestInteractionRepository testInteractionRepository = new TestInteractionRepository();
        testInteractionRepository.add(testInteraction1);
        testInteractionRepository.add(testInteraction2);


*/

        JobRepository jobRepository = new JobRepository();
        Job job = jobRepository.findFromFileById();

        System.out.println(job.toString());




    }
}
