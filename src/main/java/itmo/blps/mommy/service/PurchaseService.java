package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.PurchaseRequestDTO;
import itmo.blps.mommy.dto.PurchaseResponseDTO;
import itmo.blps.mommy.entity.Purchase;
import itmo.blps.mommy.mapper.PurchaseMapper;
import itmo.blps.mommy.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    public List<PurchaseResponseDTO> suggestPurchases(String name, Integer page, Integer perPage) {
        List<Purchase> purchases = purchaseRepository.findPurchases(name, perPage, (page - 1) * perPage);
        return purchases
                .stream()
                .map(purchaseMapper::toDto)
                .collect(Collectors.toList());
    }

    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        Purchase purchase = purchaseMapper.fromDto(purchaseRequestDTO);
        return purchaseMapper.toDto(purchaseRepository.save(purchase));
    }
}
