package servlet.dormManage;


import dao.DormDAO;
import model.Dorm;
import servlet.AbstractBaseServlet;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//新增寝室
@WebServlet("/dorm/add")
public class DormAddServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Dorm dorm = JSONUtil.read(req.getInputStream(),Dorm.class);
        int num = new DormDAO().insert(dorm);
        return null;
    }
}
