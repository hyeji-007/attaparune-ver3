package com.green.attaparunever2.order;

import com.green.attaparunever2.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    long postOrder(OrderPostReq p);
    int postOrderDetail(OrderDetailPostReq p);
    int updOrderAccess(OrderAccessPatchReq p);
    GetOrderRes getOrder(OrderGetReq p);
    List<GetOrderDto> getOrderList(OrderGetReq p);
    List<RestaurantOrderDto> selReservationOrderListByRestaurantId(long restaurantId);
    List<RestaurantOrderDto> selCompleteOrderListByRestaurantId(long restaurantId);
    List<OrderDetailDto> selOrderDetailByOrderId(long orderId);

    List<OrderDetailPostReq> getTotalPrice(long orderId);
    OrderSelDto selOrderByOrderId(long orderId);
    long insReservationOrder(OrderPostReq p);
}