package optfine;

import net.minecraft.util.*;
import java.nio.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.texture.*;
import java.util.*;

public class TextureAnimation
{
    private String srcTex;
    private String dstTex;
    ResourceLocation dstTexLoc;
    private int dstTextId;
    private int dstX;
    private int dstY;
    private int frameWidth;
    private int frameHeight;
    private CustomAnimationFrame[] frames;
    private int activeFrame;
    byte[] srcData;
    private ByteBuffer imageData;
    
    public TextureAnimation(final String p_i63_1_, final byte[] p_i63_2_, final String p_i63_3_, final ResourceLocation p_i63_4_, final int p_i63_5_, final int p_i63_6_, final int p_i63_7_, final int p_i63_8_, final Properties p_i63_9_, final int p_i63_10_) {
        this.srcTex = null;
        this.dstTex = null;
        this.dstTexLoc = null;
        this.dstTextId = -1;
        this.dstX = 0;
        this.dstY = 0;
        this.frameWidth = 0;
        this.frameHeight = 0;
        this.frames = null;
        this.activeFrame = 0;
        this.srcData = null;
        this.imageData = null;
        this.srcTex = p_i63_1_;
        this.dstTex = p_i63_3_;
        this.dstTexLoc = p_i63_4_;
        this.dstX = p_i63_5_;
        this.dstY = p_i63_6_;
        this.frameWidth = p_i63_7_;
        this.frameHeight = p_i63_8_;
        final int i = p_i63_7_ * p_i63_8_ * 4;
        if (p_i63_2_.length % i != 0) {
            Config.warn("Invalid animated texture length: " + p_i63_2_.length + ", frameWidth: " + p_i63_7_ + ", frameHeight: " + p_i63_8_);
        }
        this.srcData = p_i63_2_;
        int j = p_i63_2_.length / i;
        if (p_i63_9_.get("tile.0") != null) {
            for (int k = 0; p_i63_9_.get("tile." + k) != null; ++k) {
                j = k + 1;
            }
        }
        final String s2 = ((Hashtable<K, String>)p_i63_9_).get("duration");
        final int l = Config.parseInt(s2, p_i63_10_);
        this.frames = new CustomAnimationFrame[j];
        for (int i2 = 0; i2 < this.frames.length; ++i2) {
            final String s3 = ((Hashtable<K, String>)p_i63_9_).get("tile." + i2);
            final int j2 = Config.parseInt(s3, i2);
            final String s4 = ((Hashtable<K, String>)p_i63_9_).get("duration." + i2);
            final int k2 = Config.parseInt(s4, l);
            final CustomAnimationFrame customanimationframe = new CustomAnimationFrame(j2, k2);
            this.frames[i2] = customanimationframe;
        }
    }
    
    public boolean nextFrame() {
        if (this.frames.length <= 0) {
            return false;
        }
        if (this.activeFrame >= this.frames.length) {
            this.activeFrame = 0;
        }
        final CustomAnimationFrame customAnimationFrame;
        final CustomAnimationFrame customanimationframe = customAnimationFrame = this.frames[this.activeFrame];
        ++customAnimationFrame.counter;
        if (customanimationframe.counter < customanimationframe.duration) {
            return false;
        }
        customanimationframe.counter = 0;
        ++this.activeFrame;
        if (this.activeFrame >= this.frames.length) {
            this.activeFrame = 0;
        }
        return true;
    }
    
    public int getActiveFrameIndex() {
        if (this.frames.length <= 0) {
            return 0;
        }
        if (this.activeFrame >= this.frames.length) {
            this.activeFrame = 0;
        }
        final CustomAnimationFrame customanimationframe = this.frames[this.activeFrame];
        return customanimationframe.index;
    }
    
    public int getFrameCount() {
        return this.frames.length;
    }
    
    public boolean updateTexture() {
        if (this.dstTextId < 0) {
            final ITextureObject itextureobject = TextureUtils.getTexture(this.dstTexLoc);
            if (itextureobject == null) {
                return false;
            }
            this.dstTextId = itextureobject.getGlTextureId();
        }
        if (this.imageData == null) {
            (this.imageData = GLAllocation.createDirectByteBuffer(this.srcData.length)).put(this.srcData);
            this.srcData = null;
        }
        if (!this.nextFrame()) {
            return false;
        }
        final int k = this.frameWidth * this.frameHeight * 4;
        final int i = this.getActiveFrameIndex();
        final int j = k * i;
        if (j + k > this.imageData.capacity()) {
            return false;
        }
        this.imageData.position(j);
        GlStateManager.bindTexture(this.dstTextId);
        GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.imageData);
        return true;
    }
    
    public String getSrcTex() {
        return this.srcTex;
    }
    
    public String getDstTex() {
        return this.dstTex;
    }
    
    public ResourceLocation getDstTexLoc() {
        return this.dstTexLoc;
    }
}