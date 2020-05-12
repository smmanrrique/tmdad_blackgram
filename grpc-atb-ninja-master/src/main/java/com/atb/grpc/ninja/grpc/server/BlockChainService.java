package com.atb.grpc.ninja.grpc.server;

import com.atb.grpc.ninja.dao.BlockDataDao;
import com.atb.grpc.ninja.srv.BlockSrv;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atb.grpc.ninja.blockchain.Block;
import com.atb.grpc.ninja.blockchain.BlockChainServiceGrpc.BlockChainServiceImplBase;
import com.atb.grpc.ninja.blockchain.BlockConfirm;


@GRpcService
public class BlockChainService extends BlockChainServiceImplBase { 
   private static final Logger log =LoggerFactory.getLogger(BlockChainService.class);
   @Autowired
   private BlockSrv blockSrv;
   @Autowired
   private BlockDataDao dao;
	public void addBlock(Block request,
		      StreamObserver<BlockConfirm> responseObserver)
	{
		String returnHash="0";
		// Comprobamos que el hash de enlace del nuevo bloque es el correcto
		if (blockSrv.checkBlock(request.getLinkHash())) {
			log.info("\n ATB - Una vez comprobada la validez del bloque en el servidor, la guardamos en Mongo ");
			//Guardamos una replica de la cadena en la collection Mongo
			dao.save(new Document("data", request.getData())
						.append("hash", request.getHash())
							.append("link_hash",request.getLinkHash()));
			// retornamos el hash del nuevo bloque
			log.info("\n ATB - Retornamos el hash del nuevo bloque, clave para la vinculación del siguiente");
			returnHash = request.getHash();
		}
		BlockConfirm hash=BlockConfirm.newBuilder()
				.setMessage(returnHash)
					.build();
			
			responseObserver.onNext(hash);
				responseObserver.onCompleted();
	}
}
