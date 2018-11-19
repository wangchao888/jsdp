package com.joinsoft.platform.global.service.impl;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.platform.global.service.IXmemCacheService;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class XmemCacheService implements IXmemCacheService {

	private static final Logger log = LoggerFactory
			.getLogger(XmemCacheService.class);
	
	private XMemcachedClient memCachedClient;

	/**
	 * 获取key的缓存值
	 * @param key
	 * @return
	 */
	public Object get(String key){
		try {
			return getMemCachedClient().get(key);
		} catch (TimeoutException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		} catch (MemcachedException e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取key的缓存值，采用touch协议，只需要传递key就能更新缓存的超时时间（xmemcached 1.3.6之后支持）
	 * 实现数据缓存时间的轮滑机制，和session的过期一样。
	 * @param key
	 * @return
	 */
	public Object touch(String key, int expire){
		try {
			return (Object)getMemCachedClient().getAndTouch(key, expire);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 永久存储信息（默认一个月）
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean set(String key, Object obj){
		return set(key, 0, obj);
	}
	
	/**
	 * 设置到期时间
	 * @param key
	 * @param expire（秒）0表示永久存储，默认一个月；大于0，超过此时间将去除。
	 * @param obj
	 * @return
	 */
	public boolean set(String key, int expire, Object obj){
		boolean flag = false;
		try {
			flag = getMemCachedClient().set(key, expire, obj);
		} catch (TimeoutException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		} catch (MemcachedException e) {
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 设置到期时间
	 * @param key
	 * @param expire（秒）0表示永久存储，默认一个月；大于0，超过此时间将去除。
	 * @param obj
	 * @param timeout（毫秒）超时时间，优先级高于client.setOpTimeout
	 * @return
	 */
	public boolean set(String key, int expire, Object obj, long timeout){
		boolean flag = false;
		try {
			flag = getMemCachedClient().set(key, expire, obj, timeout);
		} catch (TimeoutException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		} catch (MemcachedException e) {
			log.error(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 设置到期时间
	 * @param key
	 * @param obj
	 * @param timeout（毫秒1s=1000ms）超时时间，优先级高于client.setOpTimeout
	 * @return
	 */
	public boolean set(String key, Object obj, long timeout){
		return set(key, 0, obj, timeout);
	}
	
	public boolean delete(String key){
		boolean flag = false;
		try {
			flag = getMemCachedClient().delete(key);
		} catch (TimeoutException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		} catch (MemcachedException e) {
			log.error(e.getMessage());
		}
		return flag;
	}

	public XMemcachedClient getMemCachedClient() {
		return memCachedClient;
	}

	public void setMemCachedClient(XMemcachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

}
