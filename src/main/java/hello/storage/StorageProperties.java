package hello.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     *  dir location for file root path
     */

    private String location = "upload-dir";

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
