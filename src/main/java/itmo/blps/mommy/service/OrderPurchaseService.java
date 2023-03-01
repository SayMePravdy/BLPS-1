package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.OrderPurchaseDto;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.entity.UserPurchase;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.UserPurchaseMapper;
import itmo.blps.mommy.repository.UserPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderPurchaseService {
    private UserPurchaseRepository userPurchaseRepository;
    private UserPurchaseMapper userPurchaseMapper;
    private PurchaseCountService purchaseCountService;
    private UserService userService;


    public OrderPurchaseDto addOrderPurchase(OrderPurchaseDto orderPurchaseDto) throws Exception {
        UserPurchase userPurchase = new UserPurchase(orderPurchaseDto.getUserId(), orderPurchaseDto.getPurchaseId());
        userPurchase.setDateCreated(Instant.now());
        userPurchase.setProductsCount(orderPurchaseDto.getCountOfProducts());
        UserPurchase db = userPurchaseRepository.save(userPurchase);
        purchaseCountService.countPurchasedProducts(db);
        return userPurchaseMapper.toDto(db);
    }

    public OrderPurchaseDto getOrderPurchase(Integer purchaseId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userPurchaseMapper.toDto(userPurchaseRepository.findById_UserIdAndId_PurchaseId(user.getId(), purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность выкупа не найдена")));
    }

    public OrderPurchaseDto deleteOrderPurchase(Integer purchaseId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userPurchaseMapper.toDto(userPurchaseRepository.deleteById_UserIdAndId_PurchaseId(user.getId(), purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность выкупа не найдена")));
    }

    public Page<UserPurchase> getPagedOrderPurchase(int page, int pageSize) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName);
        return userPurchaseRepository.findAllById_UserId(user.getId(), PageRequest.of(page, pageSize));
    }
}
