package me.offeex.ofx.api.event.events;

import java.util.function.Consumer;

import me.offeex.ofx.api.event.Event;
import net.minecraft.client.network.ClientPlayerEntity;

public class EventPlayerMotionUpdate extends Event
{
    protected float yaw;
    protected float pitch;
    protected double x;
    protected double y;
    protected double z;
    protected boolean onGround;
    private Consumer<ClientPlayerEntity> funcToCall = null;
    
    public EventPlayerMotionUpdate(double posX, double posY, double posZ, boolean pOnGround) {
        x = posX;
        y = posY;
        z = posZ;
        onGround = pOnGround;
    }

    public static class EventPlayerMotionUpdateCancelled extends EventPlayerMotionUpdate
    {
        public EventPlayerMotionUpdateCancelled(float pitch, float yaw)
        {
            super(0, 0, 0, false);
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }
    
    public Consumer<ClientPlayerEntity> getFunc()
    {
        return funcToCall;
    }

    public void setFunct(Consumer<ClientPlayerEntity> post)
    {
        funcToCall = post;
    }

    public float getYaw()
    {
        return yaw;
    }

    public void setYaw(float yaw)
    {
        this.yaw = yaw;
    }

    public float getPitch()
    {
        return pitch;
    }

    public void setPitch(float pitch)
    {
        this.pitch = pitch;
    }
    
    public void setYaw(double yaw)
    {
        this.yaw = (float)yaw;
    }
    
    public void setPitch(double pitch)
    {
        this.pitch = (float)pitch;
    }
    
    public void setX(double posX)
    {
        x = posX;
    }
    
    public void setY(double d)
    {
        y = d;
    }
    
    public void setZ(double posZ)
    {
        z = posZ;
    }

    public void setOnGround(boolean b)
    {
        onGround = b;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getZ()
    {
        return z;
    }
    
    public boolean getOnGround()
    {
        return onGround;
    }
}
