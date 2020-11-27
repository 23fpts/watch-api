package com.thc.watchapi.utils;


import com.thc.watchapi.model.WatchData;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author thc
 * @Title:
 * @Package com.thc.bluetoothtest.utils
 * @Description:
 * @date 2020/11/3 3:12 下午
 */
public class WatchDataUtil {

//    private static String hexTest = "A5,A5,2C,F2,4A,43,02,3F,57,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,A1,00,00,00,00,01,30,FD,CA,06,5A,9B,58,01,0D,0A";

    private static String hexTest = "A5,A5,2C,FD,A3,DA,1F,16,24,D6,0C,00,00,00,50,CA,02,00,00,16,88,00,00,07,00,00,00,00,00,00,00,A1,00,00,C0,05,02,00,00,00,00,00,00,00,00,0D,0A";

    // D6,0C,00,00,00,50,CA,02,00,00,16,88,00,00,07,00,00,00,00,00,00,00,A1,00,00,C0,05,02,00,00,00,00,00,00,00,00,0D,0A
    // mac: FD,A3,DA,1F,16,24 4-9 (6-17)
    // 运动时长 sportsTime D6,0C,00,00 10-13 (18-25)
    /**
     * 16进制的47位数据转为对象数据
     * @param hex
     * @return
     */
    public static WatchData HexDataToData(String hex) {
        // 或者用 Integer.parseUnsignedInt 无符号
        // TODO 经纬度的正负
        // 先去掉逗号
        hex = hex.replaceAll(",", "");
        for (int i=0; i<hex.length(); i++){
            System.out.println(i+ ": "+hex.charAt(i));
        }
        WatchData watchData = new WatchData();
        // mac 6-17
//        String mac = hex.substring(6, 18);
        String mac = reverse(hex.substring(6, 18));

        watchData.setMac(mac);
        System.out.println("mac: "+mac);
        // 运动时长 18-25
        System.out.println(hex.substring(18, 26));
        System.out.println(reverse(hex.substring(18, 26)));
//        System.out.println(Integer.parseInt(hex.substring(18, 26), 16));
//        Integer sportsTime = Integer.parseInt(hex.substring(18, 26), 16);
        BigInteger sportsTime = new BigInteger(reverse(hex.substring(18, 26)), 16);
        watchData.setSportsTime(sportsTime);
        System.out.println("运动时长: "+sportsTime);
        System.out.println(hex.substring(18, 26));
        System.out.println(reverse(hex.substring(18, 26)));
        // 实时心率 26-27
//        Integer realTimeHeartRate = Integer.parseInt(hex.substring(26, 28), 16);
        BigInteger realTimeHeartRate = new BigInteger(reverse(hex.substring(26, 28)), 16);
        watchData.setRealTimeHeartRate(realTimeHeartRate);
        System.out.println("实时心率: "+realTimeHeartRate);
        System.out.println(hex.substring(26, 28));
        System.out.println(reverse(hex.substring(26, 28)));
        // 平均心率 28-29
//        Integer averageHeartRate = Integer.parseInt(hex.substring(28, 30), 16);
        BigInteger averageHeartRate = new BigInteger(reverse(hex.substring(28, 30)), 16);
        watchData.setAverageHeartRate(averageHeartRate);
        System.out.println("平均心率: "+averageHeartRate);
        System.out.println(hex.substring(28, 30));
        System.out.println(reverse(hex.substring(28, 30)));
        // 距离 30-37
//        Integer distance = Integer.parseInt(hex.substring(30, 38), 16);
        BigInteger distance = new BigInteger(reverse(hex.substring(30, 38)), 16);
        watchData.setDistance(distance);
        System.out.println("距离: "+distance);
        System.out.println(hex.substring(30, 38));
        System.out.println(reverse(hex.substring(30, 38)));
        // 卡路里 38-45
//        Integer calorie = Integer.parseInt(hex.substring(38, 46), 16);
        BigInteger calorie = new BigInteger(reverse(hex.substring(38, 46)), 16);
        watchData.setCalorie(calorie);
        System.out.println("卡路里: "+calorie);
        System.out.println(hex.substring(38, 46));
        System.out.println(reverse(hex.substring(38, 46)));
        // 总步数 46-53
//        Integer totalStepCount = Integer.parseInt(hex.substring(46, 54), 16);
        BigInteger totalStepCount = new BigInteger(reverse(hex.substring(46, 54)), 16);
        watchData.setTotalStepCount(totalStepCount);
        System.out.println("总步数: "+totalStepCount);
        System.out.println(hex.substring(46, 54));
        System.out.println(reverse(hex.substring(46, 54)));
        // 实时步频 54-57
//        Integer realTimeCadence = Integer.parseInt(hex.substring(54, 58), 16);
        BigInteger realTimeCadence = new BigInteger(reverse(hex.substring(54, 58)), 16);
        watchData.setRealTimeCadence(realTimeCadence);
        System.out.println("实时步频: "+realTimeCadence);
        System.out.println(hex.substring(54, 58));
        System.out.println(reverse(hex.substring(54, 58)));
        // 平均步频 58-61
//        Integer averageCadence = Integer.parseInt(hex.substring(58, 62), 16);
        BigInteger averageCadence = new BigInteger(reverse(hex.substring(58, 62)), 16);
        watchData.setAverageCadence(averageCadence);
        System.out.println("平均步频: "+averageCadence);
        System.out.println(hex.substring(58, 62));
        System.out.println(reverse(hex.substring(58, 62)));
        // 运动类型 62-63
        Integer sportsType = Integer.parseInt(reverse(hex.substring(62, 64)), 16);
        watchData.setSportsType(sportsType);
        System.out.println("运动类型: "+sportsType);
        System.out.println(hex.substring(62, 64));
        System.out.println(reverse(hex.substring(62, 64)));
        // 实时速度 64-67
        Integer realTimeSpeed = Integer.parseInt(reverse(hex.substring(64, 68)), 16);
        watchData.setRealTimeSpeed(new BigDecimal(realTimeSpeed));
        System.out.println("实时速度: "+watchData.getRealTimeSpeed());
        System.out.println(hex.substring(64, 68));
        System.out.println(reverse(hex.substring(64, 68)));
        // 平均速度 averageSpeed 68-71
        Integer averageSpeed = Integer.parseInt(reverse(hex.substring(68, 72)), 16);
        watchData.setAverageSpeed(new BigDecimal(averageSpeed));
        System.out.println("平均速度: "+watchData.getAverageSpeed());
        System.out.println(hex.substring(68, 72));
        System.out.println(reverse(hex.substring(68, 72)));
        // 运动状态 sportsStatus 72-73
        Integer sportsStatus = Integer.parseInt(reverse(hex.substring(72, 74)), 16);
        watchData.setSportsStatus(sportsStatus);
        System.out.println("运动状态: "+watchData.getSportsStatus());
        System.out.println(hex.substring(72, 74));
        // 经度 longitude 74-81  TODO
        BigInteger longitude = new BigInteger(reverse(hex.substring(74, 82)), 16);
        // 再除以1000000
        BigDecimal longitudeDeal = new BigDecimal(longitude);
        longitudeDeal = longitudeDeal.divide(new BigDecimal(1000000));
        watchData.setLongitude(longitudeDeal);
        System.out.println("经度: "+watchData.getLongitude() );
        System.out.println(hex.substring(74, 82));
        // 纬度 latitude 82-89 TODO
        BigInteger latitude = new BigInteger(reverse(hex.substring(82, 90)), 16);
        // 再除以1000000
        BigDecimal latitudeDeal = new BigDecimal(latitude);
        latitudeDeal = latitudeDeal.divide(new BigDecimal(1000000));
        watchData.setLatitude(latitudeDeal);
        System.out.println("纬度: "+watchData.getLatitude());
        System.out.println(hex.substring(82, 90));

        return watchData;
    }

    public static String reverse(String str) {
        // TODO 把诸如 580C0000 转为 00000C58
        String result = "";
        int length = str.length()/2;
        for (int i=0; i<length; i++){
            result = str.substring(2*i, 2*i+2) + result ;
        }
        return result;
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
        System.out.println(reverse("580C0000"));

    }

    public static void test1(){
        HexDataToData(hexTest);
    }

    public static void test2(){
        String hex = "D60C0000";
        Integer x = Integer.parseInt(hex,16);
        System.out.println(x);
    }

    public static void test3(){
        // 先去掉逗号
        String hex = hexTest.replaceAll(",", "");
        WatchData watchData = new WatchData();
        System.out.println(hex.substring(18, 26));
    }
}
