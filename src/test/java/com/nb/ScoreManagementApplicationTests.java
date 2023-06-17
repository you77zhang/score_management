package com.nb;

import com.nb.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ScoreManagementApplicationTests {
    
    @Autowired
    private UserMapper userMapper;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    void getRoles(){
        String username = "zp";
        List<String> roles = userMapper.getRoles(username);
        System.out.println(roles.toString());
    }
}
