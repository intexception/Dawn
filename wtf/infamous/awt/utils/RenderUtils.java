package wtf.infamous.awt.utils;

import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;

public class RenderUtils
{
    private static final Frustum frustum;
    
    static {
        frustum = new Frustum();
    }
    
    public static double interpolate(final double newPos, final double oldPos) {
        return oldPos + (newPos - oldPos) * Minecraft.getMinecraft().timer.renderPartialTicks;
    }
    
    public static boolean isInFrustumView(final Entity ent) {
        final Entity current = Minecraft.getMinecraft().getRenderViewEntity();
        RenderUtils.frustum.setPosition(interpolate(current.posX, current.lastTickPosX), interpolate(current.posY, current.lastTickPosY), interpolate(current.posZ, current.lastTickPosZ));
        return RenderUtils.frustum.isBoundingBoxInFrustum(ent.getEntityBoundingBox()) || ent.ignoreFrustumCheck;
    }
}
