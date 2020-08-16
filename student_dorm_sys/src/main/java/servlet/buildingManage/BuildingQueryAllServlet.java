package servlet.buildingManage;



import dao.BuildingDAO;
import model.Building;
import servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/building/query")
public class BuildingQueryAllServlet extends AbstractBaseServlet {
    //宿舍楼管理页面
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Building> buildings = BuildingDAO.queryAll();
        return buildings;
    }
}
