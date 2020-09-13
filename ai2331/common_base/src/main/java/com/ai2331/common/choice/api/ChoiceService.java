package com.ai2331.common.choice.api;

import java.util.List;

import com.ai2331.common.choice.Choice;
import com.ai2331.common.choice.ChoiceConfig;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;

public interface ChoiceService {
	// ************** 应用接口 ******************
	/**
	 * 分页查询某选项实体的具体选项.
	 * @param choiceCode 选项实体代码
	 * @param q 查询参数
	 * @param extraKeys 扩展参数，逗号分隔的值
	 * @param px 分页
	 */
	GridX<List<Choice>> queryChoice(String choiceCode, String q, String extraKeys, PageX px);
	GridX<List<Choice>> queryChoice(String choiceCode, String q, PageX px);
	
	/**
	 * 获取多个具体选项.
	 * @param choiceCode 选项实体代码
	 * @param ids 选项Id
	 * @param keepOrder 结果是否按ids中顺序排列
	 * @param extraKeys 扩展参数，逗号分隔的值
	 */
	List<List<Choice>> listChoice(String choiceCode, String[] ids, boolean keepOrder, String extraKeys);
	
	/**
	 * 获取多个具体选项.
	 * 无额外参数.
	 */
	List<List<Choice>> listChoice(String choiceCode, String[] ids, boolean keepOrder);
	
	/**
	 * 获取多个具体选项.
	 * 结果不排序
	 */ 
	List<List<Choice>> listChoice(String choiceCode, String ...ids);
	
	/**
	 * 根据id获取name，支持逗号分隔的多选.
	 */
	String mapName(String choiceCode, String ids, String extraKeys);
	
	String mapName(String choiceCode, String ids);
	
	/**
	 * 取一个具体选项.
	 * @param choiceCode
	 * @param id
	 */
	List<Choice> getChoice(String choiceCode, String id);
	
	// ************** 配置接口 ******************
	/**
	 * 获取选项配置对象.
	 * @param choiceCode
	 * @return
	 */
	ChoiceConfig getChoiceConfig(String choiceCode);
	
	/**
	 * 保存配置，新增或更新.
	 * @param config 配置对象，choiceCode唯一
	 */
	void saveChoiceConfig(ChoiceConfig config);
	
	/**
	 * 删除配置.
	 * @param choiceCode 选项实体代码
	 */
	void deleteChoiceConfig(String choiceCode);
	
	/**
	 * 模糊查询配置列表.
	 * @param codeLike 选项实体代码匹配
	 * @param nameLike 选项实体名称匹配
	 * @return
	 */
	List<ChoiceConfig> listChoiceConfig(String codeLike, String nameLike);
}
