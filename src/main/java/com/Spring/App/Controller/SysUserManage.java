package com.Spring.App.Controller;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Spring.App.Core.DataVO;
import com.Spring.App.Model.Sys_User;
import com.Spring.App.Service.SysUserBllService;

import javax.servlet.http.*;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@EnableAutoConfiguration
//@ComponentScan
public class SysUserManage {
	@Autowired
	private SysUserBllService sysuserbll;
	
	@Autowired  
	HttpServletRequest request; //这里可以获取到request
	
	@RequestMapping("/UserManage")
	public ModelAndView Index(ModelMap map)
	{
		//return "/SysUser/index";
		
		map.addAttribute("host", "http://blog.didispace.com");
		ModelAndView mv = new ModelAndView("/SysUser/index");
        
        return mv;
	}
	
	@RequestMapping("/getuserList")
	@ResponseBody
	public List<Sys_User> getList() throws Exception
	{
		return sysuserbll.getlist();
	}
	
	@RequestMapping("/getuserbypage")
	@ResponseBody
	public DataVO<Sys_User> getuserbypage(@RequestParam String param)
	{
		//JSONArray jsonarray = JSONArray.fromObject(param);
		String seCho = request.getParameter("sEcho");//请求次数
		//String sEcho = null;
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始索引
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
		int index=iDisplayStart/iDisplayLength;
		
		JSONObject queryParam = JSONObject.fromObject(param);//查询条件
		String username=queryParam.optString("username");
		String email=queryParam.optString("email");
		String cnname=queryParam.optString("cnname");
		String gender=queryParam.optString("gender");
				
		Page<Sys_User> page = sysuserbll.getpageList(index,iDisplayLength,username,email,cnname,gender);//sysuserbll.getlist();
		List<Sys_User> list=page.getContent();
		DataVO<Sys_User> result = new DataVO<Sys_User>();
		result.setdraw(Integer.parseInt(seCho == null ? "0"
                : seCho) + 1);
		result.setRecordsTotal((int)page.getTotalElements());
		result.setRecordsFiltered((int)page.getTotalElements());
		result.setData(list);
		return result;
	}
}
