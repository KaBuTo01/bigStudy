package com.example.forum.banner;

import com.example.forum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName DataBean
 * @Author name
 * @Date 2022/10/15
 * @Description
 */
public class DataBean {
public Integer imageRes;
public String imageUrl;
public String title;
public int viewType;

public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
        }

public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
        }

public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.a, "相信自己,你努力的样子真的很美", 1));
        list.add(new DataBean(R.drawable.b, "极致简约,梦幻小屋", 3));
        list.add(new DataBean(R.drawable.c, "超级卖梦人", 3));
        list.add(new DataBean(R.drawable.d, "夏季新搭配", 1));
        list.add(new DataBean(R.drawable.e, "绝美风格搭配", 1));
        return list;
        }

public static List<DataBean> getTestData2() {
       List<DataBean> list = new ArrayList<>();
//        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 3));
//        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
//        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 3));
//        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
//        list.add(new DataBean(R.drawable.image11, "生活的态度", 3));
        return list;
        }

/**
 * 仿淘宝商品详情第一个是视频
 * @return
 */
public static List<DataBean> getTestDataVideo() {
        List<DataBean> list = new ArrayList<>();
//        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
//        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 1));
//        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
//        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 1));
//        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
//        list.add(new DataBean(R.drawable.image11, "生活的态度", 1));
        return list;
        }

public static List<DataBean> getTestData3() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("https://img-u-4.51miz.com/preview/muban/00/00/29/01/M-290120-FEE2847D.jpg-1.jpg", "牛逼", 1));
        list.add(new DataBean("https://tse1-mm.cn.bing.net/th/id/OIP-C.Xf7-1gQZsRPxSwE4IlZw9wHaEK?pid=ImgDet&rs=1", "11111", 2));
        list.add(new DataBean("https://ts1.cn.mm.bing.net/th/id/R-C.09843d244a86523e485a7bb1481250a6?rik=o6akwCOkNA5IuQ&riu=http%3a%2f%2fwww.jzrb.com%2ftxy%2fupload%2f2016927144150.JPG&ehk=gZr5Yjjo4q3HHYwvRH33OqC9naMDyF3gIsx2HeE9RaQ%3d&risl=&pid=ImgRaw&r=0", "22222", 3));
        list.add(new DataBean("https://ts1.cn.mm.bing.net/th/id/R-C.2a13939f999e429d6317a2cb6e52d6ed?rik=OfEnAj9P9KGE2A&riu=http%3a%2f%2fwww.wenmizhiyou.net%2fuploads%2fueditor%2f20200706%2f1594042593895351.jpg&ehk=Wbf0sKJs4TPLnFCN4KbpZgmLnR36QWndkblJWH5Tj%2fM%3d&risl=&pid=ImgRaw&r=0", "33333", 4));
        list.add(new DataBean("https://ts1.cn.mm.bing.net/th/id/R-C.55b72e03fb71dc3a1d2407ccb29299da?rik=GnP0TTmx8WTN%2bA&riu=http%3a%2f%2fimg95.699pic.com%2fphoto%2f40162%2f1245.jpg_wh300.jpg!%2ffh%2f300%2fquality%2f90&ehk=lJxQGogHThs2XZgQWneKiLi3AprczJf4XuZ%2b7WJhyyk%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1", "44444", 5));
        return list;
        }

public static List<DataBean> getVideos() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4", null, 0));
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4", null, 0));
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4", null, 0));
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4", null, 0));
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4", null, 0));
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4", null, 0));
        return list;
        }


public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
        list.add(getRandColor());
        }
        return list;
        }

/**
 * 获取十六进制的颜色代码.例如  "#5A6677"
 * 分别取R、G、B的随机值，然后加起来即可
 *
 * @return String
 */
public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
        }
        }
