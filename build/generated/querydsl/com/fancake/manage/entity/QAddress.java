package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -248214814L;

    public static final QAddress address = new QAddress("address");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath abookaddress1 = createString("abookaddress1");

    public final StringPath abookaddress2 = createString("abookaddress2");

    public final StringPath abookaddress3 = createString("abookaddress3");

    public final StringPath abookinfo = createString("abookinfo");

    public final NumberPath<Integer> abookmbno = createNumber("abookmbno", Integer.class);

    public final StringPath abookphone = createString("abookphone");

    public final StringPath abookreciever = createString("abookreciever");

    public final StringPath abookregtime = createString("abookregtime");

    public final NumberPath<Integer> abookseq = createNumber("abookseq", Integer.class);

    public final StringPath abooktitle = createString("abooktitle");

    public final StringPath abookzipcode = createString("abookzipcode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata metadata) {
        super(Address.class, metadata);
    }

}

