package com.joinsoft.platform.global.service;

public interface IBaseCacheService {

	public abstract <T> T getCacheValue(String key, Class<T> type);
	  
    public abstract boolean setCacheValue(String key, Object obj);
  
    public abstract boolean setCacheValue(String key, Object obj, Integer expire);
  
    public abstract boolean deleteCacheValue(String key);
}
