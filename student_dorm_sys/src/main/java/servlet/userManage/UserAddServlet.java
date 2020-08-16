package servlet.userManage;


import dao.BuildingDAO;
import dao.UserDAO;
import model.Building;
import model.User;
import servlet.AbstractBaseServlet;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//新增宿舍楼
@WebServlet("/user/register")
public class UserAddServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JSONUtil.read(req.getInputStream(),User.class);
        int num = new UserDAO().insert(user);
        return null;
    }
}
