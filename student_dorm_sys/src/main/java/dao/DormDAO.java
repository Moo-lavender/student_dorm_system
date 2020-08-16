package dao;


import model.DictionaryTag;
import model.Dorm;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormDAO {

    public static List<DictionaryTag> query(int id) {
        List<DictionaryTag> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "SELECT d.id," +
                    "       d.dorm_no" +
                    "   FROM building b" +
                    "         JOIN dorm d ON b.id = d.building_id" +
                    "   WHERE b.id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                DictionaryTag tag = new DictionaryTag();
                tag.setDictionaryTagKey(rs.getString("id"));
                tag.setDictionaryTagValue(rs.getString("dorm_no"));
                list.add(tag);
            }
        } catch (Exception e) {
            throw new RuntimeException("查询寝室数据字典出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return list;
    }

    public static List<Dorm> queryAll() {
        List<Dorm> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select b.building_name,d.dorm_no,d.dorm_desc,d.create_time from building b join dorm d on b.id = d.building_id";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Dorm tag = new Dorm();
                tag.setBuildingName(rs.getString("building_name"));
                tag.setDormNo(rs.getString("dorm_no"));
                tag.setDormDesc(rs.getString("dorm_desc"));
                tag.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                list.add(tag);
            }
        } catch (Exception e) {
            throw new RuntimeException("查询寝室出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return list;
    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from dorm where id in (");
            for(int i=0; i<ids.length; i++){
                if(i != 0) {
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());
            for(int i=0; i<ids.length; i++){
                ps.setInt(i+1, Integer.parseInt(ids[i]));
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("删除寝室信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }

    public int insert(Dorm dorm) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            String sql = "insert into dorm(dorm_no, dorm_desc, building_id) values (?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, dorm.getDormNo());
            ps.setString(2, dorm.getDormDesc());
            ps.setInt(3, dorm.getBuildingId());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("插入寝室信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
