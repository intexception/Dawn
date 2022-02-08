package wtf.infamous.awt.module.player;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.entity.*;
import wtf.infamous.awt.event.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;

public class Scaffold extends Module
{
    public Scaffold() {
        super("scaffold", 47, Category.PLAYER);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.isToggled()) {
            final Entity p = this.mc.thePlayer;
            final BlockPos bp = new BlockPos(p.posX, p.getEntityBoundingBox().minY, p.posZ);
            if (this.valid(bp.add(0, -1, 0))) {
                this.place(bp.add(0, -1, -1), EnumFacing.UP);
            }
            else if (this.valid(bp.add(-1, -1, 0))) {
                this.place(bp.add(0, -1, 0), EnumFacing.EAST);
            }
            else if (this.valid(bp.add(1, -1, 0))) {
                this.place(bp.add(0, -1, -1), EnumFacing.WEST);
            }
            else if (this.valid(bp.add(0, -1, -1))) {
                this.place(bp.add(0, -1, 0), EnumFacing.SOUTH);
            }
            else if (this.valid(bp.add(0, -1, 1))) {
                this.place(bp.add(0, -1, 0), EnumFacing.NORTH);
            }
            else if (this.valid(bp.add(1, -1, 1))) {
                if (this.valid(bp.add(0, -1, 1))) {
                    this.place(bp.add(0, -1, 1), EnumFacing.NORTH);
                }
                this.place(bp.add(1, -1, 1), EnumFacing.EAST);
            }
            else if (this.valid(bp.add(-1, -1, 1))) {
                if (this.valid(bp.add(-1, -1, 0))) {}
                this.place(bp.add(0, -1, 1), EnumFacing.WEST);
                this.place(bp.add(-1, -1, -1), EnumFacing.SOUTH);
            }
            else if (this.valid(bp.add(-1, -1, -1))) {
                if (this.valid(bp.add(0, -1, -1))) {}
                this.place(bp.add(0, -1, 1), EnumFacing.SOUTH);
                this.place(bp.add(-1, -1, 1), EnumFacing.WEST);
            }
            else if (this.valid(bp.add(1, -1, -1))) {
                if (this.valid(bp.add(1, -1, 0))) {}
                this.place(bp.add(1, -1, 0), EnumFacing.EAST);
                this.place(bp.add(1, -1, 1), EnumFacing.NORTH);
            }
        }
    }
    
    void place(BlockPos p, final EnumFacing f) {
        if (f == EnumFacing.UP) {
            p = p.add(0, -1, 0);
        }
        else if (f == EnumFacing.NORTH) {
            p = p.add(0, 0, 1);
        }
        else if (f == EnumFacing.EAST) {
            p = p.add(-1, 0, 0);
        }
        else if (f == EnumFacing.SOUTH) {
            p = p.add(0, 0, 1);
        }
        else if (f == EnumFacing.WEST) {
            p = p.add(1, 0, 0);
        }
        final EntityPlayerSP _p = this.mc.thePlayer;
        if (_p.getHeldItem() != null && _p.getHeldItem().getItem() instanceof ItemBlock) {
            this.mc.playerController.onPlayerRightClick(_p, this.mc.theWorld, _p.getHeldItem(), p, f, new Vec3(0.5, 0.5, 0.5));
            final double x = p.getX() + 0.25 - _p.posX;
            final double y = p.getY() + 0.25 - _p.posY;
            final double z = p.getZ() + 0.25 - _p.posZ;
            final double distance = MathHelper.sqrt_double(x * x + z * z);
            final float yaw = (float)(Math.atan2(z, x) * 100.0 / 3.141592653589793 - 90.0);
            final float pitch = (float)(-(Math.atan2(y, distance) * 100.0 / 3.141592653589793));
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(_p.posX, _p.posZ, _p.posZ, yaw, pitch, _p.onGround));
        }
    }
    
    boolean valid(final BlockPos p) {
        final Block b = this.mc.theWorld.getBlock(p);
        return !(b instanceof BlockLiquid) && b.getMaterial() != Material.air;
    }
}
