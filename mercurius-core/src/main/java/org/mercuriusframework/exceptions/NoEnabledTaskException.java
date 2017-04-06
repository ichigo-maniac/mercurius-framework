package org.mercuriusframework.exceptions;

/**
 * No enabled task exception
 */
public class NoEnabledTaskException extends RuntimeException {

    /**
     * Constructor
     * @param taskCode Task code
     */
    public NoEnabledTaskException(String taskCode) {
        super("No Task with code \"" + taskCode + "\" has been found");
    }
}
