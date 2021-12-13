package cn.linz.base.demo1;

import cn.linz.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author taogl
 * @date 2021/12/12 01:03
 */
@RestController
@RequestMapping("api1")
public class TestController1 extends BaseController<Test1, String, Test1, Test1> {

}
