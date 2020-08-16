package servlet.studentManage;

import dao.BuildingDAO;
import model.DictionaryTag;
import servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/building/queryAsDict")
public class BuildingQueryServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<DictionaryTag> tags = BuildingDAO.query();
        return tags;
    }
}
