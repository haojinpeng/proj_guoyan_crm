package com.it.dao;



import com.it.bean.Expenses_apply;
import com.it.bean.PageBean;

import java.util.List;

public interface IExpenses_apply_DAO {
    //增
    boolean addExpenses_apply(Expenses_apply expenses_apply);
    //删
    boolean delExpenses_apply(Expenses_apply expenses_apply);
    //改
    boolean updExpenses_apply(Expenses_apply expenses_apply);
    //申请后审批状态修改
    boolean updExpenses_apply_Apply_status(Expenses_apply expenses_apply);
    //查
    List<Expenses_apply> queryAllExpenses_apply();
    //申请后给审核表赋值
    boolean submitExpenses_apply(Expenses_apply expenses_apply);
    //模糊查询
    List<Expenses_apply> queryAllLikeExpenses_apply(Expenses_apply expenses_apply);
    //带分页查询所有
    List<Expenses_apply> queryAllExpenses_applyAndLimit(PageBean pageBean, Expenses_apply expenses_apply);
    //查询总记录数
    int queryExpenses_applyCount(Expenses_apply expenses_apply);

}
