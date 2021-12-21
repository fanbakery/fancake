package com.fancake.manage.service;

import com.fancake.manage.dto.FaqDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Faq;
import com.fancake.manage.repository.FaqRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class FaqServiceImpl implements FaqService{

    //매우 중요한 부분
    private final FaqRepository repository; //반드시 final 로 선언

    @Override
    public FaqDTO view(int faid){
        Optional<Faq> result=repository.findById(faid);
        return result.isPresent() ? entityToDto(result.get()) :null ;
    }

    @Override
    public int register(FaqDTO dto) {
//
//        log.info("DTO-----------------------------");
//        log.info(dto);
//        Address entity = dtoToEntity(dto);
//        log.info(entity);
//        repository.save(entity);
//        return entity.getMbno();
        return 0;

    }

    @Override
    public PageResultDTO<FaqDTO, Faq> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("faid").descending());
        Page<Faq> result = repository.findAll( pageable );

        Function<Faq,FaqDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
