package cn.linz.base.demo;

import cn.linz.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author taogl
 * @date 2021/12/12 01:03
 */
@RestController
@RequestMapping("api")
@Api(tags = "测试")
public class TestController extends BaseController<Test, String, Test, Test, Test> {

}
