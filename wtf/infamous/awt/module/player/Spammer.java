package wtf.infamous.awt.module.player;

import net.minecraft.entity.*;
import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;
import net.minecraft.util.*;

public class Spammer extends Module
{
    EntityLivingBase target;
    long current;
    long last;
    int delay;
    float yaw;
    float pitch;
    boolean others;
    
    public Spammer() {
        super("spammer", 0, Category.PLAYER);
        this.delay = 2;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
    }
    
    private BlockPos pos(final double posX, final double d, final double posZ) {
        return null;
    }
}
