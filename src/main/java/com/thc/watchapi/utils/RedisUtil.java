package com.thc.watchapi.utils;

import com.thc.watchapi.config.redis.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.utils
 * @Description: redis工具类
 * @date 2020/11/12 4:02 下午
 */
@Lazy
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    // =============================common============================
    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean expire(String key, long time, int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long getExpire(String key, int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hasKey(String key,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public void del(int indexdb,String... key) {
        redisTemplate.indexdb.set(indexdb);
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    // ============================String=============================
    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("static-access")
    public Object get(String key, int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    @SuppressWarnings({ "static-access", "unchecked" })
    public boolean set(String key, Object value, int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean set(String key, Object value, int indexdb, long time) {
        System.out.println("redis");
        try {
            redisTemplate.indexdb.set(indexdb);
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta  要增加几(大于0)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long incr(String key, long delta,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param delta  要减少几(小于0)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long decr(String key, long delta,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================
    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public Object hget(String key, String item,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public Map<Object, Object> hmget(String key, int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hmset(String key, Map<String, Object> map,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hmset(String key, Map<String, Object> map, long time,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time,indexdb);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hset(String key, String item, Object value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hset(String key, String item, Object value, long time,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time,indexdb);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public void hdel(String key,int indexdb, Object... item) {
        redisTemplate.indexdb.set(indexdb);
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean hHasKey(String key, String item,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public double hincr(String key, String item, double by,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public double hdecr(String key, String item, double by,int indexdb) {
        redisTemplate.indexdb.set(indexdb);
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public Set<Object> sGet(String key, int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean sHasKey(String key, Object value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long sSet(String key,int indexdb, Object... values) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long sSetAndTime(String key, long time,int indexdb, Object... values) {
        try {
            redisTemplate.indexdb.set(indexdb);
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time,indexdb);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long sGetSetSize(String key,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long setRemove(String key,int indexdb, Object... values) {
        try {
            redisTemplate.indexdb.set(indexdb);
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public List<Object> lGet(String key, long start, long end, int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long lGetListSize(String key,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public Object lGetIndex(String key, long index,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param indexdb  库
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean lSet(String key, Object value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean lSet(String key, Object value, long time,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time,indexdb);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param indexdb  库
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean lSet(String key, List<Object> value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean lSet(String key, List<Object> value, long time,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time,indexdb);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public boolean lUpdateIndex(String key, long index, Object value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public long lRemove(String key, long count, Object value,int indexdb) {
        try {
            redisTemplate.indexdb.set(indexdb);
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

