package util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBUtilTest {

    @Test
    public void getConnection() {
        Assert.assertNotNull(DBUtil.getConnection());
    }

    @Test
    public void close() {
    }

    @Test
    public void testClose() {
    }
}