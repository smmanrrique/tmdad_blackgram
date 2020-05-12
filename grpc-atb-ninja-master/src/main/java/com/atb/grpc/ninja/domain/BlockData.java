package com.atb.grpc.ninja.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BlockData {
	
	private String data;
	private String hash;
	private String linkHash;
	private String timestamp;
	

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getLinkHash() {
		return linkHash;
	}
	public void setLinkHash(String linkHash) {
		this.linkHash = linkHash;
	}
	public String getTimestamp() {
		return Long.toString(new Date().getTime());
	}
	
	@Override
	public String toString() {
		return "Block [\n data=" + data + "\n hash=" + hash + "\n linkHash=" + linkHash + "]";
	}
	public BlockData(String data, String hash, String linkHash) {
		this.data = data;
		this.hash = hash;
		this.linkHash = linkHash;
	}
	public BlockData() {
	}
	
	
	

}
