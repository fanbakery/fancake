package com.fancake.manage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHpAuth is a Querydsl query type for HpAuth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHpAuth extends EntityPathBase<HpAuth> {

    private static final long serialVersionUID = -1737222622L;

    public static final QHpAuth hpAuth = new QHpAuth("hpAuth");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath cert = createString("cert");

    public final StringPath expiretime = createString("expiretime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> itemregDate = _super.itemregDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath sendtime = createString("sendtime");

    public QHpAuth(String variable) {
        super(HpAuth.class, forVariable(variable));
    }

    public QHpAuth(Path<? extends HpAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHpAuth(PathMetadata metadata) {
        super(HpAuth.class, metadata);
    }

}

