package wtf.infamous.awt.event.events;

import wtf.infamous.awt.event.*;

public class Event3D extends Event
{
    private float partialTicks;
    
    public Event3D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
}
