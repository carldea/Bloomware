package me.offeex.bloomware.api.util;

public final class Timer
{
    private long time;

    public Timer()
    {
        time = -1;
    }

    public boolean passed(double ms)
    {
        return System.currentTimeMillis() - this.time >= ms;
    }

    public void reset()
    {
        this.time = System.currentTimeMillis();
    }
}