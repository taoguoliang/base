package cn.taoguoliang.base.demo;

import cn.taoguoliang.base.controller.BaseController;
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
public class TestController extends BaseController<Test, String, Test, Test> {

    @Override
    protected Class getEntityCls() {
        return null;
    }

    @Override
    protected Class getVoCls() {
        return null;
    }

}
