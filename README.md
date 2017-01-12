# AlumniLinkBackend
Spring backend for android application: AlumniLink

## To build
    ./mvnw clean package

## To run
    java -jar target/AlumniLinkBackend-0.1.0.jar


#Test

## Using cURL on *nix machines

## set remote destination
    DEST="localhost:8080"

## To test if backend is up
    curl "$DEST/greeting"

## To test if backend is up
    curl "$DEST/greeting"

## To test if we can set name
    curl "$DEST/greeting?name=steve"

## To test file upload
    curl "$DEST/store"

## To test single file upload like ``hello.test``
### clean and recreate a file named hello.test contains "hello world"
    rm -rf hello.test
    echo "hello world" > hello.test
    curl -F "file=@hello.test" "$DEST/store"

## To verify the file has been uploaded and can be accessed through URL
    curl "$DEST/store/files/hello.test"

