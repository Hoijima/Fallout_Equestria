package com.redsparkle.foe.Init;
/**
 * Created by hoijima on 14.12.16.
 */
public class StartUpCommon {
    public static void preInitCommon() {
        ConfigInit.preInit();
//        System.out.println("MBE70: myInteger=" + ConfigInit.myInteger
//                + "; myBoolean=" + ConfigInit.myBoolean
//                + "; myString=" + ConfigInit.myString);
//        System.out.println("MBE70: myDouble=" + ConfigInit.myDouble
//                + "; myColour=" + ConfigInit.myColour);
//        System.out.print("MBE70: myIntList=");
//        for (int value : ConfigInit.myIntList) {
//            System.out.print(value + "; ");
//        }
//        System.out.println();
        System.out.println("Radio config");
        System.out.println(ConfigInit.Radio1Name);
        System.out.println(ConfigInit.Radio1URL);
        System.out.println(ConfigInit.Radio2Name);
        System.out.println(ConfigInit.Radio2URL);




    }
    public static void InitCommon() {
    }
    public static void postInitCommon() {
    }
}