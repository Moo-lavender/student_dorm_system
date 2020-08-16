package util;

import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JSONUtilTest {

    @Test
    public void read() {
        Map<String,String> map = new HashMap<>();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        System.out.println(JSONUtil.write(map));
    }

    @Test
    public void write() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test.json");
        HashMap map = JSONUtil.read(is,HashMap.class);
        System.out.println(map);

    }
}