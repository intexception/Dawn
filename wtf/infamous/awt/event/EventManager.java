package wtf.infamous.awt.event;

import java.lang.reflect.*;
import java.util.*;
import java.lang.annotation.*;

public class EventManager
{
    private Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP;
    
    public EventManager() {
        this.REGISTRY_MAP = new HashMap<Class<? extends Event>, ArrayHelper<Data>>();
    }
    
    public void register(final Object o) {
        Method[] declaredMethods;
        for (int length = (declaredMethods = o.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            if (!this.isMethodBad(method)) {
                this.register(method, o);
            }
        }
    }
    
    public void register(final Object o, final Class<? extends Event> clazz) {
        Method[] declaredMethods;
        for (int length = (declaredMethods = o.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            if (!this.isMethodBad(method, clazz)) {
                this.register(method, o);
            }
        }
    }
    
    private void register(final Method method, final Object o) {
        final Class<?> clazz = method.getParameterTypes()[0];
        final Data methodData = new Data(o, method, method.getAnnotation(EventTarget.class).value());
        if (!methodData.target.isAccessible()) {
            methodData.target.setAccessible(true);
        }
        if (this.REGISTRY_MAP.containsKey(clazz)) {
            if (!this.REGISTRY_MAP.get(clazz).contains(methodData)) {
                this.REGISTRY_MAP.get(clazz).add(methodData);
                this.sortListValue((Class<? extends Event>)clazz);
            }
        }
        else {
            this.REGISTRY_MAP.put((Class<? extends Event>)clazz, new ArrayHelper<Data>(methodData) {
                {
                    this.add(t);
                }
            });
        }
    }
    
    public void unregister(final Object o) {
        for (final ArrayHelper<Data> flexibalArray : this.REGISTRY_MAP.values()) {
            for (final Data methodData : flexibalArray) {
                if (methodData.source.equals(o)) {
                    flexibalArray.remove(methodData);
                }
            }
        }
        this.cleanMap(true);
    }
    
    public void unregister(final Object o, final Class<? extends Event> clazz) {
        if (this.REGISTRY_MAP.containsKey(clazz)) {
            for (final Data methodData : this.REGISTRY_MAP.get(clazz)) {
                if (methodData.source.equals(o)) {
                    this.REGISTRY_MAP.get(clazz).remove(methodData);
                }
            }
            this.cleanMap(true);
        }
    }
    
    public void cleanMap(final boolean b) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!b || iterator.next().getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }
    
    public void removeEnty(final Class<? extends Event> clazz) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> iterator = this.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey().equals(clazz)) {
                iterator.remove();
                break;
            }
        }
    }
    
    private void sortListValue(final Class<? extends Event> clazz) {
        final ArrayHelper<Data> flexibleArray = new ArrayHelper<Data>();
        byte[] value_ARRAY;
        for (int length = (value_ARRAY = Priority.VALUE_ARRAY).length, i = 0; i < length; ++i) {
            final byte b = value_ARRAY[i];
            for (final Data methodData : this.REGISTRY_MAP.get(clazz)) {
                if (methodData.priority == b) {
                    flexibleArray.add(methodData);
                }
            }
        }
        this.REGISTRY_MAP.put(clazz, flexibleArray);
    }
    
    private boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }
    
    private boolean isMethodBad(final Method method, final Class<? extends Event> clazz) {
        return this.isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
    }
    
    public ArrayHelper<Data> get(final Class<? extends Event> clazz) {
        return this.REGISTRY_MAP.get(clazz);
    }
    
    public void shutdown() {
        this.REGISTRY_MAP.clear();
    }
}
