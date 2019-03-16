/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.jit8j.core.util.FileUtils;
import org.jit8j.core.util.GsonBuilderUtils;
import org.jit8j.core.util.ImageUtils;
import org.jit8j.core.util.image.ImageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.gecko.core.util.StringUtils;

import dml.sz0099.course.app.client.wrapper.media.ImageExtendLogWrapper;
import dml.sz0099.course.app.client.wrapper.media.ImageExtendWrapper;
import dml.sz0099.course.app.client.wrapper.media.ImageRefWrapper;
import dml.sz0099.course.app.module.define.CourseRespCode;
import dml.sz0099.course.app.persist.data.ImageStrategy;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import jodd.io.FileUtil;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-22 20:18:10
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-22	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ImageProccessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageProccessor.class);


	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


	@Autowired
	private ImageRequestResolver imageRequestResolver;
	
	@Autowired
	private ImageExtendWrapper imageExtendWrapper;
	
	@Autowired
	private ImageExtendLogWrapper imageExtendLogWrapper;
	
	@Autowired
	private ImageRefWrapper imageRefWrapper;
	
	private Map<Long, ImageAdaptor> adaptorContainer;
	
	public ImageResponse proccessSingle(ImageRequest request) {
		// 1.解析
		ImageExtend imageExtend = resolveImageRequest(request);
		ImageResponse response = new ImageResponse();
		if(null != imageExtend && ImageExtend.SUCCESS_YES==imageExtend.getSuccess()) {
			// 2.生成
			List<Imagebase> images = imageExtend.getImages();
			for(Imagebase image: images) {
				Integer strategy = image.getStrategy();
				List<ImageStrategy> imageStrategyList = ImageStrategy.STRATEGY_1.get(strategy);
				if(null != imageStrategyList && !imageStrategyList.isEmpty()) {
					imageExtend = generateImage(imageExtend, imageStrategyList, imageStrategyList.get(0).getWidth(), imageStrategyList.get(0).getWidth());
				}
				
				ImageRef ref = image.getImageRef();
				if(null != ref) {
					ref.setDeleted(true);
				}
			}
			
			//3.保存db记录
			imageExtend=imageExtendWrapper.saveForUpload(imageExtend);
			imageExtend.setSuccess(ParagProduct.SUCCESS_YES);
			imageExtend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_UPLOAD_SUCCESS);
			imageExtend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_UPLOAD_SUCCESS);
			
			adaptorProcess(imageExtend, response);
		}
		response.setSuccess(imageExtend.getSuccess());
		response.setRespCode(imageExtend.getRespCode());
		response.setRespMsg(imageExtend.getRespMsg());
		return response;
	}

	public ImageResponse proccess(ImageRequest request) {
		
		//request.get
		
		// 1.解析
		ImageExtend imageExtend = resolveImageRequest(request);
		ImageResponse response = new ImageResponse();
		if(null != imageExtend && ImageExtend.SUCCESS_YES==imageExtend.getSuccess()) {
			// 2.生成
			imageExtend = generateImage(imageExtend, ImageStrategy.STRATEGY_1_LIST, ImageStrategy.W_720, ImageStrategy.W_720);
			//3.保存db记录
			imageExtend=imageExtendWrapper.saveForUpload(imageExtend);
			imageExtend.setSuccess(ParagProduct.SUCCESS_YES);
			imageExtend.setRespCode(CourseRespCode.SZ0099_CODE_COURSE_MEDIA_UPLOAD_SUCCESS);
			imageExtend.setRespMsg(CourseRespCode.SZ0099_MSG_COURSE_MEDIA_UPLOAD_SUCCESS);
	
			adaptorProcess(imageExtend, response);
			
		}
		response.setSuccess(imageExtend.getSuccess());
		response.setRespCode(imageExtend.getRespCode());
		response.setRespMsg(imageExtend.getRespMsg());
		return response;
	}

	/**
	 * @param imageExtend
	 * @param response
	 */
	private void adaptorProcess(ImageExtend imageExtend, ImageResponse response) {
		// 4.转化
		//每个positionId就对应着一个ImageAdaptor,配置在xml中
		Long positionId = imageExtend.getPositionId();
		ImageAdaptor imageAdaptor = adaptorContainer.get(positionId);
		boolean result = false;
		if(null != imageAdaptor) {
			Object obj = imageAdaptor.convert(imageExtend);
			// 5.调用
			result = imageAdaptor.persist(obj);
			if(!result) {
				final ImageExtend log = imageExtend;
				executor.execute(new Runnable() {
					@Override
					public void run() {
						//记录未执行成功的日志
						imageExtendLogWrapper.persistForFail(log);
					}
				});
			}
		}
		//记录调用日志，更新引用计数 TODO
		

		// 6.返回
		List<Imagebase> images = imageExtend.getImages();
		List<ImageRef> refList = new ArrayList<>(images.size());
		for(Imagebase image : images) {
			refList.add(image.getImageRef());
		}
		response.setContent(refList);
	}

	public ImageExtend resolveImageRequest(ImageRequest request) {

		ImageExtend imageExtend = imageRequestResolver.resolveImageRequest(request);
		return imageExtend;
	}
	public ImageExtend resolveImageRequestForDelete(ImageRequest request) {
		ImageExtend imageExtend = imageRequestResolver.resolveImageRequestForDelete(request);
		return imageExtend;
	}

	public ImageExtend generateImage(ImageExtend imageExtend ,List<ImageStrategy> imageStrategyList , Integer viewUrlW , Integer viewDefaultW) {

		List<Imagebase> images = imageExtend.getImages();
		if(null == imageStrategyList) {
			imageStrategyList = ImageStrategy.STRATEGY_1_LIST;
		}
		
		if(viewUrlW==null) {
			viewUrlW = ImageStrategy.W_720;
		}
		
		if(viewDefaultW==null) {
			viewDefaultW = ImageStrategy.W_720;
		}
		
		int length = imageStrategyList.size();
		int imagesNum = images.size();
		int cdownSize = imagesNum;
		final CountDownLatch countDownLatch = new CountDownLatch(cdownSize);//定义countDownLatch
		LOGGER.debug("----------cdownSize --------------- {} ",cdownSize);
		for (Imagebase image : images) {
			File source = image.getFile();
			String absolute = image.getAbsolute();
			String viewAbsolute = PhotoUtil.generateViewPath(absolute);

			String filename = image.getFilename();
			String suffix = image.getSuffix();
			if(StringUtils.isBlank(suffix)) {
				suffix="jpg";
			}
			
			BufferedImage src = null;
			try {
				FileUtils.forceMkdir(new File(viewAbsolute));
				src = ImageIO.read(source);
				image.setWidth(src.getWidth());
				image.setHeight(src.getHeight());
			} catch (IOException e) {
				LOGGER.error("IOException:",e);
			}
			
			ImageRef ref = image.getImageRef();
			Integer wanted = PhotoUtil.getWantedWidth(ref.getExpectedW(), image.getWidth(), imageStrategyList);
			
			int ltcount=0;
			for (int i=length;i>0;i--) {
				ImageStrategy strategy  = imageStrategyList.get(i-1);
				StringBuilder sb = new StringBuilder();
				ImageBase imageBase = new ImageBase();
				
				imageBase.setFormatName(suffix);
				
				int width = strategy.getWidth();
				LOGGER.debug(">>>>>filename:{}   ----  width : {}  vs  image.width: {} >>>>>. ",filename, width,image.getWidth());
				if(width<image.getWidth()) {
					sb.append(viewAbsolute).append(filename).append("_").append(width).append(".").append(suffix);
					File dest = new File(sb.toString());
					Integer calHeight = strategy.getHeight();
					if(image.getWidth()<width) {
						calHeight = null;
					}
					final Integer maxHeight= calHeight;
					executor.execute(new Runnable() {
						@Override
						public void run() {
							ImageUtils.compressImage(dest, source, strategy.getQuality(), strategy.getWidth(), maxHeight, imageBase);
							LOGGER.debug("--------in compress wanted: {} vs {} width ,thread : {} ----- ", wanted, width, Thread.currentThread().getName());
							
							if(wanted.equals(width)) {
								LOGGER.debug("--------countDown in compress ------{} ", countDownLatch.getCount());
								countDownLatch.countDown();
							}
						}
					});
					//ImageUtils.generateImage(dest, source, strategy.getQuality(), strategy.getWidth(), strategy.getHeight(), imageBase);
				}else {
					LOGGER.debug("--------countDown else ------{} ", countDownLatch.getCount());
					if(width == viewDefaultW) {
						//控制宽度，若图片小于此宽度，直接复制一个源图并以此宽度命名
						sb.append(viewAbsolute).append(filename).append("_").append(width).append(".").append(suffix);
						File dest = new File(sb.toString());
						try {
							FileUtil.copy(source, dest);
						} catch (IOException e) {
							LOGGER.error("IOException, {} ",e);
						}
					}
					countDownLatch.countDown();
				}
			}
			String viewUrl = PhotoUtil.getUrl(image.getAccessUrl(), viewUrlW, image.getWidth(), imageStrategyList);
			image.setViewUrl(viewUrl);
			
			String expectedUrl = PhotoUtil.getUrl(image.getAccessUrl(),wanted);
			ref.setExpectedUrl(expectedUrl);
			ref.setWidth(image.getWidth());
			ref.setHeight(image.getHeight());
			ref.setViewUrl(viewUrl);
			image.setMainRefNum(1);//初始化，计数设置成1
			image.setSubRefNum(1);//初始化，计数设置成1
		}
		try {
			long t1=System.currentTimeMillis();
			LOGGER.debug(">>>> awaint begin, t1={} >>>>>",t1);
			countDownLatch.await();
			long t2=System.currentTimeMillis();
			LOGGER.debug(">>>> awaint end t2={} >>>>",t2);
			LOGGER.debug(">>>> awaint t2-t1= {}  ms ",t2-t1);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}

		return imageExtend;
	}
	
	public ImageRef mergeImageRef(ImageRef imageRef) {
		Long id = imageRef.getId();
		Long positionId = imageRefWrapper.findPositionId(id);
		ImageRef entity = imageRefWrapper.mergeForTitle(imageRef);
		
		if(ImageRef.SUCCESS_YES==entity.getSuccess()) {
			ImageAdaptor imageAdaptor = adaptorContainer.get(positionId);
			boolean result = imageAdaptor.mergeImage(entity);
			if(!result) {
				//TODO log it
				LOGGER.error("!!!imageAdaptor.mergeImage , {} ",imageAdaptor.getClass().getName());
				LOGGER.error("!!!imageAdaptor.mergeImage ,data: {} ",GsonBuilderUtils.toJson(entity));
			}
		}
		
		return entity;
	}
	
	/**
	 * 删除单张图片
	 * @param imageRef
	 * @return
	 */
	public ImageRef deleteImageRef(ImageRef imageRef) {
		Long id = imageRef.getId();
		Long positionId = imageRefWrapper.findPositionId(id);
		ImageRef entity = imageRefWrapper.findById(id);
		if(null != positionId) {
			ImageAdaptor imageAdaptor = adaptorContainer.get(positionId);
			imageRef.setSubId(entity.getSubId());
			imageRef.setMainId(entity.getMainId());
			boolean result = imageAdaptor.deleteImage(imageRef);
			
			if(!result) {
				//TODO log it
				LOGGER.error("!!!imageAdaptor.mergeImage , {} ",imageAdaptor.getClass().getName());
				LOGGER.error("!!!imageAdaptor.mergeImage ,data: {} ",GsonBuilderUtils.toJson(entity));
			}
			imageRefWrapper.deleteById(imageRef);
			entity.setSuccess(ImageRef.SUCCESS_YES);
		}
		
		return entity;
	}
	
	public boolean deleteByRequest(ImageRequest request) {
		ImageExtend imageExtend  = resolveImageRequestForDelete(request);
		if(ImageExtend.SUCCESS_YES==imageExtend.getSuccess()) {
			
			Long mainId = request.getMainId();
			Long subId = request.getSubId();
			Long extendId = imageExtend.getId();
			Long userId = request.getUserId();
			ImageRef imageRef = new ImageRef();
			imageRef.setMainId(mainId);
			imageRef.setSubId(subId);
			imageRef.setExtendId(extendId);
			imageRef.setUserId(userId);
			imageRef.setLastModifiedBy(userId);
			
			if(null != extendId) {
				imageRefWrapper.deleteByMainIdAndSubId(imageRef);
			}
		}
		return true;
	}
	

	public Map<Long, ImageAdaptor> getAdaptorContainer() {
		return adaptorContainer;
	}

	public void setAdaptorContainer(Map<Long, ImageAdaptor> adaptorContainer) {
		this.adaptorContainer = adaptorContainer;
	}

}
