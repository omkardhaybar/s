package com.mcqhubb.service

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.apigateway.model.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.localstack.LocalStackContainer
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3
import spock.lang.Specification

@SpringBootTest
class TestSpec extends Specification{

    static LocalStackContainer s3Container = new LocalStackContainer().withServices(LocalStackContainer.Service.S3).withReuse(true);

    def setup(){
        s3Container.start();

//        System.setProperty("aws.accessKeyId", s3Container.getDefaultCredentialsProvider().getCredentials().getAWSAccessKeyId());
////        System.setProperty("aws.secretKey", s3Container.getDefaultCredentialsProvider().getCredentials().getAWSSecretKey());
////        System.setProperty("s3.endpoint", s3Container.getEndpointConfiguration(LocalStackContainer.Service.S3).getServiceEndpoint());
////        System.setProperty("s3.region", s3Container.getEndpointConfiguration(LocalStackContainer.Service.S3).getSigningRegion())
         String accessKeyId = s3Container.getDefaultCredentialsProvider().getCredentials().getAWSAccessKeyId()
         String secretKey =  s3Container.getDefaultCredentialsProvider().getCredentials().getAWSSecretKey()
         String endpoint = s3Container.getEndpointConfiguration(S3).getServiceEndpoint()
         String region = s3Container.getEndpointConfiguration(S3).getSigningRegion()

    }


//    @DynamicPropertySource
////    public static void prop(DynamicPropertyRegistry registry){
//////        registry.add("")
////    }

//    def "example test"(){
//
//        when:
//        then:
//
//        expect:
//        1+1==1+1
//    }

    def "two plus two should equal four"() {
        setup:
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(s3Container.getEndpointConfiguration(S3)).withCredentials(s3Container.getDefaultCredentialsProvider())
                .build();
        try {
            s3.createBucket("testbucket");
            s3.putObject("test.txt", "testbucket/test.txt", new File("C:\\Users\\a\\IdeaProjects\\mcqhubb\\src\\main\\resources\\test.txt"));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ " File Upload Successfull "+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        } catch(Exception e){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ " Exceptoin Occered while creating bucket "+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ " Exceptoin Occered while creating bucket "+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ " Exceptoin Occered while creating bucket "+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+ " Exceptoin Occered while creating bucket "+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


        }


        when:
        int result = 2 + 2

        then:
        result == 4
    }

//    @TestConfiguration
//    public static class beanTest{
//        @Bean
//        @Primary
//
//    }
}
