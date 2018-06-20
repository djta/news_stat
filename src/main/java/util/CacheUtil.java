package util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import domain.stat.otherStat.OtherStatDomain;

import java.util.concurrent.TimeUnit;

/**
 * Created by hzyuyongmao on 2018/6/20.
 *
 * @Description
 */
public class CacheUtil {
    public static Cache<String, OtherStatDomain> otherStateCache = CacheBuilder.newBuilder().build();
}
