package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.SeenDTO;
import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Seen;
import com.fancake.manage.repository.AddressRepository;
import com.fancake.manage.repository.SeenRepository;
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
public class SeenServiceImpl implements SeenService{

    //매우 중요한 부분
    private final SeenRepository repository; //반드시 final 로 선언

    @Override
    public SeenDTO view(int mbno){
        Optional<Seen> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(SeenDTO dto) {
//
//        log.info("DTO-----------------------------");
//        log.info(dto);
//
//        Address entity = dtoToEntity(dto);
//        log.info(entity);
//        repository.save(entity);
//
//        return entity.getMbno();
        return 0;

    }

    @Override
    public PageResultDTO<SeenDTO, Seen> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("seenseq").descending());
        Page<Seen> result = repository.findAll( pageable );

        Function<Seen,SeenDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
