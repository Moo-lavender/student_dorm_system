package servlet.dormManage;

import dao.DormDAO;
import model.Dorm;
import servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/dorm/query")
public class DormQueryAllServlet extends AbstractBaseServlet {

    //寝室管理页面
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Dorm> buildings = DormDAO.queryAll();
        return buildings;
    }
}
