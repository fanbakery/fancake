package com.fancake.manage.service;

import com.fancake.manage.dto.FaqMasterDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.FaqMaster;
import com.fancake.manage.repository.FaqMasterRepository;

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
public class FaqMasterServiceImpl implements FaqMasterService{

    //매우 중요한 부분
    private final FaqMasterRepository repository; //반드시 final 로 선언

    @Override
    public FaqMasterDTO view(int fmid){
        Optional<FaqMaster> result=repository.findById(fmid);
        return result.isPresent() ? entityToDto(result.get()) :null ;
    }

    @Override
    public int register(FaqMasterDTO dto) {
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
    public PageResultDTO<FaqMasterDTO, FaqMaster> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("fmid").descending());
        Page<FaqMaster> result = repository.findAll( pageable );

        Function<FaqMaster,FaqMasterDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
