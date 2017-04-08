package org.mercuriusframework.providers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.listeners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Entity listeners provider
 */
@Service("entityListenerProvider")
public class EntityListenerProvider {
    /**
     * Multimaps of listeners
     */
    private static Multimap<Class, PrePersistEntityListener> prePersistListeners = HashMultimap.create();
    private static Multimap<Class, PreUpdateEntityListener> preUpdateListeners = HashMultimap.create();
    private static Multimap<Class, PreRemoveEntityListener> preRemoveListeners = HashMultimap.create();
    private static Multimap<Class, PostPersistEntityListener> postPersistListeners = HashMultimap.create();
    private static Multimap<Class, PostUpdateEntityListener> postUpdateListeners = HashMultimap.create();
    private static Multimap<Class, PostRemoveEntityListener> postRemoveListeners = HashMultimap.create();
    private static Multimap<Class, PostLoadEntityListener> postLoadListeners = HashMultimap.create();
    /**
     * Spring application context
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Init multimaps collections
     */
    @PostConstruct
    private void initCollections() {
        /** Pre-persist */
        Map prePersist = applicationContext.getBeansOfType(PrePersistEntityListener.class);
        for (Object temp : prePersist.values()) {
            PrePersistEntityListener listener = (PrePersistEntityListener) temp;
            prePersistListeners.put(listener.getEntityType(), listener);
        }
        /** Pre-update */
        Map preUpdate = applicationContext.getBeansOfType(PreUpdateEntityListener.class);
        for (Object temp : preUpdate.values()) {
            PreUpdateEntityListener listener = (PreUpdateEntityListener) temp;
            preUpdateListeners.put(listener.getEntityType(), listener);
        }
        /** Pre-remove */
        Map preRemove = applicationContext.getBeansOfType(PreRemoveEntityListener.class);
        for (Object temp : preRemove.values()) {
            PreRemoveEntityListener listener = (PreRemoveEntityListener) temp;
            preRemoveListeners.put(listener.getEntityType(), listener);
        }
        /** Post-persist */
        Map postPersist = applicationContext.getBeansOfType(PostPersistEntityListener.class);
        for (Object temp : postPersist.values()) {
            PostPersistEntityListener listener = (PostPersistEntityListener) temp;
            postPersistListeners.put(listener.getEntityType(), listener);
        }
        /** Post-update */
        Map postUpdate = applicationContext.getBeansOfType(PostUpdateEntityListener.class);
        for (Object temp : postUpdate.values()) {
            PostUpdateEntityListener listener = (PostUpdateEntityListener) temp;
            postUpdateListeners.put(listener.getEntityType(), listener);
        }
        /** Post-remove */
        Map postRemove = applicationContext.getBeansOfType(PostRemoveEntityListener.class);
        for (Object temp : postRemove.values()) {
            PostRemoveEntityListener listener = (PostRemoveEntityListener) temp;
            postRemoveListeners.put(listener.getEntityType(), listener);
        }
        /** Post-load */
        Map postLoad = applicationContext.getBeansOfType(PostLoadEntityListener.class);
        for (Object temp : postLoad.values()) {
            PostLoadEntityListener listener = (PostLoadEntityListener) temp;
            postLoadListeners.put(listener.getEntityType(), listener);
        }
    }

    /**
     * Get entity hierarchy classes
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return List of classes
     */
    public static <T extends AbstractEntity> List<Class> getEntityHierarchyClasses(T object) {
        Class clazz = object.getClass();
        if (clazz.equals(AbstractEntity.class)) {
            return Collections.singletonList(clazz);
        } else {
            ArrayList<Class> result = new ArrayList<Class>();
            result.add(clazz);
            Class superClass = clazz.getSuperclass();
            while (!superClass.equals(AbstractEntity.class)) {
                result.add(superClass);
                superClass = superClass.getSuperclass();
            }
            result.add(superClass);
            return result;
        }
    }

    /**
     * Get pre-persist entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PrePersistEntityListener> getPrePersistListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PrePersistEntityListener> result = new HashSet<PrePersistEntityListener>();
        for (Class clazz : classes) {
            result.addAll(prePersistListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get pre-update entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PreUpdateEntityListener> getPreUpdateListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PreUpdateEntityListener> result = new HashSet<PreUpdateEntityListener>();
        for (Class clazz : classes) {
            result.addAll(preUpdateListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get pre-remove entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PreRemoveEntityListener> getPreRemoveListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PreRemoveEntityListener> result = new HashSet<PreRemoveEntityListener>();
        for (Class clazz : classes) {
            result.addAll(preRemoveListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get post-persist entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PostPersistEntityListener> getPostPersistListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PostPersistEntityListener> result = new HashSet<PostPersistEntityListener>();
        for (Class clazz : classes) {
            result.addAll(postPersistListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get post-update entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PostUpdateEntityListener> getPostUpdateListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PostUpdateEntityListener> result = new HashSet<PostUpdateEntityListener>();
        for (Class clazz : classes) {
            result.addAll(postUpdateListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get post-remove entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PostRemoveEntityListener> getPostRemoveListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PostRemoveEntityListener> result = new HashSet<PostRemoveEntityListener>();
        for (Class clazz : classes) {
            result.addAll(postRemoveListeners.get(clazz));
        }
        return result;
    }

    /**
     * Get post-load entity listeners
     *
     * @param object Entity object
     * @param <T>    Entity type
     * @return Set of listeners
     */
    public static <T extends AbstractEntity> Set<PostLoadEntityListener> getPostLoadListeners(T object) {
        List<Class> classes = getEntityHierarchyClasses(object);
        Set<PostLoadEntityListener> result = new HashSet<PostLoadEntityListener>();
        for (Class clazz : classes) {
            result.addAll(postLoadListeners.get(clazz));
        }
        return result;
    }

}
