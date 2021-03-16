package com.meow.blog.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

/**
 * @author loveliness
 */
@Controller
@RequestMapping("/visit")
public class VisitController {


    @SneakyThrows
    @RequestMapping("/getAllCount")
    @ResponseBody
    public Integer getAllCount() {
        File info = new File("src/main/resources/static/static/info.txt");
        InputStream is = new FileInputStream(info);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String num;
        num = bufferedReader.readLine();
        int vistedNum = Integer.parseInt(num);

        is.close();
        bufferedReader.close();


        return vistedNum;
    }

    @SneakyThrows
    @RequestMapping("/addVisit")
    @ResponseBody
    public Void addVisit() {
        File info = new File("src/main/resources/static/static/info.txt");
        String num;

        int vistedNum = getAllCount();
        num = String.valueOf(vistedNum + 1);

//        写入文件
        FileWriter fileWriter = new FileWriter(info);
        fileWriter.write(num);

        fileWriter.close();

        return null;
    }


}
