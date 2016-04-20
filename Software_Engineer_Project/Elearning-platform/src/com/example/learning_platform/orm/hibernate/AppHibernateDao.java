package com.example.learning_platform.orm.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.util.Assert;

import com.example.learning_platform.orm.Page;
import com.example.learning_platform.orm.PropertyFilter;


/**
 * @author zdc
 * 
 */
public class AppHibernateDao<T, PK extends Serializable> extends
		HibernateDao<T, PK> {
	/**
	 * 保存对象别名，以免重复添加导致Hibernate报异常.
	 */
	private Set<String> aliases ;
	private Set<String> prefixSet;

	/**
	 * 按属性过滤条件列表分页查找对象. 添加了关联查询和关联排序功能
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters) {
		Assert.notNull(page, "page不能为空");
		DetachedCriteria dc = buildPropertyFilterDetachedCriteria(page, filters);
		Criteria c = dc.getExecutableCriteria(getSession());
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(c, page);
	
		List result = c.list();
		page.setResult(result);
		return page;
	}

	protected DetachedCriteria buildPropertyFilterDetachedCriteria(
			Page<T> page, final List<PropertyFilter> filters) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		aliases = new HashSet<String>();
		prefixSet = new HashSet<String>();
		// 通过page的OrderBy设置别名
		if(page!=null){
			String orderBy = page.getOrderBy();
			if (orderBy != null) {
				String[] orderBys = orderBy.split(",");
				for (String s : orderBys) {
					if (s.contains("."))
						addAliasesToDc(dc, s);
				}
			}	
		}
		for (PropertyFilter filter : filters) {
			if (!filter.hasMultiProperties()) {
				if (filter.getPropertyName().contains(".")) {
					addAliasesToDc(dc, filter.getPropertyName());
				}
			} else { // 含有or的情况
				for (String propertyName : filter.getPropertyNames()) {
					if (propertyName.contains(".")) {
						addAliasesToDc(dc, propertyName);
					}
				}
			}
		}
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		for (Criterion criterion : criterions) {
			dc.add(criterion);
		}

		return dc;
	}

	private void addAliasesToDc(DetachedCriteria dc, String filterName) {
		String prefix = StringUtils.substringBeforeLast(filterName, ".");
		if (!prefixSet.contains(prefix)) {
			String[] names = prefix.split("_");
			String associationPath = null;
			String alias = null;
			for (int i = 0; i < names.length; i++) {
				if (i != 0) {
					associationPath = alias + "." + names[i];
					alias = alias + "_" + names[i];
				} else {
					associationPath = names[0];
					alias = names[0];
				}
				if (!aliases.contains(alias)) {
					dc.createAlias(associationPath, alias,
							CriteriaSpecification.LEFT_JOIN);// 默认用left
																// join
					aliases.add(alias);
				}

			}
			prefixSet.add(prefix);
		}
	}
	
	/*
	 * 增强属性过滤查询,便于多张表联查
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByMyFilter(final List<PropertyFilter> filters){
		DetachedCriteria dc = buildPropertyFilterDetachedCriteria(null, filters);
		Criteria c = dc.getExecutableCriteria(getSession());
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public Long getCountByFilter(final List<PropertyFilter> filters){
		DetachedCriteria dc = buildPropertyFilterDetachedCriteria(null, filters);
		dc.setProjection(Projections.rowCount());
		Criteria c = dc.getExecutableCriteria(getSession());
		List<Long> result=c.list();
		Long length=null;
		if(result!=null){
		   length=result.get(0);
		}
		return length;
	}

	public Set<String> getAliases() {
		return aliases;
	}

	public Set<String> getPrefixSet() {
		return prefixSet;
	}

}
