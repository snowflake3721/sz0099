/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.multi.fetch;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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

import dml.sz0099.course.app.client.controller.home.HomeCoeActivityController;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.converter.CoeActivityConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeActivityFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeActivityPageDto;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
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
public class MultiHomeActivityFetcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MultiHomeActivityFetcher.class);
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private CoeActivityConverter coeActivityConverter;
	
	@Autowired
	private CoeActivityFetcher coeActivityFetcher;
	
	@Autowired
	private CoeCategActivityWrapper categoryWrapper;
	
	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		map.put("fff", null);
	}
	
	/**
	 * 推荐
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityRecommend(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition headPosition = new CoeActivityPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				LOGGER.debug("----1-fetchHomeActivityRecommend, subId:{} begin----",Position.SUBID_1_INDEX.getValueInt());
				CoeActivityPageDto headDto = coeActivityFetcher.fetchActivityIndexHead(headPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
				LOGGER.debug("----2-fetchHomeActivityRecommend, subId:{} end----",Position.SUBID_1_INDEX.getValueInt());
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
				CoeActivity coeActivity = new CoeActivity();
				coeActivity.setRecommend(CoeActivity.RECOMMEND_YES.getValueInt());
				Pageable pageable = new PageRequest(0,20, Direction.DESC, "topLevel","refreshTime");
				Page<CoeActivity> recommendPage = coeActivityWrapper.findPageForRecommend(coeActivity, pageable);;
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
		Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
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
	public Model fetchHomeActivityFlag(User user, final Model model) {
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition actionFlagPosition = new CoeActivityPosition();
				actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto actionFlagDto = coeActivityFetcher.fetchActionFlag(actionFlagPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
				model.addAttribute("actionFlagDto", actionFlagDto);
				long t8=System.currentTimeMillis();
				LOGGER.debug("t8-t1= {} ms",t8-t1);
				countDownLatch.countDown();
			}
		});
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_FLAG);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> flagPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("flagPage", flagPage);
				model.addAttribute("entity", coeCategActivity);
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
	 * 集体
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityJoin(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition joinPostion = new CoeActivityPosition();
				joinPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				joinPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto joinDto = coeActivityFetcher.fetchIndexJoin(joinPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

				model.addAttribute("joinDto", joinDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> joinPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("joinPage", joinPage);
				model.addAttribute("entity", coeCategActivity);
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
	 * 徒步
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityFoot(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition footPostion = new CoeActivityPosition();
				footPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				footPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto footDto = coeActivityFetcher.fetchIndexFoot(footPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_FOOT);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> footPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("footPage", footPage);
				model.addAttribute("entity", coeCategActivity);
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
	public Model fetchHomeActivityTravel(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition travelPostion = new CoeActivityPosition();
				travelPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				travelPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto travelDto = coeActivityFetcher.fetchIndexTravel(travelPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_TRAVEL);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> travelPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("travelPage", travelPage);
				model.addAttribute("entity", coeCategActivity);
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
	 * 骑行
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityBike(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition bikePostion = new CoeActivityPosition();
				bikePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				bikePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto bikeDto = coeActivityFetcher.fetchIndexBike(bikePostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_BIKE);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> bikePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("bikePage", bikePage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> bikePage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> bikePage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> bikePage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	/**
	 * 自驾
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityCar(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition carPostion = new CoeActivityPosition();
				carPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				carPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto carDto = coeActivityFetcher.fetchIndexCar(carPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

				model.addAttribute("carDto", carDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_CAR);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> carPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("carPage", carPage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> carPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> carPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> carPage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	/**
	 * 志愿者
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityVolunteer(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition carPostion = new CoeActivityPosition();
				carPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				carPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto volunteerDto = coeActivityFetcher.fetchIndexVolunteer(carPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

				model.addAttribute("volunteerDto", volunteerDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_VOLUNTEER);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> volunteerPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("volunteerPage", volunteerPage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> volunteerPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> volunteerPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> volunteerPage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	/**
	 * 自由行
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityFree(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition carPostion = new CoeActivityPosition();
				carPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				carPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto freeDto = coeActivityFetcher.fetchIndexFree(carPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

				model.addAttribute("freeDto", freeDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_FREE);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> freePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("freePage", freePage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> freePage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> freePage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> freePage await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		
		return model;
	}
	
	
	/**
	 * 聚餐
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityEat(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition carPostion = new CoeActivityPosition();
				carPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				carPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto eatDto = coeActivityFetcher.fetchIndexEat(carPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_EAT);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> eatPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("eatPage", eatPage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
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
	 * 采摘
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityPick(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition carPostion = new CoeActivityPosition();
				carPostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				carPostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto pickDto = coeActivityFetcher.fetchIndexPick(carPostion, CoeActivityFetcher.STRATEGY_DEFAULT);

				model.addAttribute("pickDto", pickDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_PICK);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> pickPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("pickPage", pickPage);
				model.addAttribute("entity", coeCategActivity);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> pickPage await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> pickPage await end tb={} >>>>",tb);
			LOGGER.debug(">>>> pickPage await tb-ta= {}  ms ",tb-ta);
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
	public Model fetchHomeActivityRoadline(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition roadLinePostion = new CoeActivityPosition();
				roadLinePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				roadLinePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto roadlineDto = coeActivityFetcher.fetchIndexRoadLine(roadLinePostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ROADLINE);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> roadlinePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("roadlinePage", roadlinePage);
				model.addAttribute("entity", coeCategActivity);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t9-t1);
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
	 * 公益
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeActivityWelfare(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition welfarePostion = new CoeActivityPosition();
				welfarePostion.setPonMainId(Robot.ROBOT_PLAT.getId());
				welfarePostion.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto welfareDto = coeActivityFetcher.fetchIndexWelfare(welfarePostion, CoeActivityFetcher.STRATEGY_DEFAULT);

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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ACTIVITY_JOIN_WELFARE);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> welfarePage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("welfarePage", welfarePage);
				model.addAttribute("entity", coeCategActivity);
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
	public Model fetchHomeActivityEquip(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition equipRealPosition = new CoeActivityPosition();
				equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto equipRealDto = coeActivityFetcher.fetchEquipReal(equipRealPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeCategActivity coeCategActivity = new CoeCategActivity();
				Category category = new Category();
				category.setCode(Category.CODE_ARTICLE_EQUIP);
				coeCategActivity.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "activity.topLevel","activity.refreshTime");
				Page<CoeCategActivity> equipPage = categoryWrapper.findPageForPublishWithChildren(coeCategActivity, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("equipPage", equipPage);
				model.addAttribute("entity", coeCategActivity);
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
	
	
	
	
	public Model fetchHomeActivityIndex(User user, final Model model) {
		
		long t1=System.currentTimeMillis();
		final CountDownLatch countDownLatch = new CountDownLatch(8);//定义countDownLatch
		
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CoeActivityPosition headPosition = new CoeActivityPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto headDto = coeActivityFetcher.fetchActivityIndexHead(headPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition explorerPosition = new CoeActivityPosition();
				explorerPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				explorerPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto explorerDto = coeActivityFetcher.fetchExplorer(explorerPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition rememberPosition = new CoeActivityPosition();
				rememberPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				rememberPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto rememberDto = coeActivityFetcher.fetchSpecialRemember(rememberPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition farmstayPosition = new CoeActivityPosition();
				farmstayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				farmstayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto farmstayDto = coeActivityFetcher.fetchEatFarmstay(farmstayPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition outdoorPosition = new CoeActivityPosition();
				outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto outdoorDto = coeActivityFetcher.fetchEatOutdoor(outdoorPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition equipRealPosition = new CoeActivityPosition();
				equipRealPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				equipRealPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto equipRealDto = coeActivityFetcher.fetchEquipReal(equipRealPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				CoeActivityPosition actionFlagPosition = new CoeActivityPosition();
				actionFlagPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				actionFlagPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				CoeActivityPageDto actionFlagDto = coeActivityFetcher.fetchActionFlag(actionFlagPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
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
				Category categoryTree = coeActivityWrapper.findCategoryTree(mainId, subId);
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
				Page<CoeActivity> invitorPage = coeActivityWrapper.findPageForInvitor(createdBy,userId);
				model.addAttribute("invitorPage", invitorPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<CoeActivity> contentList = invitorPage.getContent();
				CircleMain circleMain = coeActivityConverter.convert(contentList,coeUser);
				model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
				
				long t10=System.currentTimeMillis();
				LOGGER.debug("t10-t1= {} ms",t10-t1);
				countDownLatch.countDown();
			}
		});*/
		
		CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
		model.addAttribute("currentUser", coeUser);
		
		//4.当前登录用户最新文章标签
		Page<CoeActivity>  tagPage = coeActivityWrapper.findPageTagForCurrentUser(userId);
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
