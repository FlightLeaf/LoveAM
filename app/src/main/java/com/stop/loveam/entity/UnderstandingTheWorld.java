package com.stop.loveam.entity;

import java.io.Serializable;
import java.util.List;

//{
//  "title": "在这里每天60秒读懂世界",
//  "banner": "https://picx.zhimg.com/v2-53faea643761bb3a473844b87833d3e4_720w.jpg?source=d16d100b",
//  "time": "05月31日",
//  "data": [
//    "三部门：对航空航天结构件及发动机制造相关装备等实施出口管制。",
//    "国铁集团：6月15日零时起实行新列车运行图，动车折扣票价最低到4折。",
//    "工信部：拟新增电动自行车用充电器不得设计、制造及使用车载形式。",
//    "最高法：对情节恶劣的未成年人犯罪绝不纵容，发生欺凌学校未尽职责将依法担责。",
//    "海关总署：即日起，允许符合相关要求的俄罗斯牛肉进口。",
//    "近日，农行、交通银行等相继发公告，加大无卡存取款业务限制。",
//    "2024新一线城市魅力排行榜发布：成都、杭州、重庆居前三名。",
//    "上海正式启动失业保险稳岗返还工作，预计60多万户用人单位受益。",
//    "江苏淮安：中考复读今年减10分录取，明年减20分。",
//    "浙江将17种抗癌药纳入医保支付，每年为群众减负11亿元。",
//    "祝贺！我国成功发射巴基斯坦多任务通信卫星。",
//    "全球企业研发投入前十榜单公布：亚马逊以852亿美元排名第一，华为第七。",
//    "韩国总统宣布将在航天领域投入100万亿韩元，计划于2032年登陆月球、2045年登陆火星。",
//    "英国议会正式解散，首相宣布7月4日举行大选，新议会将在大选后组成。",
//    "土耳其总统称联合国精神在加沙已死：美国手上沾满鲜血，欧洲也是帮凶。",
//    "【微语】风轻扬夏未央，林荫路单车响，原来所谓爱情是这模样，早安！"
//  ]
//}

public class UnderstandingTheWorld implements Serializable {
    private String title;
    private String banner;
    private String time;
    private List<String> data;

    public UnderstandingTheWorld() {
    }

    public UnderstandingTheWorld(String title, String banner, String time, List<String> data) {
        this.title = title;
        this.banner = banner;
        this.time = time;
        this.data = data;
    }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getBanner() { return banner; }
    public void setBanner(String value) { this.banner = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public List<String> getData() { return data; }
    public void setData(List<String> value) { this.data = value; }
}
