package com.ai2331.common.choice.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai2331.common.choice.Choice;
import com.ai2331.common.choice.ChoiceConfig;
import com.ai2331.common.choice.api.ChoiceService;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.util.DateUtils;

/**
 * 提供动态选项功能. (相比而言，lookup是静态的)
 */
public abstract class AbstractChoiceService implements ChoiceService {
	protected static Log log = LogFactory.getLog(AbstractChoiceService.class);

	@Override
	public GridX<List<Choice>> queryChoice(String choiceCode, String q, String extraKeys, PageX px) {
		GridX<List<Choice>> gx = new GridX<>(px);

		ChoiceConfig cc = getChoiceConfig(choiceCode);
		if (cc == null) {
			log.warn("Invalid choice code - " + choiceCode);
			return gx;
		}

		if (px.getTotalNumber() == 0) {
			px.setTotalNumber(getTotalCountInternal(cc, q, extraKeys));
		}

		if (px.getTotalNumber() > 0) {
			gx.setRows(queryChoiceInternal(cc, q, cc.getExtraFields(), px));
		}
		return gx;
	}

	@Override
	public GridX<List<Choice>> queryChoice(String choiceCode, String q, PageX px) {
		return queryChoice(choiceCode, q, null, px);
	}

	@Override
	public List<List<Choice>> listChoice(String choiceCode, String[] ids, boolean keepOrder, String extraKeys) {
		ChoiceConfig cc = getChoiceConfig(choiceCode);
		if (cc == null) {
			log.warn("Invalid choice code - " + choiceCode);
			return Collections.emptyList();
		}
		List<List<Choice>> list = listChoiceInternal(cc, extraKeys, ids);
		if (!keepOrder || list == null || list.size() < 2) {
			return list;
		} else {
			// keep order
			List<List<Choice>> retList = new ArrayList<>();
			for (String id : ids) {
				Iterator<List<Choice>> it = list.iterator();
				while (it.hasNext()) {
					List<Choice> c = it.next();
					if (c.get(0).getId().equals(id)) {
						retList.add(c);
						it.remove();
					}
				}
			}
			return retList;
		}
	}

	@Override
	public List<List<Choice>> listChoice(String choiceCode, String[] ids, boolean keepOrder) {
		return listChoice(choiceCode, ids, keepOrder, null);
	}

	@Override
	public List<List<Choice>> listChoice(String choiceCode, String... ids) {
		return listChoice(choiceCode, ids, false, null);
	}

	@Override
	public String mapName(String choiceCode, String ids, String extraKeys) {
		List<List<Choice>> list = listChoice(choiceCode, StringUtils.split(ids, ", "), true, extraKeys);
		if (list == null || list.size() == 0)
			return null;
		else if (list.size() == 1)
			return getFieldStringVal(list.get(0).get(1).getValue());
		else {
			StringBuilder sb = new StringBuilder();
			String sep = "";
			for (List<Choice> c : list) {
				sb.append(sep).append(getFieldStringVal(c.get(1).getValue()));
				sep = ",";
			}
			return sb.toString();
		}
	}

	@Override
	public String mapName(String choiceCode, String ids) {
		return mapName(choiceCode, ids, null);
	}

	private Map<String, ChoiceConfig> configCache = new ConcurrentHashMap<String, ChoiceConfig>();

	@Override
	public List<Choice> getChoice(String choiceCode, String id) {
		List<List<Choice>> list = listChoice(choiceCode, id);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public ChoiceConfig getChoiceConfig(String choiceCode) {
		ChoiceConfig cc = configCache.get(choiceCode);
		if (cc != null)
			return cc;

		synchronized (this) {
			cc = configCache.get(choiceCode);
			if (cc != null)
				return cc;

			cc = getChoiceConfigInternal(choiceCode);
			if (cc != null) {
				configCache.put(choiceCode, cc);
			}
		}
		return cc;
	}

	@Override
	public void saveChoiceConfig(ChoiceConfig config) {
		ChoiceConfig cc = getChoiceConfigInternal(config.getChoiceCode());
		if (cc != null) {
			updateChoiceConfigInternal(config);
		} else {
			insertChoiceConfigInternal(config);
		}

		clearCache(config.getChoiceCode());
	}

	@Override
	public void deleteChoiceConfig(String choiceCode) {
		int ret = deleteChoiceConfigInternal(choiceCode);
		if (ret > 0)
			clearCache(choiceCode);
	}

	// protected
	// to be subclassed
	protected void clearCache(String choiceCode) {
		configCache.remove(choiceCode);
	}

	protected String getFieldStringVal(Object data) {
		if (data == null)
			return null;

		if (data instanceof byte[]) {
			return new String((byte[]) data);
		} else if (data instanceof Date) {
			return DateUtils.getDefaultDateTimeStr((Date) data);
		} else {
			return data.toString();
		}
	}

	/**
	 * 满足条件的总记录数.
	 */
	protected abstract Long getTotalCountInternal(ChoiceConfig cc, String q, String extraKeys);

	/**
	 * 分页查询选项.
	 */
	protected abstract List<List<Choice>> queryChoiceInternal(ChoiceConfig cc, String q, String extraKeys, PageX px);

	/**
	 * 根据Id（多个）取对象.
	 */
	protected abstract List<List<Choice>> listChoiceInternal(ChoiceConfig cc, String extraKeys, String[] ids);

	protected abstract ChoiceConfig getChoiceConfigInternal(String choiceCode);

	protected abstract void updateChoiceConfigInternal(ChoiceConfig config);

	protected abstract void insertChoiceConfigInternal(ChoiceConfig config);

	protected abstract int deleteChoiceConfigInternal(String choiceCode);
}
