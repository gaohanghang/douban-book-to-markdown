package org.hackerandpainter.doubanbooktomarkdown.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.hackerandpainter.doubanbooktomarkdown.utils.CrawlerDoubanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-09-25 19:29
 **/
@RestController
@Api("豆瓣信息")
public class DoubanController {

    @GetMapping("/douban")
    public String getInfo(String url) {
        CrawlerDoubanUtils crawlerDoubanUtils = new CrawlerDoubanUtils();
        return crawlerDoubanUtils.getInfo(url);
    }

}
