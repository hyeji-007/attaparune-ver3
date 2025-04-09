package com.green.attaparunever2.admin.restaurant;

import com.green.attaparunever2.admin.restaurant.model.SelBlackListReq;
import com.green.attaparunever2.admin.restaurant.model.SelBlackListRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRestaurantMapper {
    List<SelBlackListRes> selBlackList(SelBlackListReq p);

    int selBlackListCount(long restaurantId, long userId);
}
