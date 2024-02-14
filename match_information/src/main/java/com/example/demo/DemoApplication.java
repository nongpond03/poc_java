package com.example.demo;

import com.example.demo.pgp.KeyBasedFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
//@EnableBatchProcessing
public class DemoApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Value("classpath:output/sample.pgp")
    private Resource outputResource;
    @Value("classpath:files/sample.pgp")
    private Resource inputResource;
    @Value("classpath:keys/0xA2EFEE7F-pub.pgp")
    private Resource publicKeyResource;
    @Value("classpath:keys/0xA2EFEE7F-pri.pgp")
    private Resource privateKeyResource;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String outputFileName = outputResource.getFile().toPath().toString();
        String inputFileName = inputResource.getFile().toPath().toString();
        String publicKeyFileName = publicKeyResource.getFile().toPath().toString();
        String privateKeyFileName = privateKeyResource.getFile().toPath().toString();

        System.out.println("Decrypting " + inputFileName);
        KeyBasedFileProcessor.decryptFile(inputFileName, privateKeyFileName, "password".toCharArray(), null);
        System.out.println("Successfully decrypted " + inputFileName);

        //logger.info("Encrypting {}", inputFileName);
        //System.out.println("Encrypting " + inputFileName);
        //KeyBasedFileProcessor.encryptFile(outputFileName, inputFileName, publicKeyFileName, true, true);
        //logger.info("Successfully encrypted {}", inputFileName);
        //System.out.println("Successfully encrypted " + inputFileName);
    }
}
