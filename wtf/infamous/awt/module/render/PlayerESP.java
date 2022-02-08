package wtf.infamous.awt.module.render;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.entity.player.*;
import wtf.infamous.awt.utils.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.*;
import java.util.*;
import wtf.infamous.awt.event.*;

public class PlayerESP extends Module
{
    private float oldBrightness;
    ResourceLocation cute1;
    
    public PlayerESP() {
        super("playeresp", 0, Category.RENDER);
        this.cute1 = new ResourceLocation("dawn/cute.png");
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.oldBrightness = this.mc.gameSettings.gammaSetting;
    }
    
    @EventTarget
    public void EventRender3D(final Event3D event) {
        for (final EntityPlayer p : this.mc.thePlayer.getEntityWorld().playerEntities) {
            if (RenderUtils.isInFrustumView(p) && !p.isInvisible() && p.isEntityAlive()) {
                if (p == this.mc.thePlayer) {
                    continue;
                }
                final double x = RenderUtils.interpolate(p.posX, p.lastTickPosX) - this.mc.getRenderManager().renderPosX;
                final double y = RenderUtils.interpolate(p.posY, p.lastTickPosY) - this.mc.getRenderManager().renderPosY;
                final double z = RenderUtils.interpolate(p.posZ, p.lastTickPosZ) - this.mc.getRenderManager().renderPosZ;
                GlStateManager.pushMatrix();
                GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
                GL11.glDisable(2929);
                final float distance = MathHelper.clamp_float(this.mc.thePlayer.getDistanceToEntity(p), 20.0f, Float.MAX_VALUE);
                final double scale = 0.005 * distance;
                GlStateManager.translate(x, y, z);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GlStateManager.scale(-0.1, -0.1, 0.0);
                this.mc.getTextureManager().bindTexture(this.cute1);
                Gui.drawScaledCustomSizeModalRect(p.width / 2.0f - distance / 3.0f, -p.height - distance, 0.0f, 0.0f, 1.0, 1.0, 252.0 * (scale / 2.0), 476.0 * (scale / 2.0), 1.0f, 1.0f);
                GL11.glEnable(2929);
                GlStateManager.popMatrix();
            }
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.gammaSetting = this.oldBrightness;
    }
}
