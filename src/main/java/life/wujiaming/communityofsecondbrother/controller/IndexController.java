package life.wujiaming.communityofsecondbrother.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 20/07/2023 15:09
 */
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
