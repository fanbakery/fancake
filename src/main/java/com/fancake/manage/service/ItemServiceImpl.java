package com.fancake.manage.service;

import com.fancake.manage.dto.ItemDTO;
import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Item;
import com.fancake.manage.entity.Member;
import com.fancake.manage.repository.ItemRepository;
import com.fancake.manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class ItemServiceImpl implements ItemService{

    //매우 중요한 부분
    private final ItemRepository repository; //반드시 final 로 선언

    @Override
    public ItemDTO view(int itemseq){
        Optional<Item> result=repository.findById(itemseq);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(ItemDTO dto) {

        log.info("DTO-----------------------------");
        log.info(dto);

        Item entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);

        return entity.getItemseq();
    }

    @Override
    public PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("itemseq").descending());
        Page<Item> result = repository.findAll( pageable );

        Function<Item,ItemDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
