package com.joinsoft.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XmemcachedUtils {

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException, MemcachedException{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.0.31:11211"));
		 builder.setCommandFactory(new BinaryCommandFactory());
		 MemcachedClient client = builder.build();
		 client.set("t", 20, "lzx");
		 for(int t=0;t<8;t++){
			 System.out.println(client.get("t"));
			 if(t == 2){
				 client.replace("t", 20, "lzx1");
			 }
			 Thread.sleep(5000L);
		 }
	}
}
