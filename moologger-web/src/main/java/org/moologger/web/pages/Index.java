package org.moologger.web.pages;

import java.io.File;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.services.UploadedFile;

public class Index {
	
	@Property
    private UploadedFile file;

	public void onSuccess() {
		File copied = new File(file.getFileName());
        
        file.write(copied);
	}
	  
}