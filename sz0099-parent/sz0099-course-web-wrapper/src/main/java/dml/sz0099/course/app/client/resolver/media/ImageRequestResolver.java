/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.jit8j.core.util.FileUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import dml.sz0099.course.app.client.validator.media.ImageExtendValidator;
import dml.sz0099.course.app.client.wrapper.media.ImageRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-22 13:15:46
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-22	basic init
 * 
 * @formatter:on
 * </pre>
 */
public class ImageRequestResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRequestResolver.class);
	
	
	
	private String basePath;

	//@Value("${file.upload.core.defaultPath.dir}")
	private String basePathFolder;

	//@Value("${file.upload.core.access.url.basePath}")
	private String accessUrlBasePath;
	
	private String accessUrlMapping;
	
	
	@Autowired
	private ImageExtendValidator imageExtendValidator;
	
	@Autowired
	private ImageRefWrapper imageRefWrapper;

	/*public boolean validateImageRequest(ImageRequest request) {
		request = trimRequest(request);
		ImageExtend extend = resolveImageRequest(request);
		
		boolean checked = imageExtendValidator.validateImageExtend(extend);
		if(!checked) {
			return false;
		}
		
		return true;
	}*/
	
	private ImageRequest trimRequest(ImageRequest request) {
		request.setDevId(StringUtils.trim(request.getDevId()));
		request.setModule(StringUtils.trim(request.getModule()));
		request.setPosition(StringUtils.trim(request.getPosition()));
		request.setPositionId(request.getPositionId());
		request.setProject(StringUtils.trim(request.getProject()));
		request.setUserId(request.getUserId());
		request.setVariety(StringUtils.trim(request.getVariety()));
		LOGGER.debug(GsonBuilderUtils.toJsonPretty(request));
		return request;
	}
	
	public ImageExtend resolveImageRequestForDelete(ImageRequest request) {
		ImageExtend extend = new ImageExtend();
		extend.setDevId(StringUtils.trim(request.getDevId()));
		extend.setModule(StringUtils.trim(request.getModule()));
		extend.setPosition(StringUtils.trim(request.getPosition()));
		extend.setPositionId(request.getPositionId());
		extend.setProject(StringUtils.trim(request.getProject()));
		extend.setUserId(request.getUserId());
		extend.setVariety(StringUtils.trim(request.getVariety()));
		//执行校验并生成extendId
		extend = imageExtendValidator.validateImageExtend(extend);
		/*if(extend.getSuccess()!=ImageExtend.SUCCESS_YES) {
			//校验不通过，直接返回
			return extend;
		}*/
		return extend;
	}

	/**
	 * @param request
	 * @return
	 */
	public ImageExtend resolveImageRequest(ImageRequest request) {
		
		request = trimRequest(request);
		MultipartFile[] files = request.getFiles();
		ImageExtend extend = null;
		
		if(null == files || files.length==0) {
			extend = new ImageExtend();
			extend.setSuccess(ImageExtend.SUCCESS_NO);//没有文件上传，失败
			extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_IMAGE_EXTEND_NOFILES);
			extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_IMAGE_EXTEND_NOFILES);
			return extend;
		}
		
		LOGGER.debug(">>>>resolveImageRequest begin>>>>>>>");
		
		if(null != files && files.length>0) {
			Integer filesNum = files.length;
			extend = new ImageExtend();
			extend.setDevId(StringUtils.trim(request.getDevId()));
			extend.setModule(StringUtils.trim(request.getModule()));
			extend.setPosition(StringUtils.trim(request.getPosition()));
			extend.setPositionId(request.getPositionId());
			extend.setProject(StringUtils.trim(request.getProject()));
			extend.setUserId(request.getUserId());
			extend.setVariety(StringUtils.trim(request.getVariety()));
			extend.setDomain(accessUrlBasePath);
			//执行校验并生成extendId
			extend = imageExtendValidator.validateImageExtendThenCreate(extend);
			if(extend.getSuccess()!=ImageExtend.SUCCESS_YES) {
				//校验不通过，直接返回
				return extend;
			}
			
			//验证数量
			Long extendId = extend.getId();
			Integer mainMaxnum = extend.getMainMaxnum();
			Integer subMaxnum = extend.getSubMaxnum();
			Long sizeMax = extend.getSizeMax();
			
			Long mainId = request.getMainId();
			Long subId = request.getSubId();
			
			ImageRef imageRef = new ImageRef();
			imageRef.setMainId(mainId);
			imageRef.setSubId(subId);
			imageRef.setExtendId(extendId);
			
			if(null == mainId) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINID_EMPTY);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINID_EMPTY);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			if(null == subId) {
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBID_EMPTY);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBID_EMPTY);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			Long subNum = imageRefWrapper.countForSub(imageRef);
			if(subNum+filesNum>subMaxnum) {
				//子类图片数已超限
				
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SUBNUM_EXTRA);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SUBNUM_EXTRA);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				
				return extend;
			}
			
			Long mainNum = imageRefWrapper.countForMain(imageRef);
			if(mainNum+filesNum>mainMaxnum) {
				//主体图片数已超限
				extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_MAINNUM_EXTRA);
				extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_MAINNUM_EXTRA);
				extend.setSuccess(ImageExtend.SUCCESS_NO);
				return extend;
			}
			
			List<Imagebase> images = new ArrayList<>(files.length);
			extend.setImages(images);
			//long i=0;
			for(MultipartFile file : files) {
				Long size = file.getSize();
				if(size>sizeMax) {
					//单个图片大小超限,中断执行
					extend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_SIZE_EXTRA);
					extend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_SIZE_EXTRA);
					extend.setSuccess(ImageExtend.SUCCESS_NO);
					return extend;
				}
				//i++;
				
				Long userId = request.getUserId();
				//全部校验通过，生成图片保存路径、访问路径等信息，并将其持久化
				Imagebase imagebase = new Imagebase();
				images.add(imagebase);
				//imagebase.setExtend(extend);
				imagebase.setExtendId(extendId);
				
				imagebase.setUserId(userId);
				imagebase.setCreatedBy(userId);
				imagebase.setLastModifiedBy(userId);
				
				Integer strategy = request.getStrategy();
				if(null == strategy || 0==strategy) {
					strategy=1;
				}
				imagebase.setStrategy(strategy);
				
				ImageRef ref = new ImageRef();
				imagebase.setImageRef(ref);
				
				ref.setExpectedW(request.getExpectedW());
				ref.setUserId(userId);
				ref.setMainId(request.getMainId());
				ref.setSubId(request.getSubId());
				ref.setExtendId(extendId);
				//ref.setExtend(extend);
				ref.setCreatedBy(userId);
				ref.setLastModifiedBy(userId);
				
				
				imagebase.setSize(size);
				imagebase.setContentType(file.getContentType());
				String original = StringUtils.trim(file.getOriginalFilename());
				imagebase.setOriginal(original);
				
				String suffix = ImagePathUtil.splitForSuffix(original);
				imagebase.setSuffix(suffix);
				
				//生成文件名,文件名即id
				Long id = ImagePathUtil.generateFilename();
				String filename = String.valueOf(id);
				imagebase.setId(id);
				ref.setBaseId(id);
				imagebase.setFilename(filename);
				
				String subIdPath = ImagePathUtil.buildSubIdPath(extend,  mainId,  subId, filename);
				String relativePath = ImagePathUtil.buildFilePathForRelative(Imagebase.ACCESS_TYPE_ORIGINAL,subIdPath);
				String absolutePath = ImagePathUtil.buildFilePathForAbsolute(  basePath,  basePathFolder,  relativePath);
				String fullnameWithAbsolute = ImagePathUtil.buildFullname(absolutePath, filename, suffix);
				String fullnameWithRelative = ImagePathUtil.buildFullname(relativePath, filename, suffix);
				String accessUrl = ImagePathUtil.buildAccessUrl(this.accessUrlMapping,fullnameWithRelative);
				
				imagebase.setAbsolute(absolutePath);//绝对路径，不含文件名
				imagebase.setAccessUrl(accessUrl);//不含域名
				imagebase.setRelative(relativePath);//相对路径，不含文件名
				
				
				try {
					File destination = new File(fullnameWithAbsolute);
					LOGGER.debug(absolutePath);
					ImagePathUtil.persist2Disk(absolutePath, destination, file.getInputStream());
					imagebase.setFile(destination);
				} catch (IOException e) {
					LOGGER.error("!!!!! IOException,{} ", e);
				}
			}
			extend.setSuccess(ImageExtend.SUCCESS_YES);//解析成功
			LOGGER.debug(GsonBuilderUtils.toJsonPretty(extend));
			
		}
		LOGGER.debug(">>>>resolveImageRequest end>>>>>>>");
		return extend;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String original = StringUtils.trim("product.jpg");
		
		String suffix = ImagePathUtil.splitForSuffix(original);
		
		//生成文件名
		Long id = ImagePathUtil.generateFilename();
		String filename = String.valueOf(id);
		
		StringBuilder sb = new StringBuilder();
		ImageExtend extend = new ImageExtend();
		extend.setDevId("sz0099");
		extend.setModule("product");
		extend.setProject("ood");
		extend.setVariety("productV");
		extend.setPosition("paragraph");
		Long mainId = 1222l;
		Long subId = 4444l;
		String subIdPath = ImagePathUtil.buildSubIdPath(extend,  mainId,  subId, filename);
		String relativePath = ImagePathUtil.buildFilePathForRelative(Imagebase.ACCESS_TYPE_ORIGINAL,subIdPath);
		String absolutePath = ImagePathUtil.buildFilePathForAbsolute(  "F:/fileinfo",  "default",  relativePath);
		String fullname = ImagePathUtil.buildFullname(relativePath, filename, suffix);
		String accessUrl = ImagePathUtil.buildAccessUrl("assert/upload",fullname);
		
		System.out.println(subIdPath);
		System.out.println(relativePath);
		System.out.println(absolutePath);
		System.out.println(fullname);
		System.out.println(accessUrl);
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePathFolder() {
		return basePathFolder;
	}

	public void setBasePathFolder(String basePathFolder) {
		this.basePathFolder = basePathFolder;
	}

	public String getAccessUrlBasePath() {
		return accessUrlBasePath;
	}

	public void setAccessUrlBasePath(String accessUrlBasePath) {
		this.accessUrlBasePath = accessUrlBasePath;
	}

	public String getAccessUrlMapping() {
		return accessUrlMapping;
	}

	public void setAccessUrlMapping(String accessUrlMapping) {
		this.accessUrlMapping = accessUrlMapping;
	}
}
