package hello.controller;

import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.codehaus.plexus.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;


@Slf4j
@Controller
public class UploadController {
    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/store")
    public String listUploadedFiles(Model model) throws IOException {
        log.info("store get view");
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(UploadController.class, "downloadFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    // on success return status 200
    // filename in HTTP header, file content in HTTP body
    @GetMapping("/downloads/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        log.info("trying to download filename: " + filename);
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = "+file.getFilename() + "/")
                .body(file);
    }

    //File uploads = new File(properties.getProperty("upload.location"));
    @GetMapping("/store/files/{filename:.+}")
    @ResponseBody
    public void serveFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        log.info("trying to server filename: "+filename);
        Resource file = storageService.loadAsResource(filename);
        InputStream in = file.getInputStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtil.copy(in, response.getOutputStream());
    }

    // curl -F "file=@new.pdf" localhost:8080/store
    @PostMapping("/store")
    @ResponseBody
    public String handleFIleUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes){
        log.info("Trying to store file " + file.getName());
        storageService.store(file);
        return "{\"success\":1}";
    }

    // on Exception return 404
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException sfnfe){
        return ResponseEntity.notFound().build();
    }

}
