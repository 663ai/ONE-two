package com.lxl.demo.service.impl;

import com.lxl.demo.VO.UserVO;
import com.lxl.demo.entities.User;
import com.lxl.demo.mapper.UserMapper;
import com.lxl.demo.redis.UserRedis;
import com.lxl.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRedis userRedis;

    public User regist(User user){
        return userMapper.saveAndFlush(user);
    }

//    public User getUser1(String username){
//        return userMapper.getOne(username);
//    }

    public UserVO getUser(String username){
        UserVO userVO = new UserVO();
        User user = userRedis.get(username);
        if (user == null){
            user = userMapper.findByUsername(username);
            if (user != null){
                userRedis.put(user.getUsername(), user, -1);
            }else {
                return null;
            }
        }
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public void saveUserToRedisByToken(UserVO userVO,String Token){
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userRedis.put(Token, user, 3600);
    }

    @Override
    public Object getUserFromRedisByToken(String token) {
        return userRedis.get(token);
    }
}
