package com.ai2331.common.choice;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ai2331.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * 选择项配置.
 */
public class ChoiceConfig {
	private String choiceCode; // 选项实体代码
	private String choiceName; // 选项实体名称
	private String idLabel; // 选项标识的标题，为空时弹层grid中隐藏
	private String nameLabel; // 选项名称的标题
	private String extraFields; // 扩展字段定义，json格式
	private String querySelector; // 查询时的配置，如SQL
	private String getSelector; // 根据Id取对象的配置，如SQL

	private List<ExtraField> _list;
	private Map<String, ExtraField> _map;

	public String getChoiceCode() {
		return choiceCode;
	}

	public void setChoiceCode(String choiceCode) {
		this.choiceCode = choiceCode;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public String getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(String idLabel) {
		this.idLabel = idLabel;
	}

	public String getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(String nameLabel) {
		this.nameLabel = nameLabel;
	}

	public String getQuerySelector() {
		return querySelector;
	}

	public void setQuerySelector(String querySelector) {
		this.querySelector = querySelector;
	}

	public String getGetSelector() {
		return getSelector;
	}

	public void setGetSelector(String getSelector) {
		this.getSelector = getSelector;
	}

	@JsonRawValue
	public String getExtraFields() {
		return extraFields;
	}

	public void setExtraFields(String extraFields) {
		this.extraFields = extraFields;
		_list = null;
	}

	@JsonIgnore
	public List<ExtraField> getExtraFieldList() {
		if (_list != null)
			return _list;
		try {
			_list = JsonUtil.readList(extraFields, ExtraField.class);
		} catch (IOException e) {
			_list = Collections.emptyList();
		}
		return _list;
	}

	@JsonIgnore
	public Map<String, ExtraField> getExtraFieldMap() {
		if (_map != null)
			return _map;
		try {
			_list = JsonUtil.readList(extraFields, ExtraField.class);
			if (null != _list && _list.size() > 0) {
				_map = new HashMap<>();
				_list.forEach(item -> {
					_map.put(item.getFieldName(), item);
				});
			}
		} catch (IOException e) {
			_map = Collections.emptyMap();
		}
		return _map;
	}

	public void setExtraFieldList(List<ExtraField> list) {
		if (list == null) {
			this.extraFields = null;
		} else {
			this.extraFields = JsonUtil.toJSON(list);
		}
		_list = list;
	}

	// 所用到的lookup
	@JsonIgnore
	public String getLookupCodes() {
		StringBuilder buf = new StringBuilder();
		List<ExtraField> list = getExtraFieldList();
		for (ExtraField f : list) {
			if (StringUtils.isNotEmpty(f.getLookup())) {
				if (buf.length() > 0) {
					buf.append(",");
				}
				buf.append(f.getLookup());
			}
		}
		return buf.toString();
	}

	public static class ExtraField {
		private String fieldName; // 字段名，user_id将会转换成userId
		private String label; // 标题
		private Integer width; // 宽度
		private Boolean image; // 是否图片
		private String lookup; // lookup.group

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public Integer getWidth() {
			return width;
		}

		public void setWidth(Integer width) {
			this.width = width;
		}

		public Boolean getImage() {
			return image;
		}

		public void setImage(Boolean image) {
			this.image = image;
		}

		public String getLookup() {
			return lookup;
		}

		public void setLookup(String lookup) {
			this.lookup = lookup;
		}

	}
}
