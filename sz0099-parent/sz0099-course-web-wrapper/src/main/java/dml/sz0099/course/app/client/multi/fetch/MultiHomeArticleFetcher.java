/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.multi.fetch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jit4j.app.module.define.Robot;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.persist.page.PageRequest;
import org.jit8j.core.util.GsonBuilderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import dml.sz0099.course.app.client.controller.home.HomeCoeArticleController;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeArticleConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeArticleFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeArticlePageDto;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-26 22:01:48
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-26	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Component
public class MultiHomeArticleFetcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MultiHomeArticleFetcher.class);
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private CoeArticleConverter coeArticleConverter;
	
	@Autowired
	private CoeArticleFetcher coeArticleFetcher;
	
	@Autowired
	private CoeCategArticleWrapper categoryWrapper;
	
	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	
	/**
	 * 推荐
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleRecommend(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition headPosition = new CoeArticlePosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				LOGGER.debug("----1-fetchHomeArticleRecommend, subId:{} begin----",Position.SUBID_1_INDEX.getValueInt());
				CoeArticlePageDto headDto = coeArticleFetcher.fetchArticleIndexHead(headPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				LOGGER.debug("----2-fetchHomeArticleRecommend, subId:{} end----",Position.SUBID_1_INDEX.getValueInt());
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticle coeArticle = new CoeArticle();
				coeArticle.setRecommend(CoeArticle.RECOMMEND_YES.getValueInt());
				Pageable pageable = new PageRequest(0,20, Direction.DESC, "topLevel","refreshTime");
				Page<CoeArticle> recommendPage = coeArticleWrapper.findPageForRecommend(coeArticle, pageable);;
				model.addAttribute("recommendPage", recommendPage);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		
		Long userId=user.getId();
		CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
		model.addAttribute("currentUser", coeUser);
		
		//4.当前登录用户最新文章标签
		Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
		model.addAttribute("tagPage", tagPage);
		
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> recommend await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> recommend await end tb={} >>>>",tb);
			LOGGER.debug(">>>> recommend await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	/**
	 * 群侠旗帜
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleFlag(User user, final Model model) {
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition actionFlagPosition = new CoeArticlePosition();
				actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto actionFlagDto = coeArticleFetcher.fetchActionFlag(actionFlagPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				model.addAttribute("actionFlagDto", actionFlagDto);
				long t8=System.currentTimeMillis();
				LOGGER.debug("t8-t1= {} ms",t8-t1);
				countDownLatch.countDown();
			}
		});
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_FLAG);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> flagPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("flagPage", flagPage);
				model.addAttribute("entity", coeCategArticle);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t9-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> flag await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> flag await end tb={} >>>>",tb);
			LOGGER.debug(">>>> flag await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	
	
	/**
	 * 徒步
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleFoot(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition footPostion = new CoeArticlePosition();
				footPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				footPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto footDto = coeArticleFetcher.fetchIndexFoot(footPostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("footDto", footDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_SHARED_FOOT);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> footPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("footPage", footPage);
				model.addAttribute("entity", coeCategArticle);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> foot await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> foot await end tb={} >>>>",tb);
			LOGGER.debug(">>>> foot await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	/**
	 * 旅行
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleTravel(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition travelPostion = new CoeArticlePosition();
				travelPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				travelPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto travelDto = coeArticleFetcher.fetchIndexTravel(travelPostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("travelDto", travelDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_SHARED_TRAVEL);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> travelPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("travelPage", travelPage);
				model.addAttribute("entity", coeCategArticle);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> travelPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> travelPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> travelPage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	/**
	 * 旅行
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleBike(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition bikePostion = new CoeArticlePosition();
				bikePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				bikePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto bikeDto = coeArticleFetcher.fetchIndexBike(bikePostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("bikeDto", bikeDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_SHARED_BIKE);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> bikePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("bikePage", bikePage);
				model.addAttribute("entity", coeCategArticle);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> travelPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> travelPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> travelPage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	/**
	 * 线路
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleRoadline(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition roadLinePostion = new CoeArticlePosition();
				roadLinePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				roadLinePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto roadlineDto = coeArticleFetcher.fetchIndexRoadLine(roadLinePostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("roadlineDto", roadlineDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ROADLINE);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> roadlinePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("roadlinePage", roadlinePage);
				model.addAttribute("entity", coeCategArticle);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> roadlinePage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> roadlinePage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> roadlinePage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	
	/**
	 * 美食
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleEat(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition roadLinePostion = new CoeArticlePosition();
				roadLinePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				roadLinePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto eatDto = coeArticleFetcher.fetchIndexEat(roadLinePostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("eatDto", eatDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_EAT);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> eatPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("eatPage", eatPage);
				model.addAttribute("entity", coeCategArticle);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> eatPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> eatPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> eatPage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	
	/**
	 * 公益
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleWelfare(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition welfarePostion = new CoeArticlePosition();
				welfarePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				welfarePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto welfareDto = coeArticleFetcher.fetchIndexWelfare(welfarePostion, CoeArticleFetcher.STRATEGY_DEFAULT);

				model.addAttribute("welfareDto", welfareDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_SHARED_WELFARE);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> welfarePage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("welfarePage", welfarePage);
				model.addAttribute("entity", coeCategArticle);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> welfare await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> welfare await end tb={} >>>>",tb);
			LOGGER.debug(">>>> welfare await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	

	/**
	 * 装备
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeArticleEquip(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition equipRealPosition = new CoeArticlePosition();
				equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto equipRealDto = coeArticleFetcher.fetchEquipReal(equipRealPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("equipRealPosition", equipRealPosition);
				model.addAttribute("equipRealDto", equipRealDto);
				long t7=System.currentTimeMillis();
				LOGGER.debug("t7-t1= {} ms",t7-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategArticle coeCategArticle = new CoeCategArticle();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_EQUIP);
				coeCategArticle.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "article.topLevel","article.refreshTime");
				Page<CoeCategArticle> equipPage = categoryWrapper.findPageForPublishWithChildren(coeCategArticle, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("equipPage", equipPage);
				model.addAttribute("entity", coeCategArticle);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> equip await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> equip await end tb={} >>>>",tb);
			LOGGER.debug(">>>> equip await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	
	
	public Model fetchHomeArticleIndex(User user, final Model model) {
		
		long t1=System.currentTimeMillis();
		final CountDownLatch countDownLatch = new CountDownLatch(8);//定义countDownLatch
		
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition headPosition = new CoeArticlePosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto headDto = coeArticleFetcher.fetchArticleIndexHead(headPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("headPosition", headPosition);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition explorerPosition = new CoeArticlePosition();
				explorerPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				explorerPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto explorerDto = coeArticleFetcher.fetchExplorer(explorerPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("explorerPosition", explorerPosition);
				model.addAttribute("explorerDto", explorerDto);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		//3.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition rememberPosition = new CoeArticlePosition();
				rememberPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				rememberPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto rememberDto = coeArticleFetcher.fetchSpecialRemember(rememberPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("rememberPosition", rememberPosition);
				model.addAttribute("rememberDto", rememberDto);
				long t4=System.currentTimeMillis();
				LOGGER.debug("t4-t1= {} ms",t4-t1);
				countDownLatch.countDown();
			}
		});
		//4.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition farmstayPosition = new CoeArticlePosition();
				farmstayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				farmstayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto farmstayDto = coeArticleFetcher.fetchEatFarmstay(farmstayPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("farmstayPosition", farmstayPosition);
				model.addAttribute("farmstayDto", farmstayDto);
				long t5=System.currentTimeMillis();
				LOGGER.debug("t5-t1= {} ms",t5-t1);
				countDownLatch.countDown();
			}
		});
		
		//5.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition outdoorPosition = new CoeArticlePosition();
				outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto outdoorDto = coeArticleFetcher.fetchEatOutdoor(outdoorPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("outdoorPosition", outdoorPosition);
				model.addAttribute("outdoorDto", outdoorDto);
				long t6=System.currentTimeMillis();
				LOGGER.debug("t6-t1= {} ms",t6-t1);
				countDownLatch.countDown();
			}
		});
		//6.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition equipRealPosition = new CoeArticlePosition();
				equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto equipRealDto = coeArticleFetcher.fetchEquipReal(equipRealPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("equipRealPosition", equipRealPosition);
				model.addAttribute("equipRealDto", equipRealDto);
				long t7=System.currentTimeMillis();
				LOGGER.debug("t7-t1= {} ms",t7-t1);
				countDownLatch.countDown();
			}
		});
		//7.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeArticlePosition actionFlagPosition = new CoeArticlePosition();
				actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeArticlePageDto actionFlagDto = coeArticleFetcher.fetchActionFlag(actionFlagPosition, CoeArticleFetcher.STRATEGY_DEFAULT);
				//model.addAttribute("actionFlagPosition", actionFlagPosition);
				model.addAttribute("actionFlagDto", actionFlagDto);
				long t8=System.currentTimeMillis();
				LOGGER.debug("t8-t1= {} ms",t8-t1);
				countDownLatch.countDown();
			}
		});
		
		//8>>>2.查找分类树
		executor.execute(new Runnable() {
			@Override
			public void run() {
				Long mainId = null;
				Long subId = null;
				Category categoryTree = coeArticleWrapper.findCategoryTree(mainId, subId);
				model.addAttribute("categoryTree", categoryTree);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t9-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		//9.
		Long userId=user.getId();
		/*executor.execute(new Runnable() {
			@Override
			public void run() {
				//3.邀请人前三篇文章列表
				
				Long createdBy = user.getCreatedBy();
				Page<CoeArticle> invitorPage = coeArticleWrapper.findPageForInvitor(createdBy,userId);
				model.addAttribute("invitorPage", invitorPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<CoeArticle> contentList = invitorPage.getContent();
				CircleMain circleMain = coeArticleConverter.convert(contentList,coeUser);
				model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
				
				long t10=System.currentTimeMillis();
				LOGGER.debug("t10-t1= {} ms",t10-t1);
				countDownLatch.countDown();
			}
		});*/
		
		CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
		model.addAttribute("currentUser", coeUser);
		
		//4.当前登录用户最新文章标签
		Page<CoeArticle>  tagPage = coeArticleWrapper.findPageTagForCurrentUser(userId);
		model.addAttribute("tagPage", tagPage);
		
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> awaint begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> awaint end tb={} >>>>",tb);
			LOGGER.debug(">>>> awaint tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
}
