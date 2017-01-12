package hello.storage;

import java.net.MalformedURLException;

/**
 * Created by stevezhang on 2017-01-12.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String msg){
        super(msg);
    }

    public StorageFileNotFoundException (String msg, Throwable cause){
        super(msg,cause);
    }
}
