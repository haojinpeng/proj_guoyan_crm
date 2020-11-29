package com.it.utils;

import com.it.bean.ParentAndChildren;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParentAndChildrenSort<T extends ParentAndChildren> {
    public List<T> sortList(List<T> parentAndChildren){

        parentAndChildren.stream().forEach(value->{
            parentAndChildren.stream().forEach(value1->{
                if(value.getId() == value1.getPid()){
                    if(value.getChild() == null)
                        value.setChild(new ArrayList<>());
                    value.getChild().add(value1);
                }
            });
        });

        Iterator<T> iterator = parentAndChildren.iterator();
        while(iterator.hasNext()){
            T t = iterator.next();
            if(t.getPid() != 0) {
                iterator.remove();
            }
        }

        return  parentAndChildren;
    }

//    public List<T> sortList(List<T> parentAdnChildren){
//        for(int i = 0; i < parentAdnChildren.size(); i++){
//            Iterator<T> iterator = parentAdnChildren.iterator();
//            T t1 = parentAdnChildren.get(i);
//            while(iterator.hasNext()){
//                T t2 = iterator.next();
//                if(t1.getId() == t2.getPid()){
//                    if(t1.getChild() == null){
//                        List children  = new ArrayList();
//                        t1.setChild(children);
//                        children.add(t2);
//                    }else{
//                        t1.getChild().add(t2);
//                    }
//                    iterator.remove();
//                }
//            }
//        }
//        Iterator<T> iterator = parentAdnChildren.iterator();
//        while(iterator.hasNext()){
//            T t = iterator.next();
//            if(t.getPid() != 0){
//                iterator.remove();
//            }
//        }
//        return parentAdnChildren;
//    }
}
