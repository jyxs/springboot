package com.ai2331.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.ai2331.sys.entity.dto.Menu;
import com.ai2331.sys.entity.dto.ResourceTree;

@Service
public class ResourceMenuServiceImpl extends ResourceMenuServiceAbstract {

	@Override
	public List<Menu> getMenus(List<ResourceTree> rts) {
		if (ObjectUtils.isEmpty(rts)) {
			return Collections.emptyList();
		}
		List<Menu> menus = new ArrayList<Menu>();
		for (ResourceTree rt : rts) {
			menus.add(createMenu(rt));
		}
		return menus;
	}

	private Menu createMenu(ResourceTree rt) {
		Menu menu = new Menu();

		menu.setPath(rt.getPrivCode());
		menu.setName(rt.getName());
		menu.createMeta(rt.getName(), rt.getIcon());
		List<Menu> children = new ArrayList<Menu>();
		if (null != rt.getChildren() && rt.getChildren().size() > 0) {
			menu.setRedirect(rt.getUrl());
			for (ResourceTree c : rt.getChildren()) {
				children.add(createMenu(c));
			}
			menu.setChildren(children);
		} else {
			menu.setUri(rt.getUrl());
			if (rt.getPid().equals(0)) {
				// 只有顶级菜单时，将uri置空，redirect设置为url
				menu.setUri(null);
				menu.setRedirect(rt.getUrl());
				Menu child = new Menu();
				child.setPath(rt.getPrivCode());
				child.setUri(rt.getUrl());
				child.setName(rt.getName());
				child.createMeta(rt.getName(), rt.getIcon());
				children.add(child);
				menu.setChildren(children);
			}
		}

		return menu;
	}
}
