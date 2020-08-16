package dao;

import model.Building;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BuildingDAOTest {

    @Test
    public void queryAll() {
        BuildingDAO buildingDAO = new BuildingDAO();
        List<Building> buildings = buildingDAO.queryAll();
        System.out.println(buildings);
    }
}