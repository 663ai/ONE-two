package com.lxl.demo.service;

import com.lxl.demo.VO.UserVO;
import com.lxl.demo.entities.User;

public interface UserService {
    public User regist(User user);
    public UserVO getUser(String username);
    public void saveUserToRedisByToken(UserVO userVO, String Token);
    public Object getUserFromRedisByToken(String token);
}
