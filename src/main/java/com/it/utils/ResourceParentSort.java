package com.it.utils;

import com.it.bean.ResourceParent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceParentSort<T extends ResourceParent> {

    public List<T> sortList(List<T> resourceParent){
        resourceParent.stream().forEach(value->{
            if (value.getType().equals("C")){
                String[] paths=value.getPath().split(",");
                for (String path:paths
                     ) {
                    resourceParent.stream().forEach(value1->{
                        if (path.equals(value1.getName())){
                            if(value.getChild() == null){
                                value.setChild(new ArrayList<>());
                            }
                            value.getChild().add(value1);
                        }
                    });
                }
            }
        });

        Iterator<T> iterator = resourceParent.iterator();
        while(iterator.hasNext()){
            T t = iterator.next();
            if(t.getType().equals("M")) {
                iterator.remove();
            }
            if (t.getType().equals("C")){
                t.setPath("");
            }
        }

        return  resourceParent;
    }


}