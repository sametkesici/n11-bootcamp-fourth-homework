package com.fourthhomework.n11bootcamp.collection;

import com.fourthhomework.n11bootcamp.collection.dto.CollectionDto;
import com.fourthhomework.n11bootcamp.collection.dto.CreateCollectionDto;
import com.fourthhomework.n11bootcamp.mapper.CollectionMapper;
import com.fourthhomework.n11bootcamp.mapper.CreateCollectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collection")
public class CollectionController {

    private final CreateCollectionMapper createCollectionMapper;

    private final CollectionMapper collectionMapper;

    private final CollectionService collectionService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> makeCollection(@RequestBody CreateCollectionDto createCollectionDto) {

        var createdCollection = collectionService.makeCollection(createCollectionMapper.toEntity(createCollectionDto));

        if( createdCollection != null){
            return ResponseEntity.ok(collectionMapper.toDto(createdCollection));
        }
        return ResponseEntity.badRequest().body("Bu borc tahsilat yapmaya uygun degildir");
    }

    @GetMapping
    public ResponseEntity<?> retrieveCollectionsByCreatedDate(@RequestParam Date startedDate, @RequestParam Date endDate){
        return ResponseEntity.ok(collectionMapper.toDto(collectionService.retrieveCollectionsByCreatedAt(startedDate,endDate)));
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<?> retrieveCollectionsByUserId(@PathVariable  Long userId){
        return ResponseEntity.ok(collectionMapper.toDto(collectionService.retrieveCollectionsByUserId(userId)));
    }

    @GetMapping("/late-fee/{userId}")
    public ResponseEntity<?> getLateFeeByUser(@PathVariable Long userId){
        return ResponseEntity.ok(collectionService.getLateFeeAmountByUser(userId));
    }

}
