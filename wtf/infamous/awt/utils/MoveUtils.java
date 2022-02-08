package wtf.infamous.awt.utils;

import net.minecraft.util.*;

public class MoveUtils
{
    private double motionX;
    private double motionZ;
    
    public void setSpeed(final double speed) {
        this.motionX = -MathHelper.sin(getDirection()) * speed;
        this.motionZ = MathHelper.cos(getDirection()) * speed;
    }
    
    public static float getDirection() {
        return 0.0f;
    }
    
    public static boolean isMoving() {
        return false;
    }
    
    public static boolean isOnGround(final double d) {
        return false;
    }
}
