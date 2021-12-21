package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDonateReq is a Querydsl query type for DonateReq
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDonateReq extends EntityPathBase<DonateReq> {

    private static final long serialVersionUID = -1635772643L;

    public static final QDonateReq donateReq = new QDonateReq("donateReq");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath donatepersonaddr = createString("donatepersonaddr");

    public final StringPath donatepersonbirthday = createString("donatepersonbirthday");

    public final StringPath donatepersonname = createString("donatepersonname");

    public final StringPath donatepersonsex = createString("donatepersonsex");

    public final NumberPath<Integer> donateprice = createNumber("donateprice", Integer.class);

    public final StringPath donatereqdate = createString("donatereqdate");

    public final NumberPath<Integer> donateseq = createNumber("donateseq", Integer.class);

    public final StringPath donatestatus = createString("donatestatus");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> reqseq = createNumber("reqseq", Integer.class);

    public QDonateReq(String variable) {
        super(DonateReq.class, forVariable(variable));
    }

    public QDonateReq(Path<? extends DonateReq> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDonateReq(PathMetadata metadata) {
        super(DonateReq.class, metadata);
    }

}

