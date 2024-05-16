package io.resttestgen.database.Writer;

import io.resttestgen.database.Model.Job;

import io.resttestgen.database.Model.RestAssured;
import io.resttestgen.database.Repository.JobRepository;
import io.resttestgen.database.Repository.RestAssuredRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class RestAssuredWriterDb {

    private Job job;
    RestAssured restAssured = new RestAssured();

    private Map<String, String> nominalMap;
    private Map<String, String> errorMap;
    private RestAssuredRepository restAssuredRepository;

    public void getErrorMap() {

        errorMap.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
    }

    public RestAssuredWriterDb(){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.nominalMap = new HashMap<>();
        this.errorMap = new HashMap<>();

        this.restAssuredRepository = new RestAssuredRepository();
        restAssured.setJob(job);

    }

    public void addToNominalMap(String fileName, String testAssured){
        nominalMap.put(fileName, testAssured);
    }

    public void addErrorMap(Map<String, String> errorMap){
        this.errorMap = errorMap;
    }

    public void addToErrorMap(String fileName, String testAssured){
        errorMap.put(fileName, testAssured);
    }

    public void write(){
        restAssuredRepository.add(restAssured);
    }

    public void writeNominal(){
        byte[] nominalZipBytes = writeZipToBytes(nominalMap);

        restAssured.setNominalFileName("NominalFiles.zip");
        restAssured.setNominalFile(nominalZipBytes);

    }

    public void writeError() {
        byte[] errorZipBytes = writeZipToBytes(errorMap);
        restAssured.setErrorFileName("ErrorFiles.zip");
        restAssured.setErrorFile(errorZipBytes);
    }


    private byte[] writeZipToBytes(Map<String,String> contentMap){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos)){

            for(Map.Entry<String, String> entry : contentMap.entrySet()){
                zos.putNextEntry(new ZipEntry(entry.getKey()));

                zos.write(entry.getValue().getBytes());

                zos.closeEntry();
            }

            zos.finish();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
