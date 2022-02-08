package wtf.infamous.awt.module.movement;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.*;
import java.util.*;
import wtf.infamous.awt.gui.settings.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;
import net.minecraft.util.*;
import net.minecraft.block.*;

public class Strafe extends Module
{
    float speed;
    float jump;
    
    public Strafe() {
        super("strafe", 44, Category.MOVEMENT);
        this.speed = (float)Dawn.settingsManager.getSettingByName("Speed").getValDouble();
        this.jump = (float)Dawn.settingsManager.getSettingByName("Jump").getValDouble();
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        options.add("Y-Port");
        Dawn.settingsManager.rSetting(new Setting("Speed Mode", this, "Y-Port", options));
        Dawn.settingsManager.rSetting(new Setting("Speed", this, 0.9, 0.0, 5.0, true));
        Dawn.settingsManager.rSetting(new Setting("Jump", this, 1.0, 0.0, 10.0, true));
    }
    
    @EventTarget
    public void onPre(final EventPreMotionUpdate event) {
        if (this.isOnLiquid()) {
            return;
        }
        if (this.mc.thePlayer.onGround && (this.mc.thePlayer.moveForward != 0.0f || this.mc.thePlayer.moveStrafing != 0.0f)) {
            this.setDisplayName("bhop §7godspeed");
            if (this.mc.thePlayer.ticksExisted % 2 != 0) {
                event.y += this.speed;
            }
            this.mc.thePlayer.jump();
            this.mc.thePlayer.setSpeed(3.2f);
            this.mc.thePlayer.jumpMovementFactor = this.jump;
        }
    }
    
    private boolean isOnLiquid() {
        boolean onLiquid = false;
        final int y = (int)(this.mc.thePlayer.boundingBox.minY - 0.01);
        for (int x = MathHelper.floor_double(this.mc.thePlayer.boundingBox.minX); x < MathHelper.floor_double(this.mc.thePlayer.boundingBox.maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double(this.mc.thePlayer.boundingBox.minZ); z < MathHelper.floor_double(this.mc.thePlayer.boundingBox.maxZ) + 1; ++z) {
                final Block block = this.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block != null && !(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    onLiquid = true;
                }
            }
        }
        return onLiquid;
    }
}
