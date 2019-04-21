package cn.itcast.travel.service;

import cn.itcast.travel.domain.User; /**
 * @Author: qingye
 * @Date: 2019/2/27 0027 14:56
 * @Version 1.0
 */
public interface UserService {
    /**
     * 用户注册方法
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 邮箱激活方法
     * @param code
     * @return
     */
    boolean active(String code);


    User login(User user);
}
