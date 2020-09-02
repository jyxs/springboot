package com.ai2331.sys.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ai2331.common.entity.PageX;
import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Corp;
import com.ai2331.sys.vo.CorpSearchVO;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface CorpDAO {

	@Select("<script>"
			+ "SELECT * FROM sys_corp WHERE 1=1"
			+ "<if test='#{vo.name}!=null'> AND locate(#{vo.name},name)>0 </if>"
			+ "<if test='#{vo.code}!=null'> AND locate(#{vo.code},code)>0 </if>"
			+ "<if test='#{vo.status}!=null'> AND status = #{vo.status} </if>"
			+ " ORDER BY code"
			+ " limit #{pager.offSite} #{pager.pageSize}"
			+ "</script>")
	List<Corp> findCorps(@Param("vo") CorpSearchVO vo, @Param("pager") PageX pager);
	
	@Select("<script>"
			+ "SELECT count(0) FROM sys_corp WHERE 1=1"
			+ "<if test='#{vo.name}!=null'> AND locate(#{vo.name},name)>0 </if>"
			+ "<if test='#{vo.code}!=null'> AND locate(#{vo.code},code)>0 </if>"
			+ "<if test='#{vo.status}!=null'> AND status = #{vo.status} </if>"
			+ "</script>")
	long findCorpsCount(@Param("vo") CorpSearchVO vo);
	
	@Select("select * from sys_corp where code=#{0}")
	Corp findOne(String code);
	
	@Insert("INSERT INTO sys_corp ("
			+ "code,pcode,name,fullname,taxno,"
			+ "bank,bank_card_no,busi_scope,busi_type,"
			+ "phone,address,responsible,responsible_phone,status,"
			+ "suit_code,suit_code_extra,remark,create_time,"
			+ "creator,update_time,updator) VALUES("
			+ "#{code},#{pcode},#{name},#{fullname},#{taxno},"
			+ "#{bank},#{bankCardNo},#{busiScope},#{busiType},"
			+ "#{phone},#{address},#{responsible},#{responsiblePhone},#{status},"
			+ "#{suitCode},#{suitCodeExtra},#{remark},#{createTime},"
			+ "#{creator},#{updateTime},#{updator}"
			+ ")")
	int insert(@Param("corp") Corp corp);
	
	@Update("UPDATE sys_corp SET "
			+ "name=#{name},fullname=#{fullname},taxno=#{taxno},bank=#{bank},"
			+ "bank_card_no=#{bankCardNo},busi_scope=#{busiScope},busi_type=#{busiType},phone=#{phone},"
			+ "address=#{address},responsible=#{responsible},responsible_phone=#{responsiblePhone},status=#{status},"
			+ "suit_code=#{suitCode},suit_code_extra=#{suitCodeExtra},remark=#{remark},update_time=#{updateTime},updator=#{updator} "
			+ "WHERE code=#{code}")
	int update(@Param("corp") Corp corp);
	
	@Update("UPDATE sys_corp SET status=#{status},remark=#{remark},update_time=#{updateTime},updator=#{updator} WHERE code=#{code}")
	int updateStatus(@Param("code") String code, @Param("status") Integer status, @Param("remark") String remark, @Param("updateTime") Date updateTime,
			@Param("updator") Integer updator);
	
	@Update("UPDATE sys_corp SET suit_code=#{suitCode},suit_code_extra=#{suitCodeExtra},remark=#{remark},update_time=#{updateTime},updator=#{updator} WHERE code=#{code}")
	int updateSuit(@Param("code") String code, @Param("suitCode") String suitCode, @Param("suitCodeExtra") String suitCodeExtra, @Param("remark") String remark,
			@Param("updateTime") Date updateTime, @Param("updator") Integer updator);
	
	@Update("UPDATE sys_corp SET check_status=#{checkStatus},remark=#{remark},update_time=#{updateTime},updator=#{updator} WHERE code=#{code}")
	int updateCheckStatus(@Param("code") String code, @Param("checkStatus") Integer checkStatus, @Param("updateTime") Date updateTime, @Param("updator") Integer updator);
}
