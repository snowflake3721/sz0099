/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-12-06 23:22:35
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-12-06	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class Picture {
	
	MultipartFile file;
	
	String pic;
	
	String picname;
	
	Picture[] files;


	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Picture[] getFiles() {
		return files;
	}

	public void setFiles(Picture[] files) {
		this.files = files;
	}
}
