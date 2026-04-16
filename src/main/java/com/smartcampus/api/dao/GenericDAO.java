/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.dao;

import com.smartcampus.api.model.BaseModel;
import java.util.List;

/**
 *
 * @author acer
 */
public class GenericDAO<T extends BaseModel> {
    private final List<T> items;
    
    public GenericDAO(List<T> items){
        this.items=items;
    }
    
    public List<T> getAll(){
        return items;
    }
    
    public T getById(String id){
        for(T item: items){
            if(item.getId().equals(id) ){
                return item;
            }
        }
        return null;
    }
    
//    public void add(T item){
//        int maxId=0;
//        for(T i:items){
//            if(i.getId()>maxId){
//                maxId=i.getId();
//            }
//        }
//        item.setId(maxId+1);
//        items.add(item);
//    }
    
    public void update(T updateItem){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId().equals(updateItem.getId())){
                items.set(i, updateItem);
                return;
            }
        }
    }
    
    public void delete(String id){
        items.removeIf(item -> item.getId().equals(id));
    }
    
}
