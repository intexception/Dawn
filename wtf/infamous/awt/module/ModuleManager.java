package wtf.infamous.awt.module;

import java.util.*;
import wtf.infamous.awt.module.combat.*;
import wtf.infamous.awt.module.movement.*;
import wtf.infamous.awt.module.render.*;
import wtf.infamous.awt.module.player.*;
import wtf.infamous.awt.module.misc.*;

public class ModuleManager
{
    private ArrayList<Module> modules;
    
    public ModuleManager() {
        (this.modules = new ArrayList<Module>()).add(new KillAura());
        this.modules.add(new AntiBot());
        this.modules.add(new AutoArmor());
        this.modules.add(new Criticals());
        this.modules.add(new Velocity());
        this.modules.add(new TargetStrafe());
        this.modules.add(new Fly());
        this.modules.add(new Strafe());
        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new LongJump());
        this.modules.add(new Speed());
        this.modules.add(new Phase());
        this.modules.add(new FanAnimation());
        this.modules.add(new Fullbright());
        this.modules.add(new ClickGUI());
        this.modules.add(new Chams());
        this.modules.add(new PlayerESP());
        this.modules.add(new ChestESP());
        this.modules.add(new NameTags());
        this.modules.add(new NoFall());
        this.modules.add(new Disabler());
        this.modules.add(new HackerDetecter());
        this.modules.add(new InvWalk());
        this.modules.add(new NoSlow());
        this.modules.add(new Scaffold());
        this.modules.add(new Spammer());
        this.modules.add(new SpeedMine());
        this.modules.add(new AutoLogin());
        this.modules.add(new AutoTool());
        this.modules.add(new AutoRegister());
        this.modules.add(new Effects());
        this.modules.add(new SelfDamage());
    }
    
    public ArrayList<Module> getModules() {
        return this.modules;
    }
    
    public Module getModuleByName(final String name) {
        return this.modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
