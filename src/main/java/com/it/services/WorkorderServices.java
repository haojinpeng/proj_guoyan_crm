package com.it.services;

import com.it.bean.PageBean;
import com.it.bean.Wvo;

import java.util.List;

public interface WorkorderServices {

    //��������
    public List<Wvo> queryall(String str);
    public List<Wvo> queryall(PageBean pageBean,Wvo wvo);

    //ģ������

    public List<Wvo> queryalllike(PageBean pageBean, Wvo wvo,String startTime,String endTime);

    public List<Wvo> queryallpj(PageBean pageBean, Wvo wvo,String startTime,String endTime);
    public List<Wvo> findprojname();
    public List<Wvo> findusertname();
    public List<Wvo> findcustomername();
    int count(Wvo wvo);
    public boolean update(Wvo wvo);
    public boolean updatepj(Wvo wvo);
    public boolean add(Wvo wvo);
    int count2(Wvo wvo);
    int count3(Wvo wvo);

}
