package wtf.infamous.awt.module.combat;

import wtf.infamous.awt.module.*;
import java.util.*;
import wtf.infamous.awt.*;
import wtf.infamous.awt.gui.settings.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.client.entity.*;
import wtf.infamous.awt.event.*;

public class Velocity extends Module
{
    public Velocity() {
        super("velocity", 0, Category.COMBAT);
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        options.add("packet");
        Dawn.settingsManager.rSetting(new Setting("Hori", this, 90.0, 0.0, 100.0, true));
        Dawn.settingsManager.rSetting(new Setting("Veri", this, 100.0, 0.0, 100.0, true));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final String mode = Dawn.settingsManager.getSettingByName("Velocity Mode").getValString();
        this.setDisplayName("velocity §7" + mode);
        if (mode.equalsIgnoreCase("packet") && this.mc.thePlayer.hurtTime == this.mc.thePlayer.maxHurtTime && this.mc.thePlayer.maxHurtTime > 0) {
            final float hori = (float)Dawn.settingsManager.getSettingByName("Hori").getValDouble();
            final float veri = (float)Dawn.settingsManager.getSettingByName("Veri").getValDouble();
            final EntityPlayerSP thePlayer = this.mc.thePlayer;
            thePlayer.motionX *= hori / 100.0f;
            final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
            thePlayer2.motionY *= veri / 100.0f;
            final EntityPlayerSP thePlayer3 = this.mc.thePlayer;
            thePlayer3.motionZ *= hori / 100.0f;
        }
    }
}
