package hello.storage;

public class StorageException extends RuntimeException {
    public StorageException(String s) {
        super(s);
    }

    public StorageException(String msg, Throwable cause){
        super(msg,cause);
    }
}
