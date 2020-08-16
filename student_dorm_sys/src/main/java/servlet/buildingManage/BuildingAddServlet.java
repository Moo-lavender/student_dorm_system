package servlet.buildingManage;


import dao.BuildingDAO;
import dao.DormDAO;
import model.Building;
import model.Dorm;
import servlet.AbstractBaseServlet;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//新增宿舍楼
@WebServlet("/building/add")
public class BuildingAddServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Building building = JSONUtil.read(req.getInputStream(),Building.class);
        int num = new BuildingDAO().insert(building);
        return null;
    }
}
