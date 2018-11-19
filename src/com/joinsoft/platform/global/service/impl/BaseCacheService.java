package com.joinsoft.platform.global.service.impl;

import com.danga.MemCached.MemCachedClient;
import com.joinsoft.platform.global.service.IBaseCacheService;

public class BaseCacheService implements IBaseCacheService {

	private MemCachedClient memCachedClient;

	@Override
	public <T> T getCacheValue(String key, Class<T> type) {
		return (T)getMemCachedClient().get(key);
	}

	@Override
	public boolean setCacheValue(String key, Object obj) {
		return getMemCachedClient().set(key, obj);
	}

	@Override
	public boolean setCacheValue(String key, Object obj, Integer expire) {
		return getMemCachedClient().set(key, obj, expire);
	}

	@Override
	public boolean deleteCacheValue(String key) {
		return getMemCachedClient().delete(key);
	}

	public MemCachedClient getMemCachedClient() {
		return memCachedClient;
	}

	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

}
