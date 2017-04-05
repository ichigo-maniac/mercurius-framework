package org.mercuriusframework.facades;

/**
 * Task facade interface
 */
public interface TaskFacade {
    /**
     * Run task
     * @param taskCode Task entity code
     */
    void runTask(String taskCode);
}
