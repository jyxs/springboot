package com.ai2331.common.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class OptionGroup implements Serializable {
	private static final long serialVersionUID = -684491920340124687L;

	public static final String SEP = ",";

	private String code;
	private Map<String, Option> options;

	public OptionGroup() {
		super();
	}

	public OptionGroup(String code) {
		super();
		this.code = code;
	}

	public OptionGroup(String code, Map<String, Option> options) {
		super();
		this.code = code;
		this.options = options;
	}

	public void addOption(Option opt) {
		if (options == null)
			options = new LinkedHashMap<String, Option>();
		options.put(opt.getCode(), opt);
	}

	public String getCodeByName(String name) {
		if (options == null || name == null)
			return "";

		Iterator<Option> it = options.values().iterator();
		while (it.hasNext()) {
			Option o = it.next();
			if (name.equals(o.getName())) {
				return o.getCode();
			}
		}
		return "";
	}

	//
	public Collection<Option> getOptionList() {
		return options == null ? null : options.values();
	}

	public Option getOption(String code) {
		return options == null ? null : options.get(code);
	}

	public Option getOption(int code) {
		return getOption(String.valueOf(code));
	}

	public String getName(String code) {
		Option opt = getOption(code);
		return opt == null ? code : opt.getName();
	}

	public String getName(int code) {
		return getName(String.valueOf(code));
	}

	public List<Option> getMultiOption(String... codes) {
		if (codes == null || codes.length == 0)
			return null;

		if (options == null) {
			return null;
		}
		List<Option> result = new ArrayList<Option>();
		for (String code : codes) {
			String[] cc = StringUtils.split(code, SEP);
			for (String c : cc) {
				Option opt = options.get(c);
				if (null != opt) {
					result.add(opt);
				}
			}
		}
		return result;
	}

	public String getMultiName(String... codes) {
		if (codes == null || codes.length == 0)
			return "";

		if (options == null) {
			return "";
		}

		StringBuilder buf = new StringBuilder();
		String sepNow = "";
		for (String code : codes) {
			String[] cc = StringUtils.split(code, SEP);
			if(cc== null || cc.length==0) continue;
			
			for (String c : cc) {
				Option opt = options.get(c);
				String name = c;
				if (opt != null) {
					name = opt.getName();
				}
				buf.append(sepNow).append(name);
				sepNow = SEP;
			}
		}
		return buf.toString();
	}

	public String getOptionExt(String code, String extName) {
		if (options != null) {
			Option opt = options.get(code);
			if (opt != null) {
				String val = opt.getExt(extName);
				return val==null?"":val;
			}
		}
		return "";
	}

	public String getOptionExt(int code, String extName) {
		return getOptionExt(String.valueOf(code), extName);
	}

	// setter & getter
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Option> getOptions() {
		return options;
	}
	public void setOptions(Map<String, Option> options) {
		this.options = options;
	}
}
