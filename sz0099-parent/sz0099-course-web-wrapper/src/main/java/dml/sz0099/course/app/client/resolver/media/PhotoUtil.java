/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dml.sz0099.course.app.persist.data.ImageStrategy;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-22 20:31:23
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-22	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class PhotoUtil {
	
	/*public static String getShowUrlForHead(PhotoParag photoParag) {
		String expectedUrl = null;
		if(null != photoParag) {
			expectedUrl = photoParag.getExpectedUrl();
		}
		if(StringUtils.isBlank(expectedUrl)) {
			String url = photoParag.getFullurl();
			if(StringUtils.isNotBlank(url)) {
				Integer width = photoParag.getWidth();
				if(null == width) {
					width=ImageStrategy.W_720;
				}
				expectedUrl = getUrlByDefaultStrategy(url, ImageStrategy.W_300, width);
			}
		}
		return expectedUrl;
	}*/
	
	public static String getShowUrlForAd(String expectedUrl, String fullUrl, Integer width) {
		if(StringUtils.isBlank(expectedUrl)) {
			String url = fullUrl;
			if(StringUtils.isNotBlank(url)) {
				//Integer width = photoParag.getWidth();
				if(null == width) {
					width=ImageStrategy.W_720;
				}
				expectedUrl = getUrlByDefaultStrategy(url, ImageStrategy.W_300, width);
			}
		}
		return expectedUrl;
	}
	
	public static String getShowUrlForAdForParag(PhotoParag photoParag) {
		String expectedUrl = photoParag.getExpectedUrl();
		String fullUrl = photoParag.getFullurl();
		Integer width = photoParag.getWidth();
		return getShowUrlForAd(expectedUrl, fullUrl, width);
	}
	
	public static String getShowUrlForExpected2(String url, int expectedWidth, int width) {
		String expectedUrl = null;
		if(StringUtils.isNotBlank(url)) {
			if(0 == width) {
				width=ImageStrategy.W_720;
			}
			expectedUrl = getUrlByDefaultStrategy(url, expectedWidth, width);
		}
		return expectedUrl;
	}
	
	public static String getShowUrlForExpected(PhotoParag photoParag, int expectedWidth) {
		String expectedUrl = null;
		String url = photoParag.getFullurl();
		if(StringUtils.isNotBlank(url)) {
			Integer width = photoParag.getWidth();
			if(null == width) {
				width=ImageStrategy.W_720;
			}
			expectedUrl = getUrlByDefaultStrategy(url, expectedWidth, width);
		}
		return expectedUrl;
	}
	
	public static String getUrlByDefaultStrategy(String url, int expectedWidth, int actualWidth) {
		return getUrl(url, expectedWidth, actualWidth, ImageStrategy.STRATEGY_1_LIST);
	}
	
	public static String getUrl(String url, int wantedWidth) {
		if (StringUtils.isNotBlank(url)) {
			url = url.replace(Imagebase.ACCESS_TYPE_ORIGINAL, Imagebase.ACCESS_TYPE_VIEW);
			int lastIndex_dot = url.lastIndexOf('.');
			int lastIndex_under = url.lastIndexOf(ImageStrategy.SYMBOL_UNDER_LINE);
			StringBuilder sb = new StringBuilder();
			
			if (lastIndex_dot > -1) {
				if(lastIndex_under > -1) {
					sb.append(url.substring(0, lastIndex_under));
				}else {
					sb.append(url.substring(0, lastIndex_dot));
				}
				sb.append(ImageStrategy.SYMBOL_UNDER_LINE)
				.append(wantedWidth)
				.append(url.substring(lastIndex_dot));
			}
			return sb.toString();
		}
		return url;
	}

	public static String getUrl(String url, int expectedWidth, int actualWidth, List<ImageStrategy> widthList) {
		/*if (StringUtils.isNotBlank(url)) {
			url = url.replace(Imagebase.ACCESS_TYPE_ORIGINAL, Imagebase.ACCESS_TYPE_VIEW);
			

			int lastIndexOf = url.lastIndexOf('.');
			StringBuilder sb = new StringBuilder();
			if (lastIndexOf > -1) {
				sb.append(url.substring(0, lastIndexOf)).append(ImageStrategy.SYMBOL_UNDER_LINE).append(wanted).append(url.substring(lastIndexOf));
			}
			return sb.toString();
		}*/
		int wanted = getWantedWidth(expectedWidth, actualWidth, widthList);
		url = getUrl(url, wanted);
		return url;
	}
	
	
	public static int getWantedWidth(int expectedWidth, int actualWidth, List<ImageStrategy> widthList) {
		int w = searchDownWidth(actualWidth, widthList);
		int wanted = w;

		if (expectedWidth < w) {
			wanted = searchUpWidth(expectedWidth, widthList);
		}
		
		if(expectedWidth>=ImageStrategy.W_720 && wanted<ImageStrategy.W_720) {
			wanted=ImageStrategy.W_720;//源图宽度小于720时，取源图，源图存储时复制了一份为720
		}
		return wanted;
	}
	
	public static String getUrlReplaceWidth(String urlWithWidth, int expectedWidth, int actualWidth, List<ImageStrategy> widthList) {
		if (StringUtils.isNotBlank(urlWithWidth)) {
			urlWithWidth = urlWithWidth.replace(Imagebase.ACCESS_TYPE_ORIGINAL, Imagebase.ACCESS_TYPE_VIEW);
			int wanted=getWantedWidth(expectedWidth, actualWidth, widthList);
			String url = getUrl(urlWithWidth, wanted);
			return url;
		}
		return urlWithWidth;
	}
	
	
	public static String generateViewPath(String originalPath) {
		if (StringUtils.isNotBlank(originalPath)) {
			originalPath = originalPath.replace(Imagebase.ACCESS_TYPE_ORIGINAL, Imagebase.ACCESS_TYPE_VIEW);
		}
		return originalPath;
	}
	
	public static String generateOriPath(String viewPath) {
		if (StringUtils.isNotBlank(viewPath)) {
			viewPath = viewPath.replace(Imagebase.ACCESS_TYPE_VIEW, Imagebase.ACCESS_TYPE_ORIGINAL);
		}
		return viewPath;
	}

	public static int searchDownWidth(int actualWidth, List<ImageStrategy> widthList) {
		if (null != widthList && !widthList.isEmpty()) {
			int size = widthList.size();
			for (int i = size; i > 0; i--) {
				int w = widthList.get(i - 1).getWidth();
				if (actualWidth > w) {
					return w;
				}
			}//若找了整个列表也没有比实际宽度小的，则应返回列表中的最小宽度
			return widthList.get(0).getWidth();
		}
		return actualWidth;
	}

	public static int searchUpWidth(int expectedW, List<ImageStrategy> widthList) {

		if (null != widthList && !widthList.isEmpty()) {
			int size = widthList.size();
			for (int i = 0; i < size; i++) {
				int w = widthList.get(i).getWidth();
				if (expectedW <= w) {
					return w;
				}
			}
			return widthList.get(size - 1).getWidth();
		}
		return 0;
	}
	
	public static String getFullUrl(String domain, String viewurl) {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotBlank(domain)) {
			int index = domain.lastIndexOf(Imagebase.SYMBOL_SLASH);
			if(index==domain.length()-1) {
				sb.deleteCharAt(index);
			}
			sb.append(domain);
		}
		if(StringUtils.isNotBlank(viewurl)) {
			viewurl = viewurl.replace(Imagebase.ACCESS_TYPE_ORIGINAL, Imagebase.ACCESS_TYPE_VIEW);
		}
		sb.append(viewurl);
		return sb.toString();
	}

	public static void main(String[] args) {
		String url = "assert/upload/imori/sz0099/ood/product/productV/paragraph/2018/8/22/1222/4444/232284395829686272/232284395829686272.jpg";
		String ac = getUrl(url, 300, 1440, ImageStrategy.STRATEGY_1_LIST);
		System.out.println(ac);
	}
}
