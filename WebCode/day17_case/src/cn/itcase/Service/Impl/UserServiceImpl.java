package cn.itcase.Service.Impl;

import cn.itcase.DAO.Impl.UserDaoImpl;
import cn.itcase.DAO.UserDao;
import cn.itcase.Service.UserService;
import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 19:03
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        //调用删除
        dao.deleteUserById(Integer.parseInt(id));
    }

    @Override
    public User findUserByid(String id) {
        return dao.findUserByid(Integer.parseInt(id));
    }

    @Override
    public void update(User user) {
        dao.updateById(user);
    }

    @Override
    public void deleteUserById(String[] uids) {
        if (uids != null && uids.length > 0) {
            for (String uid : uids) {
                dao.deleteUserById(Integer.parseInt(uid));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建一个PageBean对象
        PageBean<User> pb = new PageBean<>();
        //控制按键上一页为0时的异常判断
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalCount = dao.findTotalCount(condition);
        int totalPage = (totalCount % rows) ==0? totalCount/rows:(totalCount/rows)+1;
        //控制按键下一页超出总页数的异常判断
        if(currentPage>=totalPage){
            currentPage=totalPage;
        }
        int start = (currentPage - 1) * rows;
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用dao查询总记录数
        pb.setTotalCount(totalCount);
        //4.计算总页码
        pb.setTotalPage(totalPage);
        //5.调用dao查询List集合
        //计算开始的记录索引
        List<User> list = dao.findByPage(start, rows,condition);
        pb.setList(list);

        return pb;
    }
}

