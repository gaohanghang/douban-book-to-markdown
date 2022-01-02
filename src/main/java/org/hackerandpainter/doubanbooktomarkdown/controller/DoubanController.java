package org.hackerandpainter.doubanbooktomarkdown.controller;

import org.hackerandpainter.doubanbooktomarkdown.utils.CrawlerDoubanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-09-25 19:29
 **/
@RestController
public class DoubanController {

    @GetMapping("/douban")
    public String getInfo(String url) {
        CrawlerDoubanUtils crawlerDoubanUtils = new CrawlerDoubanUtils();
        return crawlerDoubanUtils.getInfo(url);
    }

}
