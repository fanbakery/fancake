package com.fancake.manage.repository;

import com.fancake.manage.entity.Member;
import com.fancake.manage.entity.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertDummies(){

//        IntStream.rangeClosed(1,10).forEach(i->{
//
//            Member member = Member.builder()
//                    .mbname("mb_name"+i)
//                    .mbnick("mb_nick"+i)
//                    .mbemail("mb_email"+(i%10))
//                    .build();
//
//            System.out.println( memberRepository.save(member));
//
//        });
    }

//    @Test
//    public void updateTest(){
//
//        Optional<Member> result=memberRepository.findById(10L);
//        if(result.isPresent()){
//            Member member=result.get();
//            member.changeTitle("Changed Title===");
//            member.changeContent("Changed Content---");
//            memberRepository.save(member);
//        }
//
//    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("mbno").descending());

        QMember qMember   = QMember.member;
        String keyword    = "1";

        BooleanBuilder builder       = new BooleanBuilder();
        BooleanExpression expression = qMember.mbname.contains(keyword);
        builder.and(expression);

        Page<Member>   result  =  memberRepository.findAll( builder, pageable); // 5

        result.stream().forEach(member->{
             System.out.println(member);
        });

    }

//    @Test
//    public void testQuery2(){
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("mb_no").descending());
//        //Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
//
//        QMember qMember   = QMember.member;
//        String keyword    = "1";
//
//        BooleanBuilder builder       = new BooleanBuilder();
//        BooleanExpression exTitle   = qMember.mb_name.contains(keyword);
//        BooleanExpression exContent = qMember.mb_nick.contains(keyword);
//        BooleanExpression exAll=exTitle.or(exContent);
//        builder.and(exAll);
//
//        //and 조건
//        builder.and(qMember.mb_no.gt(0L));
//
//        Page<Member>   result  =  memberRepository.findAll( builder, pageable); // 5
//
//        result.stream().forEach(member->{
//            System.out.println(member);
//        });
//
//    }

}

