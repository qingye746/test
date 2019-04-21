package cn.itcase.dao.Impl;

import cn.itcase.Utils.JDBCUtils;
import cn.itcase.dao.ProvinceDao;
import cn.itcase.domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.xml.transform.Templates;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/25 0025 18:41
 * @Version 1.0
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        //执行并返回sql
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return  list ;
    }
}
