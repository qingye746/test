package cn.itcase.service.Impl;

import cn.itcase.Utils.JedisPoolUtils;
import cn.itcase.dao.Impl.ProvinceDaoImpl;
import cn.itcase.dao.ProvinceDao;
import cn.itcase.domain.Province;
import cn.itcase.service.ProvinceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/25 0025 18:40
 * @Version 1.0
 */
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao=  new ProvinceDaoImpl();


    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        // 1.先从redis中查询数据
        // 1.1获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //2判断 province_json 数据是否为null
        if(province_json==null ||province_json.length()==0){
            System.out.println("redis中没数据，查询数据库...");
            List<Province> ps = dao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province",province_json);
            jedis.close();

        }else {
            System.out.println("redis中有数据，查询缓存...");
        }
        return province_json;
    }
}
