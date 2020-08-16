package dao;

import model.Building;
import model.DictionaryTag;
import model.Dorm;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuildingDAO {

    public static List<DictionaryTag> query() {
        List<DictionaryTag> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select id, building_name from building";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DictionaryTag tag = new DictionaryTag();
                tag.setDictionaryTagKey(rs.getString("id"));
                tag.setDictionaryTagValue(rs.getString("building_name"));
                list.add(tag);
            }
        } catch (Exception e) {
            throw new RuntimeException("查询宿舍楼数据字典出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return list;
    }


    public static List<Building> queryAll() {
        List<Building> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select b.building_name, b.building_desc, b.create_time,count(0) dormCount from building b left join dorm d on b.id = d.building_id group by b.building_name";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Building tag = new Building();
                tag.setBuildingName(rs.getString("building_name"));
                tag.setBuildingDesc(rs.getString("building_desc"));
                tag.setDormCount(rs.getInt("dormCount"));
                tag.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));

                list.add(tag);
            }
        } catch (Exception e) {
            throw new RuntimeException("查询宿舍楼出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return list;
    }

    public int insert(Building building) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            String sql = "insert into building(building_name, building_desc) values (?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, building.getBuildingName());
            ps.setString(2, building.getBuildingDesc());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("插入宿舍楼信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
