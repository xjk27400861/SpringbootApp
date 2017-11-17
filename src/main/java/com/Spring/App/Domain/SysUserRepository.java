package com.Spring.App.Domain;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.Spring.App.Model.Sys_User;
import org.springframework.data.domain.*;
/*
 * 参考资料http://blog.csdn.net/jackie_xiaonan/article/details/70187752
 * 注意，在调试的时候，按步骤走，删除缓存无效，但是执行程序的话，就可以，不清楚是什么缘由
 * 如果查询所有记录或者分页查询记录，建议存在别的value下，以便全部清空，删除缓存不能多个key同时删除
 * */

@CacheConfig(cacheNames = "Users")
public interface SysUserRepository extends JpaRepository<Sys_User, Long> {
	@Cacheable(key = "#p0")//
	Sys_User findByUsername(String name);

	@SuppressWarnings({"unchecked" })
    @CachePut(key = "#p0.username")//p0对应第一个参数
	@CacheEvict(key="'all'")//清除redis缓存
    Sys_User save(Sys_User user);
	
	@Cacheable(key="'all'")//key值如果是自己定义的字符串，需要加单引号'，value分片
	List<Sys_User> findAll();
    
	@CacheEvict(key="'all'")//清除redis缓存(value= {"List","Users"},allEntries=true)
	@Modifying
	@Query("delete from Sys_User where id=?1")
	@Transactional
	void delete(Long id);
	
	//@CacheEvict(key="#p0.username")
	@CacheEvict(key="'all'")
	//@Override
	void delete(Sys_User user);
	
	@Query(value = "SELECT * FROM sys_user WHERE user_name like %?1% and email like %?2% and cnname like %?3% ORDER BY ?#{#pageable}",countQuery = "SELECT count(*) FROM sys_user WHERE user_name like %?1% and email like %?2% and cnname like %?3%",nativeQuery = true)
	Page<Sys_User> selectAll(String username,String email,String cnname,String gender, Pageable pageable);
		
}
