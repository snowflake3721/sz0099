package dml.sz0099.course.app.biz.service.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringEscapeUtils;
import org.codehaus.plexus.util.StringUtils;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.persist.dao.article.ParagraphDao;
import dml.sz0099.course.app.persist.entity.article.Paragraph;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;


/**
 * 
 * @formatter:off
 * description: ParagraphServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("articleParagraphServiceImpl")
public class ParagraphServiceImpl extends GenericServiceImpl<Paragraph, Long> implements ParagraphService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphServiceImpl.class);

	@Autowired
	private ParagraphDao paragraphDao;
	
	@Autowired
	private PhotoParagService photoParagService;
	

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragraphDao;
	}

	/**
	 * 根据Id查询Paragraph实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Paragraph findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Paragraph paragraph = paragraphDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragraph);
		return paragraph;
	}
	
	@Override
	public Paragraph findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Paragraph paragraph = paragraphDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragraph);
		return paragraph;
	}

	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Paragraph> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Paragraph> content =findByIdList(idList, true);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", content);
		return content;
	}
	
	@Transactional
	public Paragraph mergeForOrderSeq(Paragraph paragraph) {
		Long id = paragraph.getId();
		Paragraph entity = findById(id);
		if(null != entity) {
			Long orderSeq = paragraph.getOrderSeq();
			entity.setOrderSeq(orderSeq);
			entity.setLastModifiedBy(paragraph.getLastModifiedBy());
			entity.setLastModifiedDate(paragraph.getLastModifiedDate());
		}
		return entity;
	}
	
	public List<Paragraph> resetOrderSeqByIdList(List<Long> idList, boolean withPhoto, Long userId){
		List<Paragraph> content = paragraphDao.findByIdList(idList);
		if(withPhoto) {
			resetOrderForPhotoParag(content, userId);
		}
		return content;
	}
	
	public void resetOrderForPhotoParag(List<Paragraph> content, Long userId){
		
		if(null !=content && !content.isEmpty()) {
			int psize = content.size();
			Map<Long, Paragraph> map = new HashMap<>(psize);
			List<Long> idList = new ArrayList<>(psize);
			DateTime lastModifiedDate = new DateTime();
			for(int j=0;j<psize;j++) {
				Paragraph c = content.get(j);
				c.setOrderSeq(j+1l);
				c.setLastModifiedBy(userId);
				
				c.setLastModifiedDate(lastModifiedDate);
				
				String description = c.getDescription();
				if(StringUtils.isNotBlank(description)) {
					c.setDescription(HtmlUtils.htmlUnescape(description));
				}
				
				String title = c.getTitle();
				if(StringUtils.isNotBlank(title)) {
					c.setTitle(HtmlUtils.htmlUnescape(title));
				}
				
				Long id = c.getId();
				map.put(c.getId(), c);
				idList.add(id);
				
			}
			if(!idList.isEmpty()) {
				List<PhotoParag> photoParags = photoParagService.findByParagIdList(idList);
				if(null != photoParags && !photoParags.isEmpty()) {
					int size = photoParags.size();
					for(int i=0;i<size;i++) {
						PhotoParag pp = photoParags.get(i);
						Long paragId = pp.getParagId();
						pp.setOrderSeq(i+1l);
						pp.setLastModifiedBy(userId);
						pp.setLastModifiedDate(lastModifiedDate);
						
						photoParagService.mergeRefForOrder(pp);
						
						
						Paragraph paragraph = map.get(paragId);
						List<PhotoParag> inParag = null;
						if(null != paragraph) {
							inParag = paragraph.getPhotoList();
							if(null == inParag) {
								inParag = new ArrayList<>();
							}
							inParag.add(pp);
							String description = pp.getDescription();
							if(StringUtils.isNotBlank(description)) {
								description=StringEscapeUtils.unescapeJava(description);
								pp.setDescription(HtmlUtils.htmlUnescape(description));
							}
							paragraph.setPhotoList(inParag);
						}
					}
				}
			}
		}
	}
	
	public List<Paragraph> findByIdList(List<Long> idList, boolean withPhoto){
		List<Paragraph> content = paragraphDao.findByIdList(idList);
		if(withPhoto) {
			fillPhotoParagForContent(content);
		}
		return content;
	}
	
	public void fillPhotoParagForContent(List<Paragraph> content){
		
		if(null !=content && !content.isEmpty()) {
			Map<Long, Paragraph> map = new HashMap<>(content.size());
			List<Long> idList = new ArrayList<>();
			for(Paragraph c : content) {
				Long id = c.getId();
				
				String description = c.getDescription();
				if(StringUtils.isNotBlank(description)) {
					c.setDescription(HtmlUtils.htmlUnescape(description));
				}
				
				String title = c.getTitle();
				if(StringUtils.isNotBlank(title)) {
					c.setTitle(HtmlUtils.htmlUnescape(title));
				}
				
				map.put(c.getId(), c);
				idList.add(id);
			}
			if(!idList.isEmpty()) {
				List<PhotoParag> photoParags = photoParagService.findByParagIdList(idList);
				for(PhotoParag pp : photoParags) {
					Long paragId = pp.getParagId();
					Paragraph paragraph = map.get(paragId);
					List<PhotoParag> inParag = null;
					if(null != paragraph) {
						inParag = paragraph.getPhotoList();
						if(null == inParag) {
							inParag = new ArrayList<>();
						}
						inParag.add(pp);
						String description = pp.getDescription();
						if(StringUtils.isNotBlank(description)) {
							description=StringEscapeUtils.unescapeJava(description);
							pp.setDescription(HtmlUtils.htmlUnescape(description));
						}
						paragraph.setPhotoList(inParag);
					}
				}
			}
		}
	}
	
	@Override
	public Map<Long,Paragraph> findByIdListForMap(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdListForMap begin ---------  ");
		List<Paragraph> paragraphList = findByIdList(idList);
		if(null != paragraphList && !paragraphList.isEmpty()) {
			Map<Long,Paragraph> map = new HashMap<Long,Paragraph>();
			for(Paragraph p: paragraphList) {
				map.put(p.getId(), p);
			}
			return map;
		}
		LOGGER.debug("--- service>>>findByIdListForMap end ---------  result is {} ", paragraphList);
		return null;
	}
	

	@Transactional
	@Override
	public Paragraph persistEntity(Paragraph paragraph) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Paragraph entity = save(paragraph);
		Long id = paragraph.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Paragraph.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Paragraph mergeEntity(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Paragraph entity = findById(id);
		if(entity != null) {
			entity.setDescription(paragraph.getDescription());
			entity.setName(paragraph.getName());
			entity.setTitle(paragraph.getTitle());
			//entity.setUserId(paragraph.getUserId());
			Long seq = paragraph.getOrderSeq();
			if(null != seq) {
				entity.setOrderSeq(seq);
			}
			entity.setLastModifiedBy(paragraph.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Paragraph.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Paragraph saveOrUpdate(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Paragraph entity = null;
		if(null != id) {
			entity = mergeEntity(paragraph);
		}else {
			entity = persistEntity(paragraph);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Paragraph> page = paragraphDao.findPage(paragraph, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragraphDao.existById(id);
	}


	/**
	 * 创建一个段落
	 */
	@Transactional
	public Paragraph createParagraph(Paragraph paragraph ){		

		LOGGER.debug("-------service>>>createParagraph----------begin---------");

		Paragraph entity = persistEntity(paragraph);
		return entity;
	}

	@Transactional
	public void deleteByIdListAndUserId(List<Long> idList, Long userId , boolean cascade){		

		LOGGER.debug("-------service>>>deleteByIdListAndUserId----------begin---------");
		//List<Paragraph> paragraphList = findByIdList(idList);
		//1.删除段落
		paragraphDao.deleteByIdListAndUserId( idList,  userId );
		if(cascade) {
			//2.解除段落与图片关联
			photoParagService.deleteByParagIdListAndUserId(idList, userId,cascade);
		}
	}

	@Transactional
	@Override
	public Paragraph persistForPhoto(Paragraph paragraph) {
		
		//添加图片信息
		if(null != paragraph) {
			List<PhotoParag>  photoList = paragraph.getPhotoList();
			photoList=photoParagService.persistForPhoto(photoList);
			paragraph.setPhotoList(photoList);
			paragraph.setSuccess(Paragraph.SUCCESS_YES);
		}
		return paragraph;
	}


}
