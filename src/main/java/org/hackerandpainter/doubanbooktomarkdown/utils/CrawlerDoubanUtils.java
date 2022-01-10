package org.hackerandpainter.doubanbooktomarkdown.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-09-25 19:39
 **/
public class CrawlerDoubanUtils {
    public static String getSearchUrl(String bookName) {
        return "https://www.douban.com/search?cat=1001&q=" + bookName;
    }

    /**
     * 根据书名获取搜索结果页dom
     *
     * @param url 请求路径
     * @return
     */
    public static Document getDom(String url) {
        try {
            //获取页面元素dom  其中.get()为发起get请求
            Document doc = Jsoup.connect(url).userAgent("user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36").get();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取具体某个书籍的跳转路径
     *
     * @param doc   页面dom
     * @param index 取书籍信息序号
     * @return
     */
    public static String getBookItemUrl(Document doc, int index) {
        //根据class来获取元素  .get为获取第几个dom
        Element element = doc.getElementsByClass("result").get(index);
        //.first()为获取第一个dom
        Element element1 = element.getElementsByClass("title").first();
        //.getElementsByTag()为根据标签来获取dom
        Element element2 = element1.getElementsByTag("a").first();
        //.attr()获取标签中某个属性的值
        return element2.attr("href");
    }


    public static String getUrl(String str) {
        String realUrl = "";
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.getResponseCode();
            realUrl = conn.getURL().toString();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return realUrl;
    }

    /**
     * 获取图书封面图片
     *
     * @param doc
     * @return
     */
    public static String getBookCoverImg(Document doc) {
        String bookCoverImg = "";
        try {
            Element element = doc.getElementById("mainpic");
            Element element1 = element.getElementsByTag("a").first();
            Element element2 = element1.getElementsByTag("img").first();
            return element2.attr("src");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookCoverImg;
    }

    /**
     * 获取图书名称
     *
     * @param doc
     * @return
     */
    public static String getBookAuhor(Document doc) {
        String bookAuthor = "";
        try {
            Element element = doc.getElementById("info");
            Element element1 = element.getElementsByTag("a").first();
            //.text()为获取dom中文本信息
            return element1.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookAuthor;
    }

    /**
     * 获取图书作者
     *
     * @param doc
     * @return
     */
    public static String getBookName(Document doc) {
        String bookAuthor = "";
        try {
            Elements h1 = doc.getElementsByTag("h1");
            Element element1 = h1.get(0).getElementsByTag("span").first();
            //.text()为获取dom中文本信息
            return element1.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookAuthor;
    }

    /**
     * 获取豆瓣评分
     *
     * @param doc
     * @return
     */
    public static String getDoubanScore(Document doc) {
        String doubanScore = "";
        try {
            Element element = doc.getElementsByClass("rating_num").first();
            return element.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doubanScore;
    }

    /**
     * 获取书籍简介
     *
     * @param doc
     * @return
     */
    public static String getBookIntroduction(Document doc) {
        String articleIntroduction = "";
        try {
            Element element = doc.getElementsByClass("intro").first();
            return element.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleIntroduction;
    }

    public static String getBookDetailsUrl(String bookName) {
        String url = getSearchUrl(bookName);
        Document doc = getDom(url);
        return getUrl(getBookItemUrl(doc, 0));
    }


    public static void main(String[] args) {
        //String url = getSearchUrl("明朝那些事");

        //String url = "https://book.douban.com/subject/30259509/";
        String url = "https://book.douban.com/subject/27056391/";
        Document doc = getDom(url);
        //System.out.println(getBookItemUrl(doc,0));

        // 书名
        String bookName = getBookName(doc);
        // 介绍
        String bookIntroduction = getBookIntroduction(doc);
        // 评分
        String doubanScore = getDoubanScore(doc);
        // 封面图
        String bookCoverImg = getBookCoverImg(doc);
        // 作者
        String bookAuhor = getBookAuhor(doc);

        System.out.println("### 《" + bookName + "》");
        System.out.println();
        System.out.println("> 作者: " + bookAuhor);
        System.out.println(">");
        System.out.println("> 介绍: \n>\n" + "> " + bookIntroduction);
        System.out.println(">\n> 豆瓣链接: \n" + "> " + url);
        System.out.println();
        System.out.println("![](" + bookCoverImg + ")");
        System.out.println();
        System.out.println("评分: **" + doubanScore + "**");
    }

    public String getInfo(String url) {
        //String url = getSearchUrl("明朝那些事");
        //String url = "https://book.douban.com/subject/27056391/";

        Document doc = getDom(url);

        // 书名
        String bookName = getBookName(doc);
        // 介绍
        String bookIntroduction = getBookIntroduction(doc);
        // 评分
        String doubanScore = getDoubanScore(doc);
        // 封面图
        String bookCoverImg = getBookCoverImg(doc);
        // 作者
        String bookAuhor = getBookAuhor(doc);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("### 《" + bookName + "》\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("> 作者: " + bookAuhor + "\n");
        stringBuilder.append(">\n");
        stringBuilder.append("> 介绍: \n>\n" + "> " + bookIntroduction + "\n");
        stringBuilder.append(">\n> 豆瓣链接: \n" + "> " + url + "\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("![](" + bookCoverImg + ")" + "\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("评分: **" + doubanScore + "**" + "\n");
        return stringBuilder.toString();
    }
}
