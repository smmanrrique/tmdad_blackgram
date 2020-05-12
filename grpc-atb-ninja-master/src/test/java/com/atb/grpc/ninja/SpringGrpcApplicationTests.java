package com.atb.grpc.ninja;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.atb.grpc.ninja.domain.BlockData;
import com.atb.grpc.ninja.grpc.client.BlockClient;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGrpcApplicationTests {

  private static final Logger log =LoggerFactory.getLogger(SpringGrpcApplicationTests.class);
  @Autowired
  private BlockClient client;
  @Autowired
  private BlockData blockData;
  @Test
  public void testAddBlock() {
	  blockData.setData("Block"+Long.toString(new Date().getTime()));
	  blockData.setHash("");
	  blockData.setLinkHash("");
	  String hash=client.addBlock(blockData);
	  log.info("\nATB - Hash del nuevo bloque \n hash="+hash);
		
		 assertThat(hash) .isNotEqualTo("0");

  } 
}