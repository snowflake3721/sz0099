/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.module.define;

import org.jit4j.app.module.define.AppModule;
import org.jit4j.app.persist.entity.module.MappingContext;
import org.jit4j.app.persist.entity.module.MappingSub;
import org.jit4j.app.persist.entity.module.ModuleContext;
import org.jit4j.app.persist.entity.module.MsgContext;
import org.jit4j.app.persist.entity.module.MsgSub;

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

public class CourseAppModule extends SZ0099AppModule {

	private static final long serialVersionUID = -6269549617956878498L;

	
/** ----------------------- COURSE_SYS begin --------------------------- **/
	
	// 1.创建模块: COURSE_ROOT
	public static final ModuleContext APP_COURSE_ROOT = AppModule.initModule(
			ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
			"户外系统模块", "ood", "ood", "APP_COURSE_ROOT", "sz0099CourseTopic",
			"dml.sz0099.app.biz.delegate.main.system.CourseSystemProvider", 15000l);
	
	// 2.定义主目录及模块的mapping:  COURSE_ROOT_PATH
	public static final MappingContext APP_COURSE_ROOT_PATH = APP_COURSE_ROOT.initMapping(APP_COURSE_ROOT.getAd(), APP_COURSE_ROOT.getAd(), "APP_COURSE_ROOT_PATH", 15001l);

	// 3.定义子目录: APP_COURSE_ROOT_PATH,子目录的key必须唯一
	public static final MappingSub APP_COURSE_ROOT_PATH_BASIC = APP_COURSE_ROOT_PATH.initMappingSub("basic", "basic","APP_COURSE_ROOT_PATH_BASIC", 15020l);
	
	
	/** ----------------------- COURSE_SYS end --------------------------- **/
	
	/** ----------------------- COURSE_HOME begin --------------------------- **/
	// 1.创建模块: COURSE_PRODUCT
	public static final ModuleContext APP_COURSE_HOME = AppModule.initModule(
			ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
			"首页模块", "ood/home", "ood/home", "APP_COURSE_HOME", "sz0099CourseProdTopic",
			"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 15100l);
	
	// 2.定义主目录及模块的mapping:  COURSE_PERSONAL_PATH
	public static final MappingContext APP_COURSE_HOME_PATH = APP_COURSE_HOME.initMapping(APP_COURSE_HOME.getAd(), APP_COURSE_HOME.getAd(), "APP_COURSE_HOME_PATH", 15101l);

	// 3.定义子目录: APP_COURSE_PERSONAL_PATH_SITE,子目录的key必须唯一
	public static final MappingSub APP_COURSE_HOME_PATH_BASIC = APP_COURSE_HOME_PATH.initMappingSub("home", "home","APP_COURSE_HOME_PATH_BASIC", 15120l);
	
	// 3.定义子目录: APP_COURSE_PERSONAL_PATH_SITE,子目录的key必须唯一
	public static final MappingSub APP_COURSE_HOME_PATH_ARTICLE = APP_COURSE_HOME_PATH.initMappingSub("article", "article","APP_COURSE_HOME_PATH_ARTICLE", 15121l);
	public static final MappingSub APP_COURSE_HOME_PATH_PROFESSION = APP_COURSE_HOME_PATH.initMappingSub("profession", "profession","APP_COURSE_HOME_PATH_PROFESSION", 15122l);
	public static final MappingSub APP_OOD_HOME_PATH_ACTIVITY = APP_COURSE_HOME_PATH.initMappingSub("activity", "activity","APP_COURSE_HOME_PATH_ACTIVITY", 15123l);
		
	/** ----------------------- COURSE_HOME end --------------------------- **/
	
	/** ----------------------- COURSE_PRODUCT begin --------------------------- **/
	
	// 1.创建模块: COURSE_PRODUCT
	public static final ModuleContext APP_COURSE_PRODUCT = AppModule.initModule(
			ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
			"产品模块", "ood/product", "ood/product", "APP_COURSE_PRODUCT", "sz0099CourseProdTopic",
			"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 15200l);
	
	// 2.定义主目录及模块的mapping:  COURSE_PRODUCT_PATH
	public static final MappingContext APP_COURSE_PRODUCT_PATH = APP_COURSE_PRODUCT.initMapping(APP_COURSE_PRODUCT.getAd(), APP_COURSE_PRODUCT.getAd(), "APP_COURSE_PRODUCT_PATH", 15201l);

	// 3.定义子目录: APP_COURSE_PRODUCT_PATH_SITE,子目录的key必须唯一
	public static final MappingSub APP_COURSE_PRODUCT_PATH_BASIC = APP_COURSE_PRODUCT_PATH.initMappingSub("basic", "basic","APP_COURSE_PRODUCT_PATH_BASIC", 15220l);
	
	
	/** ----------------------- COURSE_PRODUCT end --------------------------- **/
	
	// 1.创建模块: COURSE_PRODUCT
		public static final ModuleContext APP_COURSE_PERSONAL = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"个人模块", "ood/personal", "ood/personal", "APP_COURSE_PERSONAL", "sz0099CourseProdTopic",
				"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 15300l);
		
		// 2.定义主目录及模块的mapping:  COURSE_PERSONAL_PATH
		public static final MappingContext APP_COURSE_PERSONAL_PATH = APP_COURSE_PERSONAL.initMapping(APP_COURSE_PERSONAL.getAd(), APP_COURSE_PERSONAL.getAd(), "APP_COURSE_PERSONAL_PATH", 15301l);

		// 3.定义子目录: APP_COURSE_PERSONAL_PATH_SITE,子目录的key必须唯一
		public static final MappingSub APP_COURSE_PERSONAL_PATH_BASIC = APP_COURSE_PERSONAL_PATH.initMappingSub("basic", "basic","APP_COURSE_PERSONAL_PATH_BASIC", 15320l);
		

		
		
		
		// 1.创建模块: APP_COURSE_ARTICLE
		public static final ModuleContext APP_COURSE_ARTICLE = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"文章模块", "ood/article", "ood/article", "APP_COURSE_ARTICLE", "sz0099CourseProdTopic",
				"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 15400l);
		
		// 2.定义主目录及模块的mapping:  APP_COURSE_ARTICLE_PATH
		public static final MappingContext APP_COURSE_ARTICLE_PATH = APP_COURSE_ARTICLE.initMapping(APP_COURSE_ARTICLE.getAd(), APP_COURSE_ARTICLE.getAd(), "APP_COURSE_ARTICLE_PATH", 15401l);

		// 3.定义子目录: APP_COURSE_ARTICLE_PATH_SITE,子目录的key必须唯一
		public static final MappingSub APP_COURSE_ARTICLE_PATH_BASIC = APP_COURSE_ARTICLE_PATH.initMappingSub("basic", "basic","APP_COURSE_ARTICLE_PATH_BASIC", 15420l);


		
		
		// 1.活动模块: APP_OOD_ACTIVITY
		public static final ModuleContext APP_OOD_ACTIVITY = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"活动模块", "ood/activity", "ood/activity", "APP_OOD_ACTIVITY", "sz0099CourseProdTopic",
				"dml.sz0099.app.biz.delegate.main.product.CourseProductProvider", 15500l);
		
		// 2.定义主目录及模块的mapping:  APP_OOD_ACTIVITY_PATH
		public static final MappingContext APP_OOD_ACTIVITY_PATH = APP_OOD_ACTIVITY.initMapping(APP_OOD_ACTIVITY.getAd(), APP_OOD_ACTIVITY.getAd(), "APP_OOD_ACTIVITY_PATH", 15501l);

		// 3.定义子目录: APP_OOD_ACTIVITY_PATH_SITE,子目录的key必须唯一
		public static final MappingSub APP_OOD_ACTIVITY_PATH_BASIC = APP_OOD_ACTIVITY_PATH.initMappingSub("basic", "basic","APP_OOD_ACTIVITY_PATH_BASIC", 15520l);

		
		// 20.定义主消息及消息名称: APP_AUTH_MSG
		public static final MsgContext APP_OOD_ACTIVITY_MSG = APP_OOD_ACTIVITY.initMsg(APP_OOD_ACTIVITY.getAd(), APP_OOD_ACTIVITY.getName(), "APP_OOD_ACTIVITY_MSG", 15501l);
		public static final MsgSub APP_OOD_ACTIVITY_MSG_ORDER = APP_OOD_ACTIVITY_MSG.initMsgSub("order", "订单","APP_OOD_ACTIVITY_MSG_ORDER", 15530l);
		//消息代码定义
		public static final MsgSub APP_OOD_ACTIVITY_MSG_ORDER_MERGE_STATUS = APP_OOD_ACTIVITY_MSG_ORDER.initChild("merge_order_status", "同步订单状态", "APP_OOD_ACTIVITY_MSG_ORDER_MERGE_STATUS", 15531l);

		
		
		
		
		// 1.定时任务模块: APP_OOD_SCHEDULER
		public static final ModuleContext APP_OOD_SCHEDULER = AppModule.initModule(
				ModuleContext.MODULE_TYPE_MICRO, DEVELOPER_ID_SZ0099,
				"定时任务模块", "ood/scheduler", "ood/scheduler", "APP_OOD_SCHEDULER", "sz0099OodSchedulerTopic",
				"dml.sz0099.course.app.biz.delegate.main.scheduler.OodSchedulerProvider", 15600l);

}
