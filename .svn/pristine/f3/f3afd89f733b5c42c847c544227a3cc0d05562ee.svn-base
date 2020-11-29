package com.it.dao;


import com.it.bean.Expenses_approval;
import com.it.bean.PageBean;

import java.util.List;

public interface IExpenses_approval_DAO {
    //增
    boolean addExpenses_approval(Expenses_approval expenses_approval);
    //改
    boolean updExpenses_approval(Expenses_approval expenses_approval);
    //非最高级审核人驳回后，改此申请状态
    boolean updExpenses_apply_Status(Expenses_approval expenses_approval);
    //判断是否为最终审核人
    int queryParent_id(Expenses_approval expenses_approval);
    //最终审核人的审核结果，结果直接返回给审核表中
    boolean updExpenses_apply_Status_Boos(Expenses_approval expenses_approval);
    //模糊查询
    List<Expenses_approval> queryAllLikeExpenses_approval(Expenses_approval expenses_approval);
    //带分页查询所有
    List<Expenses_approval> queryAllExpenses_approvalAndLimit(PageBean pageBean, Expenses_approval expenses_approval);
    //查询总记录数
    int queryExpenses_approvalCount(Expenses_approval expenses_approval);
}
