package com.fancake.manage.service;

import com.fancake.manage.dto.HpAuthDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.HpAuth;
import com.fancake.manage.repository.HpAuthRepository;

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
public class HpAuthServiceImpl implements HpAuthService{

    //매우 중요한 부분
    private final HpAuthRepository repository; //반드시 final 로 선언

    @Override
    public HpAuthDTO view(int phone){
        Optional<HpAuth> result=repository.findById(phone);
        return result.isPresent() ? entityToDto(result.get()) :null ;
    }

    @Override
    public int register(HpAuthDTO dto) {
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
    public PageResultDTO<HpAuthDTO, HpAuth> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("phone").descending());
        Page<HpAuth> result = repository.findAll( pageable );

        Function<HpAuth,HpAuthDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
