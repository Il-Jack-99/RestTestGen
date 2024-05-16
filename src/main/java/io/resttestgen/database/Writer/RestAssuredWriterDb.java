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

    private Map<String, String> nominalMap;
    private Map<String, String> errorMap;
    private RestAssuredRepository restAssuredRepository;

    public RestAssuredWriterDb(){
        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.findFromFileById();
        jobRepository.close();

        this.nominalMap = new HashMap<>();
        this.errorMap = new HashMap<>();

        this.restAssuredRepository = new RestAssuredRepository();

    }

    public void addToNominalMap(String fileName, String testAssured){
        nominalMap.put(fileName, testAssured);
    }

    public void addToErrorMap(String fileName, String testAssured){
        errorMap.put(fileName, testAssured);
    }

    public void write(){
        byte[] nominalZipBytes = writeZipToBytes(nominalMap);
        byte[] errorZipBytes = writeZipToBytes(errorMap);

        RestAssured restAssured = new RestAssured();
        restAssured.setNominalFileName("NominalFiles.zip");
        restAssured.setNominalFile(nominalZipBytes);
        restAssured.setErrorFileName("ErrorFiles.zip");
        restAssured.setErrorFile(errorZipBytes);
        restAssured.setJob(job);

        restAssuredRepository.add(restAssured);
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
