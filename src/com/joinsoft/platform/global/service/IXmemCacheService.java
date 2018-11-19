package com.joinsoft.platform.global.service;

public interface IXmemCacheService {

	/**
	 * 获取key的缓存值
	 * @param key
	 * @return
	 */
	public Object get(String key);
	
	/**
	 * 获取key的缓存值，采用touch协议，只需要传递key就能更新缓存的超时时间（xmemcached 1.3.6之后支持）
	 * 实现数据缓存时间的轮滑机制，和session的过期一样。
	 * @param key
	 * @return
	 */
	public Object touch(String key, int expire);
	
	/**
	 * 永久存储信息（默认一个月）
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean set(String key, Object obj);
	/**
	 * 设置到期时间
	 * @param key
	 * @param expire（秒）0表示永久存储，默认一个月；大于0，超过此时间将去除。
	 * @param obj
	 * @return
	 */
	public boolean set(String key, int expire, Object obj);
	/**
	 * 设置到期时间
	 * @param key
	 * @param expire（秒）0表示永久存储，默认一个月；大于0，超过此时间将去除。
	 * @param obj
	 * @param timeout（毫秒）设置过期时间，如果expire还未到期，expire到期，则该key过期
	 * @return
	 */
	public boolean set(String key, int expire, Object obj, long timeout);
	/**
	 * 设置到期时间
	 * @param key
	 * @param obj
	 * @param timeout（毫秒）设置过期时间，如果expire还未到期，expire到期，则该key过期
	 * @return
	 */
	public boolean set(String key, Object obj, long timeout);
	
    public boolean delete(String key);
}
