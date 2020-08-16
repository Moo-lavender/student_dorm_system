package servlet.dormManage;


import dao.DormDAO;
import servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//删除寝室
@WebServlet("/dorm/delete")
public class DormDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");
        int num = DormDAO.delete(ids);
        return null;
    }
}
