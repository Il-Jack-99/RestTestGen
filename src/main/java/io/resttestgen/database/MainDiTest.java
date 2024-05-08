package io.resttestgen.database;

import io.resttestgen.database.Model.Job;
import io.resttestgen.database.Model.TestInteraction;
import io.resttestgen.database.Model.TestSequence;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.TestSequenceRepository;

public class MainDiTest {
    public static void main(String[] args) {
        JobRepository jobRepository = new JobRepository();
        Job job = jobRepository.find(1L);

        System.out.println(job.toString());

        TestSequence testSequence = new TestSequence();

        testSequence.setJob(job);
        testSequence.setName("Sequence 1");

        TestSequenceRepository testSequenceRepository = new TestSequenceRepository();

        testSequenceRepository.add(testSequence);

    }
}
