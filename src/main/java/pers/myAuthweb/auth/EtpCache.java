package pers.myAuthweb.auth;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import pers.myAuthweb.common.authz.IEtpCache;
import pers.myAuthweb.utils.RedisUtil;

/*
 * author : LH 2018-05-19 PM
 */
public class EtpCache extends IEtpCache {
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public List<String> getSet(String key) {
		return redisUtil.lRange(key, 0, -1);
	}

	@Override
	public boolean putSet(String key, Set<String> values) {
		return redisUtil.lLeftPushAll(key, values) > 0;
	}

	@Override
	public boolean removeSet(String key, String value) {
		return redisUtil.lRemove(key, 0, value) > 0;
	}

	@Override
	public boolean delete(String key) {
		redisUtil.delete(key);
		return true;
	}

	@Override
	public boolean delete(Collection<String> keys) {
		redisUtil.delete(keys);
		return true;
	}

	@Override
	public Set<String> keys(String pattern) {
		return redisUtil.keys(pattern);
	}

}
