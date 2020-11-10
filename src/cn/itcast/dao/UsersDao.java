package cn.itcast.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.domain.Users;
import cn.itcast.util.JDBCUtils;

public class UsersDao {

	private JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDs());
/**
 * 登錄方法
 * @param loginUser
 * @return
 */
	public Users login(Users loginUser) {
		
		try {
			String sql="select * from BPMHCP.dbo.USERS where username=? and password=?";
			
			Users user=template.queryForObject(sql, new BeanPropertyRowMapper<Users>(Users.class),
					loginUser.getUsername(),loginUser.getPassword());
			
			return user;
		}
		 catch (DataAccessException e) {
			// TODO: handle exception
			 e.printStackTrace();
			 return null;
		}
		}

}
