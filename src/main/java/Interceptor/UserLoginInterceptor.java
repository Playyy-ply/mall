package Interceptor;

import com.dasanxia.mall.consts.MallConst;
import com.dasanxia.mall.exception.UserLoginException;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.dasanxia.mall.enums.ResponseEnum.NEED_LOGIN;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    //true表示继续，false表示中断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        User user = (User)request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(user == null){
            log.info("user=null");
            throw new UserLoginException();

//            response.getWriter().print("login,please--preHandle");
//            return false;
            //return ResponseVo.error(NEED_LOGIN);
        }
        return true;
    }
}
