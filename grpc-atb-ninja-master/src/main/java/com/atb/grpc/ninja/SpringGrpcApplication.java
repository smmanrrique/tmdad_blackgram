package com.atb.grpc.ninja;

import java.util.Date;
import com.atb.grpc.ninja.domain.BlockData;
import com.atb.grpc.ninja.grpc.client.BlockClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGrpcApplication{

	private static final Logger log =LoggerFactory.getLogger(SpringGrpcApplication.class);
    //@Autowired
    //private static BlockClient client;
    //@Autowired
    //private static BlockData blockData;

  public static void main(String[] args)
  {
      SpringApplication.run(SpringGrpcApplication.class, args);
  }
  
}
