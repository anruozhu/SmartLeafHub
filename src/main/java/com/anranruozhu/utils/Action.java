package com.anranruozhu.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anranruozhu.service.mqtt.receiveclient.MqttAcceptCallback;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.anranruozhu.entry.SensorData;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author anranruozhu
 * @ClassName action
 * @Description 行为函数
 * @create 2024/4/1 10:17
 **/
@Data
@Component
public class Action {
    private List<SensorData> ls=new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(MqttAcceptCallback.class);
    public void goA(String message) throws SQLException {
        // 数据库连接信息
        String url = "jdbc:mysql://139.155.150.150:3307/learn";
        String username = "root";
        String password = "1586866@Xyz";
        System.out.println("接收信息为:"+message);
        //数据
        JSONObject jsonObject = JSONUtil.parseObj(message);
        int temp = jsonObject.getInt("temp");
        int wet = jsonObject.getInt("wet");
        String status = jsonObject.getStr("status");
        // 数据库操作
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO sensor_data (temp, wet, status) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 将数据存入数据库
                pstmt.setInt(1, temp);
                pstmt.setInt(2, wet);
                pstmt.setString(3, status);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    public void goB(String message) throws SQLException {
        //数据
        JSONObject jsonObject = JSONUtil.parseObj(message);
        int temp = jsonObject.getInt("temp");
        int wet = jsonObject.getInt("wet");
        String status = jsonObject.getStr("status");
        SensorData sd =new SensorData(temp,wet,status);
        ls.add(sd);
        if(ls.size()==2){
            // 数据库连接信息
            String url = "jdbc:mysql://139.155.150.150:3307/learn";
            String username = "root";
            String password = "1586866@Xyz";
            // 计算日期和时间
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
           int wet1= (ls.get(0).getWet()+ls.get(1).getWet())/2;
            int temp1= (ls.get(0).getTemp()+ls.get(1).getTemp())/2;
            ls.clear();
            // 数据库操作
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO sensor_data (temp, wet, status,date_reported,time_reported) VALUES (?, ?, ?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // 将数据存入数据库
                    pstmt.setInt(1, temp1);
                    pstmt.setInt(2, wet1);
                    pstmt.setString(3, status);
                    pstmt.setDate(4, Date.valueOf(currentDate));
                    pstmt.setTime(5, Time.valueOf(currentTime));
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}

