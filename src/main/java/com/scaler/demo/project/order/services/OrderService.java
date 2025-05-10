package com.scaler.demo.project.order.services;

import com.scaler.demo.project.cartAndCheckout.models.Cart;
import com.scaler.demo.project.cartAndCheckout.service.CartItemService;
import com.scaler.demo.project.cartAndCheckout.service.CartService;
import com.scaler.demo.project.exceptions.ResourceNotFoundException;
import com.scaler.demo.project.order.dtos.OrderDto;
import com.scaler.demo.project.order.mapper.OrderModelMapper;
import com.scaler.demo.project.order.models.Order;
import com.scaler.demo.project.order.models.OrderItem;
import com.scaler.demo.project.order.models.OrderStatus;
import com.scaler.demo.project.order.repositories.OrderRepository;
import com.scaler.demo.project.product.models.Product;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final IProductRepository productRepository;
    private final CartService cartService;
    private final OrderModelMapper modelMapper;



    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order saveOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return saveOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }
    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getCartItems()
                .stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                   // productRepository.save(product);
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getUnitPrice());
                    return orderItem;
                })
                .toList();
    }
    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::convertToDto).toList();
    }

    @Override
    public OrderDto convertToDto(Order order) {
        return modelMapper.convertToDto(order);
    }
}
