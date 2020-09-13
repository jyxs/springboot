package com.ai2331.sys.util.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai2331.common.choice.Choice;
import com.ai2331.common.choice.ChoiceConfig;
import com.ai2331.common.choice.ChoiceConfig.ExtraField;
import com.ai2331.common.choice.impl.AbstractChoiceService;
import com.ai2331.common.entity.ExceptionX;
import com.ai2331.common.entity.PageX;
import com.ai2331.common.sql.Dialect;
import com.ai2331.common.sql.dialect.DialectMysql;
import com.ai2331.common.sql.dialect.FieldNameMapper;
import com.ai2331.sys.util.dao.BasicDAO;
import com.ai2331.sys.util.dao.ChoiceDAO;

public class ChoiceServiceImpl extends AbstractChoiceService {
	// SQL中占位符固定
	public static final String PLACEHOLDER_Q = "${q}";
	public static final String PLACEHOLDER_EXTRA = "${extra}";
	public static final String PLACEHOLDER_IDIN = "${in}";
	protected FieldNameMapper fieldNameMapper = FieldNameMapper.M_DEFAULT;
	@Autowired
	private ChoiceDAO dao;

	@Autowired
	private BasicDAO basicDAO;

	@Override
	public List<ChoiceConfig> listChoiceConfig(String codeLike, String nameLike) {
		return dao.listChoiceConfig(codeLike, nameLike);
	}

	@Override
	protected Long getTotalCountInternal(ChoiceConfig cc, String q, String extraKeys) {
		if (q != null)
			q = q.replace("'", "''");
		else
			q = "";
		extraKeys = normalizeExtraKeys(extraKeys);

		String sql = StringUtils.replace(cc.getQuerySelector(), PLACEHOLDER_Q, q);
		sql = StringUtils.replace(sql, PLACEHOLDER_EXTRA, extraKeys);

		Dialect dialect = new DialectMysql();
		String cntSql = dialect.getCountSql(sql);

		log.info("getTotalCountInternal sql:" + cntSql);

		return basicDAO.getCount(cntSql);
	}

	@Override
	protected List<List<Choice>> queryChoiceInternal(ChoiceConfig cc, String q, String extraKeys, PageX px) {
		if (q != null)
			q = q.replace("'", "''");
		else
			q = "";
		extraKeys = normalizeExtraKeys(extraKeys);

		String sql = StringUtils.replace(cc.getQuerySelector(), PLACEHOLDER_Q, q);
		sql = StringUtils.replace(sql, PLACEHOLDER_EXTRA, extraKeys);

		return querySql(sql, px, cc);
	}

	@Override
	protected List<List<Choice>> listChoiceInternal(ChoiceConfig cc, String extraKeys, String[] ids) {
		if (ids == null || ids.length == 0)
			return Collections.emptyList();

		StringBuilder buf = new StringBuilder();
		String sep = "";
		for (String id : ids) {
			buf.append(sep).append("'").append(id).append("'");
			sep = ",";
		}
		String sql = StringUtils.replace(cc.getGetSelector(), PLACEHOLDER_IDIN, buf.toString());
		extraKeys = normalizeExtraKeys(extraKeys);
		sql = StringUtils.replace(sql, PLACEHOLDER_EXTRA, extraKeys);

		log.info("listChoiceInternal sql:" + sql);
		return querySql(sql, cc);
	}

	@Override
	protected ChoiceConfig getChoiceConfigInternal(String choiceCode) {
		return dao.getChoiceConfig(choiceCode);
	}

	@Override
	protected void updateChoiceConfigInternal(ChoiceConfig config) {
		dao.updateChoiceConfig(config);
	}

	@Override
	protected void insertChoiceConfigInternal(ChoiceConfig config) {
		dao.insertChoiceConfig(config);
	}

	@Override
	protected int deleteChoiceConfigInternal(String choiceCode) {
		return dao.deleteChoiceConfig(choiceCode);
	}

	private String normalizeExtraKeys(String extraKeys) {
		if (extraKeys != null) {
			extraKeys = extraKeys.replace("'", "''");
			// 多个逗号分隔的处理
			if (extraKeys.indexOf(",") >= 0) {
				String[] ss = StringUtils.split(extraKeys, ", ");
				StringBuilder buf = new StringBuilder();
				String sep = "";
				for (String s : ss) {
					if (StringUtils.isNotBlank(s)) {
						buf.append(sep).append("'").append(s).append("'");
						sep = ",";
					}
				}
				extraKeys = buf.toString();
			} else {
				extraKeys = "'" + extraKeys + "'";
			}
		} else
			extraKeys = "";

		return extraKeys;
	}

	private List<List<Choice>> convert(List<Map<String, Object>> datas, ChoiceConfig cc, String[] fields, Map<String, ExtraField> extraFieldMap) {
		if (null == datas || datas.size() == 0) {
			return null;
		}
		List<List<Choice>> results = new ArrayList<>();
		for (int i = 0; i < datas.size(); i++) {
			List<Choice> choices = new ArrayList<Choice>();
			Map<String, Object> row = datas.get(i);
			for (int j = 0; j < fields.length; j++) {
				Choice obj = new Choice();
				switch (j) {
				case 0:
					String idLabel = cc.getIdLabel();
					obj.setId("id");
					obj.setName(StringUtils.isEmpty(idLabel) ? obj.getId() : idLabel);
					break;
				case 1:
					String nameLabel = cc.getNameLabel();
					obj.setId("name");
					obj.setName(StringUtils.isEmpty(nameLabel) ? obj.getName() : nameLabel);
					break;
				default:
					String pname = fieldNameMapper.getPropertyName(fields[j]);
					if ("id".equals(pname) || "name".equals(pname)) {
						throw new ExceptionX("Extra column label CAN'T be \"id\" or \"name\"");
					}
					if (null == extraFieldMap || null == extraFieldMap.get(pname)) {
						continue;
					}
					ExtraField field = extraFieldMap.get(pname);
					obj.setId(pname);
					obj.setName(field.getLabel());
					break;
				}
				obj.setValue(getFieldStringVal(row.get(fields[j])));
				choices.add(obj);
			}
			results.add(choices);
		}
		return results;
	}

	private List<List<Choice>> querySql(String sql, PageX px, ChoiceConfig cc) {
		Dialect dialect = new DialectMysql();
		sql = dialect.getPageSql(sql, px);
		Map<String, ExtraField> extraFieldMap = cc.getExtraFieldMap();
		log.info("queryChoiceInternal sql:" + sql);
		return convert(basicDAO.getDatas(sql), cc, dialect.getFields(sql), extraFieldMap);
	}

	private List<List<Choice>> querySql(String sql, ChoiceConfig cc) {
		Dialect dialect = new DialectMysql();
		Map<String, ExtraField> extraFieldMap = cc.getExtraFieldMap();
		log.info("queryChoiceInternal sql:" + sql);
		return convert(basicDAO.getDatas(sql), cc, dialect.getFields(sql), extraFieldMap);
	}
}
