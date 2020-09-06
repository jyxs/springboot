package com.ai2331.common.dict.api;

import java.util.Collection;
import java.util.List;

import com.ai2331.common.dict.Groups;
import com.ai2331.common.dict.Option;
import com.ai2331.common.dict.OptionGroup;

/**
 * LookupService的组合.
 */
public class LookupServices implements LookupService {
	private List<LookupService> services;

	@Override
	public String getLookupType(String group) {
		for (LookupService s : services) {
			if (s.containsGroup(group))
				return s.getLookupType(group);
		}
		return null;
	}

	@Override
	public void init() {
		for (LookupService s : services) {
			s.init();
		}
	}

	@Override
	public void reload() {
		for (LookupService s : services) {
			s.reload();
		}
	}

	@Override
	public void reload(String group) {
		for (LookupService s : services) {
			if (s.containsGroup(group))
				s.reload(group);
		}
	}

	@Override
	public boolean containsGroup(String group) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Groups listGroup() {
		if(services.size()==1) {
			return services.get(0).listGroup();
		} else {
			Groups gs = new Groups();
			for (LookupService s : services) {
				gs.addGroups(s.listGroup());
			}
			return gs;
		}
	}

	@Override
	public OptionGroup getOptionGroup(String group) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOptionGroup(group);
			}
		}
		return null;
	}

	@Override
	public Collection<Option> getOptions(String group) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOptions(group);
			}
		}
		return null;
	}

	@Override
	public Option getOption(String group, String code) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOption(group, code);
			}
		}
		return null;
	}

	@Override
	public Option getOption(String group, Integer code) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOption(group, code);
			}
		}
		return null;
	}
	
	@Override
	public String getName(String group, String code) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getName(group, code);
			}
		}
		return "";
	}

	@Override
	public String getName(String group, Integer code) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getName(group, code);
			}
		}
		return "";
	}

	@Override
	public String getCode(String group, String name) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getCode(group, name);
			}
		}
		return "";
	}

	@Override
	public String getMultiName(String group, String... codes) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getMultiName(group, codes);
			}
		}
		return "";
	}

	@Override
	public String getOptionExt(String group, String code, String extName) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOptionExt(group, code, extName);
			}
		}
		return "";
	}

	@Override
	public String getOptionExt(String group, Integer code, String extName) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getOptionExt(group, code, extName);
			}
		}
		return "";
	}
	
	@Override
	public List<Option> getMultiOption(String group, String... code) {
		for (LookupService s : services) {
			if (s.containsGroup(group)) {
				return s.getMultiOption(group, code);
			}
		}
		return null;
	}

	//getter & setter
	public List<LookupService> getServices() {
		return services;
	}

	public void setServices(List<LookupService> services) {
		this.services = services;
	}

}
