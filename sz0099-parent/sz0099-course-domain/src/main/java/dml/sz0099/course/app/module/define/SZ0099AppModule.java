/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.module.define;

import org.jit4j.app.module.define.AppModule;
import org.jit4j.app.module.define.Jit4jAppModule;
import org.jit4j.app.persist.entity.module.MappingContext;
import org.jit4j.app.persist.entity.module.MappingSub;
import org.jit4j.app.persist.entity.module.ModuleContext;

/**
 * <pre>
 * @formatter:off
 *
 * 模块常量定义
 * 
 * DEVELEPER_ID： 开发者id，申请成为开发者时所确定的唯一id(该id伴随您的整个应用，不能改变)
 * 
 * 声明一个模块，并初始化映射路径与目录配置
 * 1. 初始化模块：ModuleContext APP_XXX = initModule(...);
 * 2. 定义主目录：MappingContext APP_XXX_PATH = APP_XXX.initMapping(...);
 * 3. 定义子目录：MappingSub APP_XXX_PATH_AAA = APP_AAA_PATH.initMappingSub(...);
 * 			  MappingSub APP_XXX_PATH_BBB = APP_BBB_PATH.initMappingSub(...);
 *  定义二级子目录：
 * 			  MappingSub APP_XXX_PATH_AAA_YYY = APP_XXX_PATH_AAA.initMappingSub(...)
 * 			  MappingSub APP_XXX_PATH_AAA_ZZZ = APP_XXX_PATH_AAA.initMappingSub(...)
 * 
 * 一个模块对应一个主目录
 * 一个主目录下可以多个子目录，每个子目录最多分三个层级
 * 也即：一个模块下可以包含多个子模块，每个子模块均有各自的目录配置
 * 
 * @author bruce yang at 2018-08-26 10:23:24
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-26	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class SZ0099AppModule extends Jit4jAppModule {

	private static final long serialVersionUID = -6269549617956878498L;

	public static final String DEVELOPER_ID_SZ0099 = "sz0099";// 开发者唯一编号
	public static final String DEVELOPER_ID_SZ0099_LOWER = DEVELOPER_ID_SZ0099.toLowerCase();// 开发者唯一编号(小写)

	
/** ----------------------- APP_SZ0099 begin --------------------------- **/
	
	// 1.创建模块: SZ0099 开发者专属
	public static final ModuleContext APP_SZ0099 = AppModule.initModule(
			ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
			"sz0099", "sz0099", "sz0099", "APP_SZ0099", "sz0099Topic",
			"dml.sz0099.app.biz.delegate.main.SZ0099Provider", 10000l);
	
	// 2.定义主目录及模块的mapping:  APP_SZ0099_PATH
	public static final MappingContext APP_SZ0099_PATH = APP_SZ0099.initMapping(APP_SZ0099.getAd(), APP_SZ0099.getAd(),"APP_SZ0099_PATH", 10001l);

	// 3.定义子目录: APP_SZ0099_PATH_BASIC,子目录的key必须唯一
	public static final MappingSub APP_SZ0099_PATH_BASIC = APP_SZ0099_PATH.initMappingSub("basic", "basic","APP_SZ0099_PATH_BASIC", 10020l);
	
	
	/** ----------------------- APP_SZ0099 end --------------------------- **/
	
	
		// 1.创建模块: Demo
		public static final ModuleContext APP_SZ0099_DEMO = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"SZ0099模板模块", "demo", "demo",  "APP_DEMO", "sz0099DemoTopic",
				"dml.sz0099.app.biz.delegate.main.template.DemoProvider", 10100l);
		
		// 2.定义主目录及模块的mapping:  DEMO_PATH
		public static final MappingContext APP_SZ0099_DEMO_PATH = APP_SZ0099_DEMO.initMapping(APP_SZ0099_DEMO.getAd(), APP_SZ0099_DEMO.getAd(),"APP_SZ0099_DEMO_PATH", 10101l);
		
		// 3.定义子目录: APP_SZ0099_DEMO_PATH_SUB, key一定要改掉
		public static final MappingSub APP_SZ0099_DEMO_PATH_SUB = APP_SZ0099_DEMO_PATH.initMappingSub("sub", "sub","APP_SZ0099_DEMO_PATH_SUB", 10120l);
		
		
		
		public static final ModuleContext APP_SZ0099_MEDIA = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"SZ0099多媒体模块", "media", "media",  "APP_MEDIA", "sz0099MediaTopic",
				"dml.sz0099.app.biz.delegate.main.media.MediaProvider", 10300l);
		
		public static final MappingContext APP_SZ0099_MEDIA_PATH = APP_SZ0099_MEDIA.initMapping(APP_SZ0099_MEDIA.getAd(), APP_SZ0099_MEDIA.getAd(),"APP_SZ0099_MEDIA_PATH", 10301l);
		
		public static final MappingSub APP_SZ0099_MEDIA_PATH_SUB = APP_SZ0099_MEDIA_PATH.initMappingSub("sub", "sub","APP_SZ0099_MEDIA_PATH_SUB", 10320l);
		
		
		
		// 1.创建模块: COURSE_CATEGORY
		public static final ModuleContext APP_COURSE_CATEGORY = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"SZ0099类别模块", "ood/category", "ood/category", "APP_COURSE_CATEGORY", "sz0099CourseProdTopic",
				"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 10400l);
		
		// 2.定义主目录及模块的mapping:  COURSE_CATEGORY_PATH
		public static final MappingContext APP_COURSE_CATEGORY_PATH = APP_COURSE_CATEGORY.initMapping(APP_COURSE_CATEGORY.getAd(), APP_COURSE_CATEGORY.getAd(),"APP_COURSE_CATEGORY_PATH", 10401l);

		// 3.定义子目录: APP_COURSE_PRODUCT_PATH_SITE,子目录的key必须唯一
		public static final MappingSub APP_COURSE_CATEGORY_PATH_BASIC = APP_COURSE_CATEGORY_PATH.initMappingSub("basic", "basic","APP_COURSE_CATEGORY_PATH_BASIC", 10420l);
		
		
		// 1.创建模块: APP_COURSE_POSITION
		public static final ModuleContext APP_SZ0099_POSITION = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"位置模块", "ood/position", "ood/position", "APP_SZ0099_POSITION", "sz0099CourseProdTopic",
				"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 10500l);
		
		// 2.定义主目录及模块的mapping:  APP_COURSE_ARTICLE_PATH
		public static final MappingContext APP_SZ0099_POSITION_PATH = APP_SZ0099_POSITION.initMapping(APP_SZ0099_POSITION.getAd(), APP_SZ0099_POSITION.getAd(), "APP_SZ0099_POSITION_PATH", 10501l);

		// 3.定义子目录: APP_COURSE_ARTICLE_PATH_SITE,子目录的key必须唯一
		public static final MappingSub APP_SZ0099_POSITION_PATH_BASIC = APP_SZ0099_POSITION_PATH.initMappingSub("basic", "basic","APP_SZ0099_POSITION_PATH_BASIC", 10520l);

		
}
