package com.redsparkle.foe.gui.Menus.pipbuck_gui_extenders.DATA;

import com.redsparkle.foe.utils.gui.GuiButtonExtFallout;

/**
 * Created by hoijima on 09.05.17.
 */
public class DataGui {
    public static GuiButtonExtFallout World_Map = new GuiButtonExtFallout(30,
            0,
            0,
            0,
            0, "World Map");
    public static GuiButtonExtFallout Misc = new GuiButtonExtFallout(31,
            0,
            0,
            0,
            0, "Misc");
    public static GuiButtonExtFallout Radio = new GuiButtonExtFallout(32,
            0,
            0,
            0,
            0, "Radio");
    public static final GuiButtonExtFallout[] buttonsDATAmain = new GuiButtonExtFallout[]{
            World_Map, Misc, Radio
    };

    public static GuiButtonExtFallout RadioButtonStart = new GuiButtonExtFallout(33,
            0,
            0,
            0,
            0, "Radio START!");
    public static GuiButtonExtFallout RadioButtonStop = new GuiButtonExtFallout(34,
            0,
            0,
            0,
            0, "Radio STOP!");

    public static GuiButtonExtFallout RadioVolumeUp = new GuiButtonExtFallout(35,
            0,
            0,
            0,
            0, "Volume +");

    public static GuiButtonExtFallout RadioVolumeDown = new GuiButtonExtFallout(36,
            0,
            0,
            0,
            0, "Volume -");

    public static final GuiButtonExtFallout[] buttonsDATARadio = new GuiButtonExtFallout[]{
            RadioButtonStart, RadioButtonStop, RadioVolumeUp, RadioVolumeDown
    };

}
