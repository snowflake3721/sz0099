/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jit8j.core.util.FileUtils;
import org.jit8j.core.util.ImageUtils;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.jit8j.core.util.image.ImageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import dml.sz0099.course.app.persist.data.ImageStrategy;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import jodd.io.FileUtil;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-12-07 18:40:49
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-12-07	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ImagePathUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImagePathUtil.class);
	
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	
	public static String buildSubIdPath( ImageExtend extend, Long mainId, Long subId, String lastPathWithFilename) {
		StringBuilder sb = new StringBuilder();
		sb
		.append(extend.getDevId()).append("/")
		.append(extend.getProject()).append("/")
		.append(extend.getModule()).append("/")
		.append(extend.getVariety()).append("/")
		.append(extend.getPosition()).append("/")
		.append(FileUtils.buildDatePath())
		.append(mainId).append("/")
		.append(subId).append("/")
		.append(lastPathWithFilename).append("/")
		;
		return sb.toString();
	}
	
	public static String buildFilePathForAbsolute( String basePath, String basePathFolder, String relative) {
		
		StringBuilder sb = new StringBuilder();
		sb
		.append(basePath).append("/")
		.append(basePathFolder).append("/")
		.append(relative)
		;
		return sb.toString();
	}
	
	public static String buildFilePathForRelative(String accessType, String subIdPath) {
		StringBuilder sb = new StringBuilder();
		sb
		.append(accessType).append("/")
		.append(subIdPath)
		;
		return sb.toString();
	}
	
	public static Long generateFilename() {
		Long filename = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
		return filename;
	}
	
	public static String buildFullname(String path, String filename, String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(path).append(filename);
		if(StringUtils.isNotBlank(suffix)) {
			sb.append(".").append(suffix);
		}
		return sb.toString();
	}
	
	public static String splitForSuffix(String originalname) {
		if(StringUtils.isNotBlank(originalname)) {
			String[] o = originalname.split("\\.");
			if(o!=null && o.length>1) {
				return o[1];
			}
		}
		return "";
	}
	
	
	public static String buildAccessUrl(String mappingPre, String relativeWithFullname) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(mappingPre);
		if(StringUtils.isNotBlank(mappingPre)) {
			if(mappingPre.lastIndexOf(Imagebase.SYMBOL_SLASH)<(mappingPre.length()-1)) {
				sb.append(Imagebase.SYMBOL_SLASH);
			}
		}
		sb.append(relativeWithFullname);
		return sb.toString();
	}
	
	/**
	 * 生成图片基本信息，允许图片为空，则只生成id
	 * @param extend
	 * @param ref
	 * @param basePath
	 * @param basePathFolder
	 * @param accessUrlMapping
	 * @return
	 */
	public static ImageRef fillImageRef(ImageExtend extend, ImageRef ref, 
			String basePath, String basePathFolder, String accessUrlMapping
			) {
		
		Imagebase base = ref.getBase();
		Long baseId = ref.getBaseId();
		if(null == base) {
			base = new Imagebase();
			ref.setBase(base);
			base.setId(baseId);
		}
		if(null != extend) {
			Long extendId = extend.getId();
			ref.setExtendId(extendId);
			ref.setExtend(extend);
		}
		//生成文件名,文件名即id
		if(null == baseId) {
			baseId = ImagePathUtil.generateFilename();
			base.setId(baseId);
			ref.setBaseId(baseId);
			ref.setId(baseId);
		}
		String filename = String.valueOf(baseId);
		base.setFilename(filename);
		
		Long mainId=ref.getMainId();//文章id
		Long subId = ref.getSubId();//段落id
		
		String subIdPath = ImagePathUtil.buildSubIdPath(extend, mainId, subId, filename);
		String relativePath = ImagePathUtil.buildFilePathForRelative(Imagebase.ACCESS_TYPE_ORIGINAL,subIdPath);
		String absolutePath = ImagePathUtil.buildFilePathForAbsolute(basePath,  basePathFolder,  relativePath);
		base.setAbsolute(absolutePath);//绝对路径，不含文件名
		base.setRelative(relativePath);//相对路径，不含文件名
		
		Integer type = ref.getType();
		
		MultipartFile file = ref.getFile();
		if(null != file && ImageRef.TYPE_IMG.getValueInt().equals(type)) {
			String originalFilename = base.getOriginal();
			if(StringUtils.isBlank(originalFilename)) {
				originalFilename = file.getOriginalFilename();
			}
			base.setSize(file.getSize());
			base.setContentType(file.getContentType());
			base.setOriginal(originalFilename);
			String suffix = ImagePathUtil.splitForSuffix(originalFilename);
			base.setSuffix(suffix);
			
			String fullnameWithAbsolute = ImagePathUtil.buildFullname(absolutePath, filename, suffix);
			String fullnameWithRelative = ImagePathUtil.buildFullname(relativePath, filename, suffix);
			String accessUrl = ImagePathUtil.buildAccessUrl(accessUrlMapping,fullnameWithRelative);
			base.setAccessUrl(accessUrl);//不含域名
			
			try {
				File destination = new File(fullnameWithAbsolute);
				LOGGER.debug(absolutePath);
				ImagePathUtil.persist2Disk(absolutePath, destination, file.getInputStream());
				base.setFile(destination);
			} catch (IOException e) {
				LOGGER.error("!!!!! file.getInputStream, IOException,{} ", e);
			}
		}
		return ref;
	}
	
	public static File persist2Disk(String absolutePath, File destination, MultipartFile file) {
		try {
			//File destination = new File(fullnameWithAbsolute);
			LOGGER.debug(absolutePath);
			ImagePathUtil.persist2Disk(absolutePath, destination, file.getInputStream());
			//base.setFile(destination);
		} catch (IOException e) {
			LOGGER.error("!!!!! file.getInputStream, IOException,{} ", e);
		}
		return destination;
	}
	
	public static File persist2Disk(String absolutePath, File destination, InputStream source) {
		try {
			FileUtils.forceMkdir(new File(absolutePath));
			org.apache.commons.io.FileUtils.copyInputStreamToFile(source, destination);
		} catch (IOException e) {
			LOGGER.error("!!!!! persist2Disk, IOException,{} ", e);
		}
		return destination;
	}
	
	public static ImageRef fillRefThenGenerate(ImageExtend extend, ImageRef ref, String basePath, String basePathFolder, String accessUrlMapping) {
		ref = fillImageRef(extend, ref, basePath, basePathFolder, accessUrlMapping);
		Imagebase base = ref.getBase();
		Integer type = ref.getType();
		if(ImageRef.TYPE_IMG.getValueInt().equals(type)) {
			Integer strategy = 1;
			if (null != base) {
				Integer stemp = base.getStrategy();
				if (stemp != null) {
					strategy = stemp;
				}
				base.setDomain(extend.getDomain());
			}
			List<ImageStrategy> imageStrategyList = ImageStrategy.STRATEGY_1.get(strategy);
			Integer expectedW = ref.getExpectedW();
			Integer viewUrlW = imageStrategyList.get(0).getWidth();
			ref = generateImage(ref, imageStrategyList, viewUrlW, expectedW);
		}
		return ref;
	}
	
	public static ImageRef generateImage( ImageRef ref) {
		
		Imagebase base = ref.getBase();
		Integer strategy = 1;
		if (null != base) {
			Integer stemp = base.getStrategy();
			if (stemp != null) {
				strategy = stemp;
			}
		}
		List<ImageStrategy> imageStrategyList = ImageStrategy.STRATEGY_1.get(strategy);
		Integer expectedW = ref.getExpectedW();
		Integer viewUrlW = imageStrategyList.get(0).getWidth();
		ref = generateImage(ref, imageStrategyList, viewUrlW, expectedW);
		return ref;
	}

	public static ImageRef generateImage(ImageRef imageRef, List<ImageStrategy> imageStrategyList, Integer viewUrlW, Integer viewDefaultW) {
		if (null != imageRef) {
			List<ImageRef> imageRefs = new ArrayList<>();
			imageRefs.add(imageRef);
			imageRefs=generateImage(imageRefs, imageStrategyList, viewUrlW, viewDefaultW);
			return imageRefs.get(0);
		}
		return null;
	}
	
	public static List<ImageRef> generateImage(List<ImageRef> imageRefs, List<ImageStrategy> imageStrategyList, Integer viewUrlW,
			Integer viewDefaultW) {

		if (null == imageStrategyList) {
			imageStrategyList = ImageStrategy.STRATEGY_1_LIST;
		}

		if (viewUrlW == null) {
			viewUrlW = ImageStrategy.W_720;
		}

		if (viewDefaultW == null) {
			viewDefaultW = ImageStrategy.W_720;
		}

		int length = imageStrategyList.size();
		int imagesNum = imageRefs.size();
		int cdownSize = imagesNum;
		final CountDownLatch countDownLatch = new CountDownLatch(cdownSize);// 定义countDownLatch
		LOGGER.debug("----------cdownSize --------------- {} ", cdownSize);
		for (ImageRef ref : imageRefs) {
			Imagebase image = ref.getBase();
			File source = image.getFile();
			if (null != source) {
				String absolute = image.getAbsolute();
				String viewAbsolute = PhotoUtil.generateViewPath(absolute);
				ref.setType(ImageRef.TYPE_IMG.getValueInt());

				String filename = image.getFilename();
				String suffix = image.getSuffix();
				if (StringUtils.isBlank(suffix)) {
					suffix = "jpg";
				}

				BufferedImage src = null;
				try {
					FileUtils.forceMkdir(new File(viewAbsolute));
					src = ImageIO.read(source);
					image.setWidth(src.getWidth());
					image.setHeight(src.getHeight());
				} catch (IOException e) {
					LOGGER.error("IOException:", e);
				}
				// ImageRef ref = image.getImageRef();
				Integer wanted = PhotoUtil.getWantedWidth(ref.getExpectedW(), image.getWidth(), imageStrategyList);

				int ltcount = 0;
				for (int i = length; i > 0; i--) {
					ImageStrategy strategy = imageStrategyList.get(i - 1);
					StringBuilder sb = new StringBuilder();
					ImageBase imageBase = new ImageBase();

					imageBase.setFormatName(suffix);

					int width = strategy.getWidth();
					LOGGER.debug(">>>>>filename:{}   ----  width : {}  vs  image.width: {} >>>>>. ", filename, width, image.getWidth());
					if (width < image.getWidth()) {
						sb.append(viewAbsolute).append(filename).append("_").append(width).append(".").append(suffix);
						File dest = new File(sb.toString());
						Integer calHeight = strategy.getHeight();
						if (image.getWidth() < width) {
							calHeight = null;
						}
						final Integer maxHeight = calHeight;
						executor.execute(new Runnable() {
							@Override
							public void run() {
								ImageUtils.compressImage(dest, source, strategy.getQuality(), strategy.getWidth(), maxHeight, imageBase);
								LOGGER.debug("--------in compress wanted: {} vs {} width ,thread : {} ----- ", wanted, width, Thread.currentThread().getName());

								if (wanted.equals(width)) {
									LOGGER.debug("--------countDown in compress ------{} ", countDownLatch.getCount());
									countDownLatch.countDown();
								}
							}
						});
					} else {
						LOGGER.debug("--------countDown else ------{} ", countDownLatch.getCount());
						//if (width == viewDefaultW) {
							// 控制宽度，若图片小于此宽度，直接复制一个源图并以此宽度命名
							sb.append(viewAbsolute).append(filename).append("_").append(width).append(".").append(suffix);
							File dest = new File(sb.toString());
							try {
								FileUtil.copy(source, dest);
							} catch (IOException e) {
								LOGGER.error("IOException, {} ", e);
							}
						//}
						countDownLatch.countDown();
					}
				}
				String viewUrl = PhotoUtil.getUrl(image.getAccessUrl(), viewUrlW, image.getWidth(), imageStrategyList);
				image.setViewUrl(viewUrl);

				String expectedUrl = PhotoUtil.getUrl(image.getAccessUrl(), wanted);
				ref.setExpectedUrl(expectedUrl);
				ref.setWidth(image.getWidth());
				ref.setHeight(image.getHeight());
				ref.setViewUrl(viewUrl);
				image.setMainRefNum(1);// 初始化，计数设置成1
				image.setSubRefNum(1);// 初始化，计数设置成1
			}else {
				ref.setType(ImageRef.TYPE_TEXT.getValueInt());
				countDownLatch.countDown();
			}
		}
		try {
			long t1 = System.currentTimeMillis();
			LOGGER.debug(">>>> awaint begin, t1={} >>>>>", t1);
			countDownLatch.await(20,TimeUnit.SECONDS);
			long t2 = System.currentTimeMillis();
			LOGGER.debug(">>>> awaint end t2={} >>>>", t2);
			LOGGER.debug(">>>> awaint t2-t1= {}  ms ", t2 - t1);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ", e);
		}

		return imageRefs;
	}
	
	
	public static void deleteImage(String absolutePath) {
		if(StringUtils.isNotBlank(absolutePath)) {
			File directory= new File(absolutePath);
			try {
			if(absolutePath.contains(Imagebase.ACCESS_TYPE_ORIGINAL)) {
				String viewPath = PhotoUtil.generateViewPath(absolutePath);
				File viewDirectory= new File(viewPath);
				FileUtils.deleteDirectory(viewDirectory);
			}else if(absolutePath.contains(Imagebase.ACCESS_TYPE_VIEW)){
				String oriPath = PhotoUtil.generateOriPath(absolutePath);
				File oriDirectory= new File(oriPath);
				FileUtils.deleteDirectory(oriDirectory);
			}
				FileUtils.deleteDirectory(directory);
			} catch (IOException e) {
				LOGGER.error("deleteImage error, {}",e);
			}
		}
	}
}
