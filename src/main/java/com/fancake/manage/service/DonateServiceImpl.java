package com.fancake.manage.service;

import com.fancake.manage.dto.DonateDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Donate;
import com.fancake.manage.repository.DonateRepository;

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
public class DonateServiceImpl implements DonateService{

    //매우 중요한 부분
    private final DonateRepository repository; //반드시 final 로 선언

    @Override
    public DonateDTO view(int donateseq){
        Optional<Donate> result=repository.findById(donateseq);
        return result.isPresent() ? entityToDto(result.get()) :null ;
    }

    @Override
    public int register(DonateDTO dto) {
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
    public PageResultDTO<DonateDTO, Donate> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("donateseq").descending());
        Page<Donate> result = repository.findAll( pageable );

        Function<Donate,DonateDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
