package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPay is a Querydsl query type for Pay
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPay extends EntityPathBase<Pay> {

    private static final long serialVersionUID = -1487452522L;

    public static final QPay pay = new QPay("pay");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath issettlerequest = createString("issettlerequest");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    public final NumberPath<Integer> itemseq = createNumber("itemseq", Integer.class);

    public final NumberPath<Integer> mbno = createNumber("mbno", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath settleday = createString("settleday");

    public final NumberPath<Integer> settleprice = createNumber("settleprice", Integer.class);

    public final StringPath settleregdate = createString("settleregdate");

    public final StringPath settlereqdate = createString("settlereqdate");

    public final StringPath settlestatus = createString("settlestatus");

    public QPay(String variable) {
        super(Pay.class, forVariable(variable));
    }

    public QPay(Path<? extends Pay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPay(PathMetadata metadata) {
        super(Pay.class, metadata);
    }

}

