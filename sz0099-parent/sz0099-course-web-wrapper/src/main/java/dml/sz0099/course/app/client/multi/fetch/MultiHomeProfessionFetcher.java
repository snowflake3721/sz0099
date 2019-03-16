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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.data.show.blooming.converter.ProfessionConverter;
import dml.sz0099.course.app.data.show.layout.fetcher.ProfessionFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.ProfessionPageDto;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
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
public class MultiHomeProfessionFetcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MultiHomeProfessionFetcher.class);
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ProfessionConverter professionConverter;
	
	@Autowired
	private ProfessionFetcher professionFetcher;
	
	@Autowired
	private CategProfessionWrapper categoryWrapper;
	
	ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	/**
	 * Tab:推荐
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionRecommend(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexHead(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.推荐技, Profession.recommend==1
		executor.execute(new Runnable() {
			@Override
			public void run() {
				Profession profession = new Profession();
				profession.setRecommend(Profession.RECOMMEND_YES.getValueInt());
				Pageable pageable = new PageRequest(0,20, Direction.DESC, "topLevel","refreshTime");
				Page<Profession> recommendPage = professionWrapper.findPageForRecommend(profession, pageable);;
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
		Page<Profession>  tagPage = professionWrapper.findPageTagForCurrentUser(userId);
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
	 * Tab:八仙过海
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionMajor(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播专业技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexMajor(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.专业技, Profession.recommend==1
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_MAJOR);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("majorPage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> major await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> major await end tb={} >>>>",tb);
			LOGGER.debug(">>>> major await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	/**
	 * Tab:出行技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionOutdoor(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播出行技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexOutdoor(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.出行技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_OUTDOOR);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("outdoorPage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> outdoor await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> outdoor await end tb={} >>>>",tb);
			LOGGER.debug(">>>> outdoor await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	/**
	 * Tab:生活技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionLife(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播生活技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexLife(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.生活技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_LIFE);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("lifePage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> life await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> life await end tb={} >>>>",tb);
			LOGGER.debug(">>>> life await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	/**
	 * Tab:匠心技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionIngenuity(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播生活技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexIngenuity(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.匠心技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_MAJOR_INGENUITY);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("ingenuityPage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> ingenuity await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> ingenuity await end tb={} >>>>",tb);
			LOGGER.debug(">>>> ingenuity await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	
	/**
	 * Tab:乐趣技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionRelax(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播乐趣技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexRelax(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.乐趣技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_RELAX);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("relaxPage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> relax await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> relax await end tb={} >>>>",tb);
			LOGGER.debug(">>>> relax await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	
	/**
	 * Tab:特产技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionNative(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播乐趣技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexNative(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.特产技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_NATIVE);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("nativePage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> native await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> native await end tb={} >>>>",tb);
			LOGGER.debug(">>>> native await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	/**
	 * Tab:装备技
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionEquip(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播装备技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexEquip(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.装备技
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_OUTDOOR_EQUIPMENT);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("equipPage", flagPage);
				model.addAttribute("entity", categProfession);
				
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
	
	
	/**
	 * Tab:直供
	 * @param user
	 * @param model
	 * @return
	 */
	public Model fetchHomeProfessionFactory(User user, final Model model) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);//定义countDownLatch
		
		long t1=System.currentTimeMillis();
		//1.头条滚播 直供
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexFactory(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("headDto", headDto);
				long t2=System.currentTimeMillis();
				LOGGER.debug("t2-t1= {} ms",t2-t1);
				countDownLatch.countDown();
			}
		});
		
		//2.直供
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
				CategProfession categProfession = new CategProfession();
				Category category = new Category();
				category.setCode(Category.CODE_PROFESSION_MAJOR_FACTORY);
				categProfession.setCategory(category);
				List<Long> excludeMainIdList = null;
				boolean cover = true;
				boolean banner = true;
				boolean author = false;
				Pageable pageable = new PageRequest(0, 20,Direction.DESC, "profession.topLevel","profession.refreshTime");
				Page<CategProfession> flagPage = categoryWrapper.findPageForPublishWithChildren(categProfession, excludeMainIdList, pageable, cover,
						banner, author);
				model.addAttribute("factoryPage", flagPage);
				model.addAttribute("entity", categProfession);
				
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t1= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		try {
			long ta=System.currentTimeMillis();
			LOGGER.debug(">>>> factory await begin, ta={} >>>>>",ta);
			countDownLatch.await();
			long tb=System.currentTimeMillis();
			LOGGER.debug(">>>> factory await end tb={} >>>>",tb);
			LOGGER.debug(">>>> factory await tb-ta= {}  ms ",tb-ta);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException, {} ",e);
		}
		return model;
	}
	
	public Model fetchHomeProfessionIndex(User user, final Model model) {
		
		long t1=System.currentTimeMillis();
		final CountDownLatch countDownLatch = new CountDownLatch(7);//定义countDownLatch
		
		//1.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition headPosition = new ProfessionPosition();
				headPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				headPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto headDto = professionFetcher.fetchIndexHead(headPosition, ProfessionFetcher.STRATEGY_DEFAULT);
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
				ProfessionPosition recommendPosition = new ProfessionPosition();
				recommendPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				recommendPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto recommendDto = professionFetcher.fetchIndexRecommend(recommendPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("recommendDto", recommendDto);
				long t3=System.currentTimeMillis();
				LOGGER.debug("t3-t2= {} ms",t3-t1);
				countDownLatch.countDown();
			}
		});
		//3.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition artisanPosition = new ProfessionPosition();
				artisanPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				artisanPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto artisanDto = professionFetcher.fetchIndexArtisan(artisanPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("artisanDto", artisanDto);
				long t4=System.currentTimeMillis();
				LOGGER.debug("t4-t1= {} ms",t4-t1);
				countDownLatch.countDown();
			}
		});
		//4.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition todayPosition = new ProfessionPosition();
				todayPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				todayPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto todayDto = professionFetcher.fetchRecommendForMainCircle(todayPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("todayDto", todayDto);
				long t5=System.currentTimeMillis();
				LOGGER.debug("t5-t1= {} ms",t5-t1);
				countDownLatch.countDown();
			}
		});
		
		//5.
		executor.execute(new Runnable() {
			@Override
			public void run() {
				ProfessionPosition outdoorPosition = new ProfessionPosition();
				outdoorPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				outdoorPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto outdoorDto = professionFetcher.fetchIndexOutdoor(outdoorPosition, ProfessionFetcher.STRATEGY_DEFAULT);
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
				ProfessionPosition killPosition = new ProfessionPosition();
				killPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
				killPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
				ProfessionPageDto killDto = professionFetcher.fetchIndexKill(killPosition, ProfessionFetcher.STRATEGY_DEFAULT);
				model.addAttribute("killDto", killDto);
				long t7=System.currentTimeMillis();
				LOGGER.debug("t7-t1= {} ms",t7-t1);
				countDownLatch.countDown();
			}
		});
		//7.
		Long userId=user.getId();
		/*executor.execute(new Runnable() {
			@Override
			public void run() {
				//3.邀请人前三篇文章列表
				
				Long mainId = null;
				Long subId = null;
				
				//邀请人技能列表
				Long userId=user.getId();
				Long createdBy = user.getCreatedBy();
				Page<Profession> invitorPage = professionWrapper.findPageForInvitor(createdBy,userId);
				model.addAttribute("invitorPage", invitorPage);
				
				CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
				model.addAttribute("currentUser", coeUser);
				//将分页技能组装成圆形菜单数据
				List<Profession> contentList = invitorPage.getContent();
				CircleMain circleMain = professionConverter.convert(contentList, coeUser);
				model.addAttribute("circleMain", GsonBuilderUtils.toJson(circleMain));
				
				long t10=System.currentTimeMillis();
				LOGGER.debug("t10-t1= {} ms",t10-t1);
				countDownLatch.countDown();
			}
		});*/
		
		//8>>>2.查找分类树
		executor.execute(new Runnable() {
			@Override
			public void run() {
				Long mainId = null;
				Long subId = null;
				Category categoryTree = professionWrapper.findCategoryTree(mainId, subId);
				model.addAttribute("categoryTree", categoryTree);
				long t9=System.currentTimeMillis();
				LOGGER.debug("t9-t1= {} ms",t9-t1);
				countDownLatch.countDown();
			}
		});
		
		CoeUser coeUser = coeUserWrapper.findAvailableByUserId(userId);
		model.addAttribute("currentUser", coeUser);
		
		//4.当前登录用户技能标签
		Page<Profession>  tagPage = professionWrapper.findPageTagForCurrentUser(userId);
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
