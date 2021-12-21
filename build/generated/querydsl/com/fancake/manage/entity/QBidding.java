package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBidding is a Querydsl query type for Bidding
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBidding extends EntityPathBase<Bidding> {

    private static final long serialVersionUID = 782021225L;

    public static final QBidding bidding = new QBidding("bidding");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath abookaddress1 = createString("abookaddress1");

    public final StringPath abookaddress2 = createString("abookaddress2");

    public final StringPath abookaddress3 = createString("abookaddress3");

    public final StringPath abookinfo = createString("abookinfo");

    public final StringPath abookphone = createString("abookphone");

    public final StringPath abookreciever = createString("abookreciever");

    public final StringPath abookzipcode = createString("abookzipcode");

    public final StringPath biddingcanceldate = createString("biddingcanceldate");

    public final StringPath biddingdate = createString("biddingdate");

    public final NumberPath<Integer> biddingmbno = createNumber("biddingmbno", Integer.class);

    public final NumberPath<Integer> biddingprice = createNumber("biddingprice", Integer.class);

    public final NumberPath<Integer> biddingseq = createNumber("biddingseq", Integer.class);

    public final StringPath biddingstatus = createString("biddingstatus");

    public final StringPath biddingsuccdate = createString("biddingsuccdate");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final NumberPath<Integer> itemseq = createNumber("itemseq", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QBidding(String variable) {
        super(Bidding.class, forVariable(variable));
    }

    public QBidding(Path<? extends Bidding> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBidding(PathMetadata metadata) {
        super(Bidding.class, metadata);
    }

}

