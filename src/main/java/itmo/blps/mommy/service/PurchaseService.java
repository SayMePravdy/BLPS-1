package itmo.blps.mommy.service;

import itmo.blps.mommy.dto.PurchaseRequestDTO;
import itmo.blps.mommy.dto.PurchaseResponseDTO;
import itmo.blps.mommy.entity.Purchase;
import itmo.blps.mommy.entity.PurchaseStatus;
import itmo.blps.mommy.exception.EntityNotFoundException;
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

    public List<PurchaseResponseDTO> suggestPurchases(String name, int page, int perPage) {
        List<Purchase> purchases = purchaseRepository.findPurchases(
                name.strip(), perPage, (page - 1) * perPage, PurchaseStatus.CREATED.name()
        );
        return purchases
                .stream()
                .map(purchaseMapper::toDto)
                .collect(Collectors.toList());
    }

    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        Purchase purchase = purchaseMapper.fromDto(purchaseRequestDTO);
        return purchaseMapper.toDto(purchaseRepository.save(purchase));
    }

    public PurchaseResponseDTO getPurchase(Integer purchaseId) {
        return purchaseMapper.toDto(findPurchase(purchaseId));
    }

    public Purchase findPurchase(Integer purchaseId) {
        return purchaseRepository
                .findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Unknown purchase: " + purchaseId));
    }

    public void deletePurchase(Integer purchaseId) {
        Purchase purchase = findPurchase(purchaseId);

        if (purchase.getPurchaseStatus() == PurchaseStatus.PAID) {
            throw new RuntimeException("You can't delete paid purchase!");
        }
        purchaseRepository.deletePurchase(purchaseId);
    }
}
