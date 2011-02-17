package org.cvpcs.bukkit.magickraft;

public class InvalidRuneSetException extends Exception {
    private static final long serialVersionUID = 6022290094175494792L;
    private final Throwable mCause;
    
    public InvalidRuneSetException() {
        this(null);
    }
    
    public InvalidRuneSetException(Throwable throwable) {
        mCause = throwable;
    }
    
    @Override
    public Throwable getCause() {
        return mCause;
    }
}
