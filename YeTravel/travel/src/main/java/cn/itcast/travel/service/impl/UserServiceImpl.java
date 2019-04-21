package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.UUID;

/**
 * @Author: qingye
 * @Date: 2019/2/27 0027 14:56
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
   private UserDao dao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User u = dao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        //设置激活状态
        user.setStatus("N");
        //保存UuidUtil的随机激活码
        user.setCode(UuidUtil.getUuid());
        dao.save(user);
        //发送邮箱内容
        String center = "< a href = 'http://localhost/travel/active?code="+user.getCode()+"'>点击激活【清叶旅游网】 </a>";
        MailUtils.sendMail(user.getEmail(),center,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        //根据激活码查找对象
        User user = dao.findBycode(code);
        if(user!= null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User u = dao.findByUserNandP(user.getUsername(),user.getPassword());
        return u;
    }
}
