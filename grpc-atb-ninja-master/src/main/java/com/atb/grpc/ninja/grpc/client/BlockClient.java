package com.atb.grpc.ninja.grpc.client;

import javax.annotation.PostConstruct;

import com.atb.grpc.ninja.domain.BlockData;
import com.atb.grpc.ninja.srv.BlockSrv;
import com.atb.grpc.ninja.srv.EncriptSrv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.atb.grpc.ninja.blockchain.Block;
import com.atb.grpc.ninja.blockchain.BlockChainServiceGrpc;
import com.atb.grpc.ninja.blockchain.BlockConfirm;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class BlockClient {
	private static final Logger log =LoggerFactory.getLogger(BlockClient.class);
	private BlockChainServiceGrpc.BlockChainServiceBlockingStub blockChainServiceBlockingStub;
    @Autowired
    private EncriptSrv encriptSrv;
    @Autowired
    private BlockSrv blockSrv;
	  @PostConstruct
	  private void init() {
	    ManagedChannel managedChannel = ManagedChannelBuilder
	        .forAddress("localhost", 6565)
	        	.usePlaintext()
	        		.build();

	    	blockChainServiceBlockingStub =
	    			BlockChainServiceGrpc.newBlockingStub(managedChannel);
	    }
	  public String addBlock(BlockData block)
	  {
		// Obtenemos el hash del último bloque de la cadena. Vamos a obtenerlo de la cadena replicada en Mongo al ser una simulación..
	  	// Pero en una situacion real tendria que decodificarse
		  block.setLinkHash(encriptSrv.dummyLinkHashDecode());
		  
		  //Generamos el hash del nuevo bloque a partir de la data y del hash de enlace
		  block.setHash(blockSrv.genBlockHash(block));
		  log.info("\n ATB - Enviamos el nuevo bloque desde el cliente...."+block.toString());
		  BlockConfirm blockConfirm=blockChainServiceBlockingStub.addBlock(
			  Block.newBuilder()
			  	.setData(block.getData())
			  		.setHash(block.getHash())
			  			.setLinkHash(block.getLinkHash()).build()
			  );
		  return blockConfirm.getMessage();
	  }  
}
