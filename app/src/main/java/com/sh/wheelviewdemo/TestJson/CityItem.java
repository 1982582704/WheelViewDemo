package com.sh.wheelviewdemo.TestJson;

import java.util.List;

/**
 * 作用：
 *
 * @author Sh
 * @Time 2016/9/14.
 */
public class CityItem {

    /**
     * number : 200
     * info : [{"zxs":"北京市","area":"朝阳区"},{"province":"河北省","city":"石家庄","area":"裕华区"}]
     */

    private int number;
    /**
     * zxs : 北京市
     * area : 朝阳区
     */

    private List<InfoBean> info;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String zxs;
        private String area;

        public String getZxs() {
            return zxs;
        }

        public void setZxs(String zxs) {
            this.zxs = zxs;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
