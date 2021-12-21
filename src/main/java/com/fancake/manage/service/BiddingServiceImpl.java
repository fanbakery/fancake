package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.BiddingDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Bidding;
import com.fancake.manage.repository.AddressRepository;
import com.fancake.manage.repository.BiddingRepository;
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
public class BiddingServiceImpl implements BiddingService{

    //매우 중요한 부분
    private final BiddingRepository repository; //반드시 final 로 선언

    @Override
    public BiddingDTO view(int mbno){
        Optional<Bidding> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;
    }

    @Override
    public int register(BiddingDTO dto) {
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
    public PageResultDTO<BiddingDTO, Bidding> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("biddingseq").descending());
        Page<Bidding> result = repository.findAll( pageable );

        Function<Bidding,BiddingDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
