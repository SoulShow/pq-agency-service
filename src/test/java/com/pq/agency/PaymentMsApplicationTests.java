package com.pq.agency;


import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentMsApplicationTests {

    @Test
    public void contextLoads() {
        List<String> list = new ArrayList<>();
        list.add("选项1");
        list.add("选项2");
        Gson gson = new Gson();
        String ss = gson.toJson(list);
        System.out.print("");

    }

}
