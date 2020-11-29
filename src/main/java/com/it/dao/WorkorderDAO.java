package com.it.dao;

import com.it.bean.PageBean;
import com.it.bean.Wvo;

import java.util.List;

public interface WorkorderDAO {
    //查找所有
    public List<Wvo> queryall(String str);

    public List<Wvo> queryall(PageBean pageBean,Wvo wvo);

    //模糊查找
    public List<Wvo> queryalllike(PageBean pageBean, Wvo wvo,String startTime,String endTime);
    public List<Wvo> queryallpj(PageBean pageBean, Wvo wvo,String startTime,String endTime);

    public List<Wvo> findprojname();
    public List<Wvo> findusertname();
    public List<Wvo> findcustomername();

    //添加
    public boolean add(Wvo wvo);

    //chuli
    public boolean update(Wvo wvo);
    public boolean updatepj(Wvo wvo);

    //页数
    int count(Wvo wvo);
    int count2(Wvo wvo);
    int count3(Wvo wvo);
}
