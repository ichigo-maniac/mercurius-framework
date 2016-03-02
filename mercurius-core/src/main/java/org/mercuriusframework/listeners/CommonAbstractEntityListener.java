package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.helpers.EntityListenerProvider;

import javax.persistence.*;
import java.util.Set;


/**
 * Common abstract entity listener
 */
public class CommonAbstractEntityListener {
    /**
     * On "pre-persist" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PrePersist
    private void onPrePersist(AbstractEntity abstractEntity) {
        Set<PrePersistEntityListener> listeners = EntityListenerProvider.getPrePersistListeners(abstractEntity);
        for (PrePersistEntityListener listener : listeners) {
            listener.prePersist(abstractEntity);
        }
    }

    /**
     * On "pre-update" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PreUpdate
    private void onPreUpdate(AbstractEntity abstractEntity) {
        Set<PreUpdateEntityListener> listeners = EntityListenerProvider.getPreUpdateListeners(abstractEntity);
        for (PreUpdateEntityListener listener : listeners) {
            listener.preUpdate(abstractEntity);
        }

    }

    /**
     * On "pre-remove" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PreRemove
    private void onPreRemove(AbstractEntity abstractEntity) {
        Set<PreRemoveEntityListener> listeners = EntityListenerProvider.getPreRemoveListeners(abstractEntity);
        for (PreRemoveEntityListener listener : listeners) {
            listener.preRemove(abstractEntity);
        }
    }

    /**
     * On "post-persist" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PostPersist
    private void onPostPersist(AbstractEntity abstractEntity) {
        Set<PostPersistEntityListener> listeners = EntityListenerProvider.getPostPersistListeners(abstractEntity);
        for (PostPersistEntityListener listener : listeners) {
            listener.postPersist(abstractEntity);
        }
    }

    /**
     * On "post-update" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PostUpdate
    private void onPostUpdate(AbstractEntity abstractEntity) {
        Set<PostUpdateEntityListener> listeners = EntityListenerProvider.getPostUpdateListeners(abstractEntity);
        for (PostUpdateEntityListener listener : listeners) {
            listener.postUpdate(abstractEntity);
        }
    }

    /**
     * On "post-remove" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PostRemove
    private void onPostRemove(AbstractEntity abstractEntity) {
        Set<PostRemoveEntityListener> listeners = EntityListenerProvider.getPostRemoveListeners(abstractEntity);
        for (PostRemoveEntityListener listener : listeners) {
            listener.postRemove(abstractEntity);
        }
    }

    /**
     * On "post-load" handler
     *
     * @param abstractEntity Abstract entity
     */
    @PostLoad
    private void onPostLoad(AbstractEntity abstractEntity) {
        Set<PostLoadEntityListener> listeners = EntityListenerProvider.getPostLoadListeners(abstractEntity);
        for (PostLoadEntityListener listener : listeners) {
            listener.postLoad(abstractEntity);
        }
    }

}
