package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.OrderPurchaseDto;
import itmo.blps.mommy.entity.UserPurchase;
import itmo.blps.mommy.exception.EntityNotFoundException;
import itmo.blps.mommy.mapper.UserPurchaseMapper;
import itmo.blps.mommy.repository.UserPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderPurchaseService {
    private UserPurchaseRepository userPurchaseRepository;
    private UserPurchaseMapper userPurchaseMapper;
    private PurchaseCountService purchaseCountService;


    public OrderPurchaseDto addOrderPurchase(OrderPurchaseDto orderPurchaseDto) throws Exception {
        UserPurchase userPurchase = new UserPurchase(orderPurchaseDto.getUserId(), orderPurchaseDto.getPurchaseId());
        userPurchase.setDateCreated(Instant.now());
        userPurchase.setProductsCount(orderPurchaseDto.getCountOfProducts());
        UserPurchase db = userPurchaseRepository.save(userPurchase);
        purchaseCountService.countPurchasedProducts(db);
        return userPurchaseMapper.toDto(db);
    }

    public OrderPurchaseDto getOrderPurchase(Integer userId, Integer purchaseId) {
        return userPurchaseMapper.toDto(userPurchaseRepository.findById_UserIdAndId_PurchaseId(userId, purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность выкупа не найдена")));
    }

    public OrderPurchaseDto deleteOrderPurchase(Integer userId, Integer purchaseId) {
        return userPurchaseMapper.toDto(userPurchaseRepository.deleteById_UserIdAndId_PurchaseId(userId, purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Сущность выкупа не найдена")));
    }

    public Page<UserPurchase> getPagedOrderPurchase(Integer userId, int page, int pageSize) {
        return userPurchaseRepository.findAllById_UserId(userId, PageRequest.of(page, pageSize));
    }
}
