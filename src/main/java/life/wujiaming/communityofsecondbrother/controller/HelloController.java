package life.wujiaming.communityofsecondbrother.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    // Ctrl + P 提示需要传入的参数
    // Model 将网址的参数传入HTML页面
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "Wu JiaMing, head of China National Space Administration") String name, Model model) {
        // 浏览器传过来的值，放到model中
        model.addAttribute("name", name);
        // 去模板目录templates找HTML文件
        return "hello";
    }

}
