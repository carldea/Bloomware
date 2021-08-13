package me.offeex.bloomware.api.util;

import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.api.util.Timer;

import java.util.ArrayList;

public class RainbowUtil
{
    public RainbowUtil()
    {
        for (int l_I = 0; l_I < 360; l_I++)
        {
            RainbowArrayList.add(ColorUtils.GetRainbowColor(l_I, 90.0f, 50.0f, 1.0f).getRGB());
            CurrentRainbowIndexes.add(l_I);
        }
    }

    private final ArrayList<Integer> CurrentRainbowIndexes = new ArrayList<Integer>();
    private final ArrayList<Integer> RainbowArrayList = new ArrayList<Integer>();

    public int GetRainbowColorAt(int p_Index)
    {
        if (p_Index > CurrentRainbowIndexes.size() - 1)
            p_Index = CurrentRainbowIndexes.size() - 1;

        return RainbowArrayList.get(CurrentRainbowIndexes.get(p_Index));
    }
}